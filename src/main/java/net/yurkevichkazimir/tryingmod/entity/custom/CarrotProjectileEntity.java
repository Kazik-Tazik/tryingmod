package net.yurkevichkazimir.tryingmod.entity.custom;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.item.ModItem;

public class CarrotProjectileEntity extends ThrowableItemProjectile {
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
    protected Item getDefaultItem() {
        return ModItem.CARROT_PROJECTILE.get();
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity hitEntity = pResult.getEntity();

        hitEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);

        if (!this.level().isClientSide() && this.level().random.nextFloat() < 0.8F) {
            this.level().explode(null, hitEntity.getX(), hitEntity.getY(), hitEntity.getZ(), 2.0F, Level.ExplosionInteraction.NONE);
        }
        this.discard();
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide()) {
            this.level().explode(null, pResult.getLocation().x(), pResult.getLocation().y(), pResult.getLocation().z(), 5.0F, Level.ExplosionInteraction.BLOCK);
        }
        this.discard();
    }

}
