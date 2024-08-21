package net.yurkevichkazimir.tryingmod.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.block.custom.PigCropBlock;
import net.yurkevichkazimir.tryingmod.item.ModItem;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CHUGUNOK_BLOCK.get());
        dropSelf(ModBlocks.FRENCH_DIRT.get());
        dropSelf(ModBlocks.PORK_BLOCK.get());
        dropSelf(ModBlocks.COOKED_PORK_BLOCK.get());
        dropSelf(ModBlocks.POTATO_EXPLOSION_MAKER.get());

        dropSelf(ModBlocks.PORK_STAIRS.get());
        dropSelf(ModBlocks.PORK_BUTTON.get());
        dropSelf(ModBlocks.PORK_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PORK_FENCE_GATE.get());
        dropSelf(ModBlocks.PORK_WALL.get());
        dropSelf(ModBlocks.PORK_FENCE.get());
        dropSelf(ModBlocks.PORK_TRAPDOOR.get());

        this.add(ModBlocks.PORK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.PORK_SLAB.get()));

        this.add(ModBlocks.PORK_DOOR.get(),
                block -> createDoorTable(ModBlocks.PORK_DOOR.get()));

        this.add(ModBlocks.CHUGUNOK_ORE.get(),
                block -> createOreDrop(ModBlocks.CHUGUNOK_ORE.get(), ModItem.CHUGUNOK.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.PIG_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PigCropBlock.AGE, 5));

        this.add(ModBlocks.PIG_CROP.get(), createCropDrops(ModBlocks.PIG_CROP.get(), Items.PORKCHOP,
                ModItem.PIG_SEEDS.get(), lootitemcondition$builder));
    }


    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                (LootPoolEntryContainer.Builder)this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
