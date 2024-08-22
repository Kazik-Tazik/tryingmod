package net.yurkevichkazimir.tryingmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.block.Blocks;

public class FractalCubesInFranceProvider extends Feature<NoneFeatureConfiguration> {

    public FractalCubesInFranceProvider(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        WorldgenRandom random = (WorldgenRandom) context.random();

        // Number of fractal cubes to generate
        int numCubes = random.nextInt(1)+ 1; // Random number of cubes (1 to 10)

        for (int i = 0; i < numCubes; i++) {
            int x = pos.getX() + random.nextInt(64) - 32;
            int y = pos.getY() + random.nextInt(60) + 100; // Position cubes in the sky (Y = 100 to 160)
            int z = pos.getZ() + random.nextInt(64) - 32;

            int size = random.nextInt(50) + 2; // Random size between 2 and 7
            int depth = random.nextInt(3) + 1; // Random depth between 1 and 3

            buildFractalCube(world, new BlockPos(x, y, z), size, depth);
        }

        return true;
    }

    private void buildFractalCube(WorldGenLevel world, BlockPos centerPos, int size, int depth) {
        // Base case: If depth is 0, stop recursion
        if (depth == 0) {
            return;
        }

        // Calculate half size for the current cube
        int halfSize = size / 2;

        // Iterate over the 3x3x3 grid (including center and corners)
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    // Skip the center of the current cube (i.e., when all x, y, z are 0)
                    if (x == 0 && y == 0 && z == 0) continue;

                    // Calculate the position for the smaller cube
                    BlockPos newPos = centerPos.offset(x * halfSize, y * halfSize, z * halfSize);

                    // If at the lowest depth, place the block
                    if (depth == 1) {
                        // Make sure the size is set appropriately
                        if (world.isEmptyBlock(newPos)) {
                            world.setBlock(newPos, Blocks.WHITE_CONCRETE.defaultBlockState(), 3);
                        }
                    } else {
                        // Recursively build smaller cubes at the corners and centers of faces
                        buildFractalCube(world, newPos, halfSize, depth - 1);
                    }
                }
            }
        }
    }
}