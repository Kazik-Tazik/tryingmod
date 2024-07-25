package net.yurkevichkazimir.tryingmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.block.custom.PigCropBlock;
import net.yurkevichkazimir.tryingmod.tryingMod;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, tryingMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CHUGUNOK_BLOCK);
        blockWithItem(ModBlocks.PORK_BLOCK);
        blockWithItem(ModBlocks.COOKED_PORK_BLOCK);
        blockWithItem(ModBlocks.CHUGUNOK_ORE);

        stairsBlock(((StairBlock) ModBlocks.PORK_STAIRS.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.PORK_SLAB.get()), blockTexture(ModBlocks.PORK_BLOCK.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.PORK_BUTTON.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.PORK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.PORK_FENCE.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.PORK_FENCE_GATE.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.PORK_WALL.get()), blockTexture(ModBlocks.PORK_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.PORK_DOOR.get()), modLoc("block/pork_door_bottom"), modLoc("block/pork_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.PORK_TRAPDOOR.get()), modLoc("block/pork_trapdoor"), true, "cutout");

        makePigCrop((CropBlock) ModBlocks.PIG_CROP.get(), "pig_crop_stage", "pig_crop_stage");
    }

    public void makePigCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((PigCropBlock) block).getAgeProperty()),
                new ResourceLocation(tryingMod.MOD_ID, "block/" + textureName + state.getValue(((PigCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }


    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
