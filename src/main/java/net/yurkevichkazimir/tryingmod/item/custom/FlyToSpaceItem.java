package net.yurkevichkazimir.tryingmod.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class FlyToSpaceItem extends Item {
    public FlyToSpaceItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide) {
            // Set the initial upward velocity
            Vec3 initialVelocity = new Vec3(0, 2, 0);
            target.setDeltaMovement(target.getDeltaMovement().add(initialVelocity));

            // Gradually increase the speed upwards over time
            target.addTag("fly_to_space");
            target.getPersistentData().putInt("fly_time", 0);

            // Damage the item
            stack.hurtAndBreak(1, attacker, (entity) -> entity.broadcastBreakEvent(attacker.getUsedItemHand()));
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}