package net.yurkevichkazimir.tryingmod.worldgen.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, tryingMod.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FRACTAL_CUBE = FEATURES.register("fractal_cube",
            () -> new FractalCubesInFranceProvider(NoneFeatureConfiguration.CODEC));
}