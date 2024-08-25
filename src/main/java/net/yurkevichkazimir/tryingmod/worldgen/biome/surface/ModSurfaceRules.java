package net.yurkevichkazimir.tryingmod.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource FRENCH_DIRT = makeStateRule(ModBlocks.FRENCH_DIRT.get());

    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.FRANCE_BIOME),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FRENCH_DIRT)) // Apply FRENCH_DIRT on the surface
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}