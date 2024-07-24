package net.yurkevichkazimir.tryingmod.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier PORKCHOP = TierSortingRegistry.registerTier(
            new ForgeTier(0, 128, 6.0F, 1.0F, 14,
                    ModTags.Blocks.NEED_PORKCHOP_TOOL, () -> Ingredient.of(Items.PORKCHOP)),
            new ResourceLocation(tryingMod.MOD_ID, "porkchop"), List.of(Tiers.IRON), List.of());
}
