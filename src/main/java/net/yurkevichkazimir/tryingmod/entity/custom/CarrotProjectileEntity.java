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

        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) pResult.getEntity();

            entity.addEffect(new MobEffectInstance(ModEffects.PIG_EFFECT.get(), 20, 1));
        }

        this.discard();
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        // Convert Vec3 to BlockPos
        Vec3 hitVec = pResult.getLocation();
        BlockPos hitPos = new BlockPos((int) hitVec.x(), (int)hitVec.y() + 1, (int)hitVec.z());

        this.discard();
    }

    private void buildFractalCube(BlockPos centerPos, int size, int depth) {
        // Base case: If depth is 0, stop recursion
        if (depth == 0) {
            return;
        }

        // Calculate half size for the current cube
        int halfSize = size / 2;

        // Iterate over the 3x3x3 grid (including center and corners)
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    // Skip the center of the current cube (i.e., when all x, y, z are 0)
                    if (x == 0 && y == 0 && z == 0) continue;

                    // Calculate the position for the smaller cube
                    BlockPos newPos = centerPos.offset(x * halfSize, y * halfSize, z * halfSize);

                    // If at the lowest depth, place the block
                    if (depth == 1) {
                        if (this.level().isEmptyBlock(newPos)) {
                            this.level().setBlock(newPos, Blocks.WHITE_CONCRETE.defaultBlockState(), 3);
                        }
                    } else {
                        // Recursively build smaller cubes at the corners and centers of faces
                        buildFractalCube(newPos, halfSize, depth - 1);
                    }
                }
            }
        }
    }

}
