package net.yurkevichkazimir.tryingmod.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Pig;

import java.util.EnumSet;

public class RunAroundLikeCrazyGoal extends Goal {
    private final Pig pig;
    private final double speedModifier;

    public RunAroundLikeCrazyGoal(Pig pig, double speedModifier) {
        this.pig = pig;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return true;  // Always runs this goal
    }

    @Override
    public void start() {
        movePigRandomly();  // Start running immediately
    }

    @Override
    public void tick() {
        // Continue making the pig run in random directions
        if (this.pig.getRandom().nextInt(10) == 0) {
            movePigRandomly();
        }
    }

    private void movePigRandomly() {
        double randomX = this.pig.getX() + (this.pig.getRandom().nextDouble() - 0.5) * 20.0;
        double randomY = this.pig.getY();
        double randomZ = this.pig.getZ() + (this.pig.getRandom().nextDouble() - 0.5) * 20.0;
        this.pig.getNavigation().moveTo(randomX, randomY, randomZ, this.speedModifier);
    }
}