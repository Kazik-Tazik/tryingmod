package net.yurkevichkazimir.tryingmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, tryingMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.VALUABLES)
                .add(ModBlocks.CHUGUNOK_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE) //Blocks to mine only with pickaxe
                .add(ModBlocks.CHUGUNOK_BLOCK.get(),
                        ModBlocks.CHUGUNOK_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE) ; //Blocks to mine only with axe


        this.tag(BlockTags.MINEABLE_WITH_SHOVEL) ; //Blocks to mine only with shovel


        this.tag(BlockTags.MINEABLE_WITH_HOE) ; //Blocks to mine only with hoe


        this.tag(BlockTags.NEEDS_IRON_TOOL)  //Blocks to mine only with iron tool
                .add(ModBlocks.CHUGUNOK_BLOCK.get(),
                        ModBlocks.CHUGUNOK_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL) ; //Blocks to mine only with diamond tool


        this.tag(BlockTags.NEEDS_STONE_TOOL) ; //Blocks to mine only with stone tool


    }
}
