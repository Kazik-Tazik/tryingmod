package net.yurkevichkazimir.tryingmod.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.yurkevichkazimir.tryingmod.entity.custom.BinhliEntity;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BinhliBreakGoal extends Goal {
    private final Mob mob;
    public final Set<Block> blocksToAvoid; // Blocks that should not be broken
    private final int range;
    private final int mobHeight = 2; // Height of the Binhli in blocks
    private BlockPos targetPos;
    private static final Set<BlockPos> playerPlacedBlocks = new HashSet<>(); // Stores player-placed blocks

    public BinhliBreakGoal(Mob mob, Set<Block> blocksToAvoid, int range) {
        this.mob = mob;
        this.blocksToAvoid = blocksToAvoid;
        this.range = range;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public static void markBlockAsPlayerPlaced(BlockPos pos) {
        playerPlacedBlocks.add(pos);
    }

    @Override
    public boolean canUse() {
        // Check for nearby player-placed blocks
        targetPos = findNearbyBlock();
        return targetPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        // Ensure the target block is still valid, player-placed, and within range
        return targetPos != null && playerPlacedBlocks.contains(targetPos) &&
                mob.distanceToSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ()) <= range * range;
    }

    @Override
    public void start() {
        // Start moving towards the target block
        if (targetPos != null) {
            moveToAdjacentPosition();
        }
    }

    @Override
    public void tick() {
        if (targetPos != null) {
            double distanceSq = mob.distanceToSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ());
            if (distanceSq < 5.0) {
                if (mob instanceof BinhliEntity binhliEntity) {
                    // Trigger the block-breaking animation
                    if (binhliEntity.level().isClientSide()) {
                        binhliEntity.startBlockBreakingAnimation();
                    }
                }
                mob.level().destroyBlock(targetPos, false);
                playerPlacedBlocks.remove(targetPos); // Remove the block from the set after breaking
                targetPos = null;
            } else {
                // Continue moving towards the adjacent position if not close enough
                moveToAdjacentPosition();
            }
        }
    }

    @Override
    public void stop() {
        // Reset the target position
        targetPos = null;
        mob.getNavigation().stop();
    }

    private void moveToAdjacentPosition() {
        if (targetPos != null) {
            // Find an adjacent position to move to
            for (BlockPos pos : BlockPos.betweenClosed(targetPos.offset(-1, -1, -1), targetPos.offset(1, 1, 1))) {
                if (pos != targetPos && mob.level().getBlockState(pos).isAir()) {
                    mob.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.0);
                    return;
                }
            }
        }
    }

    private BlockPos findNearbyBlock() {
        BlockPos mobPos = mob.blockPosition();
        Level level = mob.level();
        for (int x = -range; x <= range; x++) {
            for (int y = 0; y <= range * mobHeight; y++) { // Start from y = 0 to 2 times the height of the mob
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = mobPos.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    if (playerPlacedBlocks.contains(pos) && !blocksToAvoid.contains(state.getBlock())) {
                        return pos;
                    }
                }
            }
        }
        return null;
    }
}
