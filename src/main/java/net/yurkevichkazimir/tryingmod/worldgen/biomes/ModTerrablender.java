package net.yurkevichkazimir.tryingmod.worldgen.biomes;

import net.minecraft.resources.ResourceLocation;
import net.yurkevichkazimir.tryingmod.tryingMod;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(tryingMod.MOD_ID, "overworld"), 5));
    }
}