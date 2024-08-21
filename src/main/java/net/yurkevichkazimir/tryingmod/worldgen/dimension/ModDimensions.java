package net.yurkevichkazimir.tryingmod.worldgen.dimension;

import com.ibm.icu.impl.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> FRANCEDIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(tryingMod.MOD_ID, "francedim"));
    public static final ResourceKey<Level> FRANCEDIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(tryingMod.MOD_ID, "francedim"));
    public static final ResourceKey<DimensionType> FRANCE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(tryingMod.MOD_ID, "francedim_type"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(FRANCE_DIM_TYPE, new DimensionType(
                OptionalLong.of(30000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, // infiniburn
                BuiltinDimensionTypes.END_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.FRANCE_BIOME)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));


        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.FRANCE_DIM_TYPE), wrappedChunkGenerator);

        context.register(FRANCEDIM_KEY, stem);
    }
}