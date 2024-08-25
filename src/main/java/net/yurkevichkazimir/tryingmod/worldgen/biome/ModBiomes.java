package net.yurkevichkazimir.tryingmod.worldgen.biome;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.sound.ModSounds;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.worldgen.feature.ModFeatures;

import java.util.List;

public class ModBiomes {
    public static final ResourceKey<Biome> FRANCE_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(tryingMod.MOD_ID, "france_biome"));

    public static void boostrap(BootstapContext<Biome> context) {
        context.register(FRANCE_BIOME, testBiome(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome testBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ZUFIK.get(), 6, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.BINHLI.get(), 6, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.KAMIZELKA.get(), 6, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ZOPSIK.get(), 6, 1, 1));



        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        addFractalCubesFeature(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)  // No rain or snow
                .downfall(0f)
                .temperature(10f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x500c6b)
                        .waterFogColor(0x500c6b)
                        .skyColor(0x500c6b)
                        .grassColorOverride(0x8f8f8f)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x1f1f1f)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.KAMIZELKA_HURT_SOUND.getHolder().get())).build())
                .build();
    }

    private static void addFractalCubesFeature(BiomeGenerationSettings.Builder biomeBuilder) {
        ConfiguredFeature<NoneFeatureConfiguration, ?> fractalCubeConfiguredFeature = new ConfiguredFeature<>(
                ModFeatures.FRACTAL_CUBE.get(), NoneFeatureConfiguration.INSTANCE
        );

        PlacedFeature fractalCubePlacedFeature = new PlacedFeature(
                Holder.direct(fractalCubeConfiguredFeature),
                List.of(
                        RarityFilter.onAverageOnceEvery(100),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE
                )
        );

        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Holder.direct(fractalCubePlacedFeature));
    }
}