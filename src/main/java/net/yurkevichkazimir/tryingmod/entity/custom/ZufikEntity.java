package net.yurkevichkazimir.tryingmod.entity.custom;

import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Set;

public class ZufikEntity extends Animal implements ItemSteerable, Saddleable {

    private static final float SUFFOCATE_STEERING_MODIFIER = 0.35F;
    private static final float STEERING_MODIFIER = 0.55F;

    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_SUFFOCATING;
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private final ItemBasedSteering steering;

    public ZufikEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.steering = new ItemBasedSteering(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);
    }

    public static final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.KELP), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 5f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));


    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_BOOST_TIME.equals(pKey) && this.level().isClientSide) {
            this.steering.onSynced();
        }

        super.onSyncedDataUpdated(pKey);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BOOST_TIME, 0);
        this.entityData.define(DATA_SUFFOCATING, false);
        this.entityData.define(DATA_SADDLE_ID, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.steering.addAdditionalSaveData(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.steering.readAdditionalSaveData(pCompound);
    }

    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    public void equipSaddle(@javax.annotation.Nullable SoundSource pSource) {
        this.steering.setSaddle(true);
        if (pSource != null) {
            this.level().playSound((Player)null, this, SoundEvents.STRIDER_SADDLE, pSource, 0.5F, 1.0F);
        }

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 16D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.01f);
    }

    public double getPassengersRidingOffset() {
        float $$0 = Math.min(0.25F, this.walkAnimation.speed());
        float $$1 = this.walkAnimation.position();
        return (double)this.getBbHeight() - 0.19 + (double)(0.12F * Mth.cos($$1 * 1.5F) * 2.0F * $$0);
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity var2 = this.getFirstPassenger();
        if (var2 instanceof Player $$0) {
            if ($$0.getMainHandItem().is(Items.DRIED_KELP_BLOCK)) {
                return $$0;
            }
        }

        return null;
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        boolean $$2 = this.isFood(pPlayer.getItemInHand(pHand));
        if (!$$2 && this.isSaddled() && !this.isVehicle() && !pPlayer.isSecondaryUseActive()) {
            if (!this.level().isClientSide) {
                pPlayer.startRiding(this);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            InteractionResult $$3 = super.mobInteract(pPlayer, pHand);
            if (!$$3.consumesAction()) {
                ItemStack $$4 = pPlayer.getItemInHand(pHand);
                return $$4.is(Items.SADDLE) ? $$4.interactLivingEntity(pPlayer, this, pHand) : InteractionResult.PASS;
            } else {
                if ($$2 && !this.isSilent()) {
                    this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.STRIDER_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                }

                return $$3;
            }
        }
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Vec3[] $$1 = new Vec3[]{getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot()), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() - 22.5F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() + 22.5F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() - 45.0F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot() + 45.0F)};
        Set<BlockPos> $$2 = Sets.newLinkedHashSet();
        double $$3 = this.getBoundingBox().maxY;
        double $$4 = this.getBoundingBox().minY - 0.5;
        BlockPos.MutableBlockPos $$5 = new BlockPos.MutableBlockPos();
        Vec3[] var9 = $$1;
        int var10 = $$1.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            Vec3 $$6 = var9[var11];
            $$5.set(this.getX() + $$6.x, $$3, this.getZ() + $$6.z);

            for(double $$7 = $$3; $$7 > $$4; --$$7) {
                $$2.add($$5.immutable());
                $$5.move(Direction.DOWN);
            }
        }

        Iterator var17 = $$2.iterator();

        while(true) {
            BlockPos $$8;
            double $$9;
            do {
                do {
                    if (!var17.hasNext()) {
                        return new Vec3(this.getX(), this.getBoundingBox().maxY, this.getZ());
                    }

                    $$8 = (BlockPos)var17.next();
                } while(this.level().getFluidState($$8).is(FluidTags.LAVA));

                $$9 = this.level().getBlockFloorHeight($$8);
            } while(!DismountHelper.isBlockFloorValid($$9));

            Vec3 $$10 = Vec3.upFromBottomCenterOf($$8, $$9);
            UnmodifiableIterator var14 = pLivingEntity.getDismountPoses().iterator();

            while(var14.hasNext()) {
                Pose $$11 = (Pose)var14.next();
                AABB $$12 = pLivingEntity.getLocalBoundsForPose($$11);
                if (DismountHelper.canDismountTo(this.level(), pLivingEntity, $$12.move($$10))) {
                    pLivingEntity.setPose($$11);
                    return $$10;
                }
            }
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.ZUFIK.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.KELP);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BAT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }

    @Override
    public boolean boost() {
        return false;
    }

    @Override
    public boolean isSaddleable() {
        return false;
    }

    static {
        DATA_BOOST_TIME = SynchedEntityData.defineId(ZufikEntity.class, EntityDataSerializers.INT);
        DATA_SUFFOCATING = SynchedEntityData.defineId(ZufikEntity.class, EntityDataSerializers.BOOLEAN);
        DATA_SADDLE_ID = SynchedEntityData.defineId(ZufikEntity.class, EntityDataSerializers.BOOLEAN);
    }
}
