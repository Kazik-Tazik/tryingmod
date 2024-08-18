package net.yurkevichkazimir.tryingmod.entity.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.yurkevichkazimir.tryingmod.effect.ModEffects;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.item.ModItem;

public class CarrotProjectileEntity extends ThrowableItemProjectile {
    private int age;

    public CarrotProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CarrotProjectileEntity(Level pLevel) {
        super(ModEntities.CARROT_PROJECTILE.get(), pLevel);
    }

    public CarrotProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.CARROT_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItem.CARROT_PROJECTILE.get();
    }

    @Override
    public void tick() {
        super.tick();
        age++;

        if (!this.level().isClientSide && age > 100) {
            this.discard();
        }
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) pResult.getEntity();

            entity.addEffect(new MobEffectInstance(ModEffects.PIG_EFFECT.get(), 20, 1));
        }

        this.discard();
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        this.discard();
    }


}
