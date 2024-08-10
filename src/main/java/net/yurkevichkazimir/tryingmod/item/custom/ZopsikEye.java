package net.yurkevichkazimir.tryingmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ZopsikEye extends Item {
    public ZopsikEye(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON; // Choose from COMMON, UNCOMMON, RARE, EPIC
    }
}
