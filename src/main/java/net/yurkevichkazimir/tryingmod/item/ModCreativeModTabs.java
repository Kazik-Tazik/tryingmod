package net.yurkevichkazimir.tryingmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, tryingMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TRYING_MOD_BLOCKS_TAB = CREATIVE_MODE_TABS.register("trying_mod_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CHUGUNOK_BLOCK.get()))
                    .title(Component.translatable("creativetab.trying_mod_blocks_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CHUGUNOK_BLOCK.get());
                        pOutput.accept(ModBlocks.CHUGUNOK_ORE.get());
                        pOutput.accept(ModBlocks.PORK_BLOCK.get());
                        pOutput.accept(ModBlocks.PORK_STAIRS.get());
                        pOutput.accept(ModBlocks.PORK_SLAB.get());
                        pOutput.accept(ModBlocks.PORK_DOOR.get());
                        pOutput.accept(ModBlocks.PORK_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.PORK_FENCE.get());
                        pOutput.accept(ModBlocks.PORK_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.PORK_BUTTON.get());
                        pOutput.accept(ModBlocks.PORK_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.PORK_WALL.get());
                        pOutput.accept(ModBlocks.COOKED_PORK_BLOCK.get());
                        pOutput.accept(ModBlocks.POTATO_EXPLOSION_MAKER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> TRYING_MOD_ITEMS_TAB = CREATIVE_MODE_TABS.register("trying_mod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.PIG_MAKER_ITEM.get()))
                    .title(Component.translatable("creativetab.trying_mod_items_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItem.CHUGUNOK_STICK.get());
                        pOutput.accept(ModItem.CHUGUNOK.get());
                        pOutput.accept(ModItem.PIG_MAKER_ITEM.get());
                        pOutput.accept(ModItem.HEALTH_WRITE_ITEM.get());
                        pOutput.accept(ModItem.FRENCH_STAFF_ITEM.get());
                        pOutput.accept(ModItem.TWENTY_FOUR_HOUR_FUEL.get());
                        pOutput.accept(ModItem.ZOPSIK_EYE.get());
                        pOutput.accept(ModItem.POTATO_PROJECTILE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> TRYING_MOD_FOOD_TAB = CREATIVE_MODE_TABS.register("trying_mod_food_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.DRANIKI.get()))
                    .title(Component.translatable("creativetab.trying_mod_food_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItem.DRANIKI.get());
                        pOutput.accept(ModItem.FRACTAL_GRASS.get());
                        pOutput.accept(ModItem.PIG_SEEDS.get());
                    })
                    .build());


    public static final RegistryObject<CreativeModeTab> TRYING_MOD_ENTITY_TAB = CREATIVE_MODE_TABS.register("trying_mod_entity_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.KAMIZELKA_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.trying_mod_entity_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItem.KAMIZELKA_SPAWN_EGG.get());
                        pOutput.accept(ModItem.ZUFIK_SPAWN_EGG.get());
                        pOutput.accept(ModItem.BINHLI_SPAWN_EGG.get());
                        pOutput.accept(ModItem.ZOPSIK_SPAWN_EGG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}