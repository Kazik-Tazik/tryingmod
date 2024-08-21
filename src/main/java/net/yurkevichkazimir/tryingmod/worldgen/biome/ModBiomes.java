package net.yurkevichkazimir.tryingmod.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.sound.ModSounds;
import net.yurkevichkazimir.tryingmod.tryingMod;

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
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.ZUFIK.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.BINHLI.get(), 3, 2, 4));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Explicitly avoid adding water features
        // No default carvers to avoid caves and ravines
        // No lakes or springs
        // Add only essential features like ores, vegetation

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder); // Keep ore generation
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        // Avoid features that could generate water (lakes, springs, etc.)
        // No features that add water blocks

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
}