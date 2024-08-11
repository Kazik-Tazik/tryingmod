package net.yurkevichkazimir.tryingmod.entity.custom;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;

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
        return Items.POTATO;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity hitEntity = pResult.getEntity();

        hitEntity.hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);

        this.discard();
        super.onHitEntity(pResult);
    }
}
