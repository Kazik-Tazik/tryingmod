package net.yurkevichkazimir.tryingmod.entity.custom;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.item.ModItem;

public class PotatoProjectileEntity extends ThrowableItemProjectile {
    public PotatoProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public PotatoProjectileEntity(Level pLevel) {
        super(ModEntities.POTATO_PROJECTILE.get(), pLevel);
    }

    public PotatoProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.POTATO_PROJECTILE.get(), livingEntity, pLevel);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItem.POTATO_PROJECTILE.get();
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity hitEntity = pResult.getEntity();

        hitEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);

        if (!this.level().isClientSide() && this.level().random.nextFloat() < 0.8F) {
            // Convert this entity to an item stack and drop it
            this.spawnAtLocation(this.getItem());
        }
        this.discard();
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide() && this.level().random.nextFloat() < 0.8F) {
            this.spawnAtLocation(this.getItem());
        }
        this.discard();
    }
}
