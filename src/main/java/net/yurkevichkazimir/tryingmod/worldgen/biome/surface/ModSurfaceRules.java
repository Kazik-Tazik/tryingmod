package net.yurkevichkazimir.tryingmod.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final SurfaceRules.RuleSource CHUGUNOK_BLOCK = makeStateRule(ModBlocks.CHUGUNOK_BLOCK.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.FRANCE_BIOME),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MYCELIUM)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CHUGUNOK_BLOCK))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}