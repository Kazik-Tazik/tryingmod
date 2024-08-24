package net.yurkevichkazimir.tryingmod.block.custom;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.yurkevichkazimir.tryingmod.entity.custom.CarrotProjectileEntity;
import net.yurkevichkazimir.tryingmod.worldgen.dimension.ModDimensions;
import net.yurkevichkazimir.tryingmod.worldgen.portal.ModTeleporter;

public class ModPortalBlock extends Block {
    public ModPortalBlock(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
      // Check if the projectile is an instance of your custom carrot projectile
        if (pProjectile instanceof CarrotProjectileEntity) {
            Entity shooter = pProjectile.getOwner();
           if (shooter != null) {
                handleFrancePortal(shooter, pHit.getBlockPos());
           }
       }
    }

    private void handleFrancePortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == ModDimensions.FRANCEDIM_LEVEL_KEY ?
                    Level.OVERWORLD : ModDimensions.FRANCEDIM_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if(resourcekey == ModDimensions.FRANCEDIM_LEVEL_KEY) {
                    player.changeDimension(portalDimension, new ModTeleporter(pPos, true));
                } else {
                    player.changeDimension(portalDimension, new ModTeleporter(pPos, false));
                }
            }
        }
    }
}