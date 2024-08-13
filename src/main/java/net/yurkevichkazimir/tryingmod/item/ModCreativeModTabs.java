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

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.CHUGUNOK.get()))
                    .title(Component.translatable("creativetab.trying_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItem.CHUGUNOK_STICK.get());
                        pOutput.accept(ModItem.CHUGUNOK.get());
                        pOutput.accept(ModItem.PIG_MAKER_ITEM.get());
                        pOutput.accept(ModItem.TWENTY_FOUR_HOUR_FUEL.get());
                        pOutput.accept(ModItem.ZOPSIK_EYE.get());
                        pOutput.accept(ModItem.KAMIZELKA_SPAWN_EGG.get());
                        pOutput.accept(ModItem.ZUFIK_SPAWN_EGG.get());
                        pOutput.accept(ModItem.BINHLI_SPAWN_EGG.get());
                        pOutput.accept(ModItem.ZOPSIK_SPAWN_EGG.get());
                        pOutput.accept(ModItem.POTATO_PROJECTILE.get());
                        pOutput.accept(ModItem.CARROT_PROJECTILE.get());

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

                        pOutput.accept(ModItem.DRANIKI.get());
                        pOutput.accept(ModItem.FRACTAL_GRASS.get());
                        pOutput.accept(ModItem.PIG_SEEDS.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}