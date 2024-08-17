package net.yurkevichkazimir.tryingmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HealthWriteItem extends Item {
    public HealthWriteItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!player.level().isClientSide()) {
            float health = target.getHealth();
            String entityName = target.getDisplayName().getString();
            player.sendSystemMessage(Component.literal("The health of " + entityName + " is: " + health + " HP"));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
