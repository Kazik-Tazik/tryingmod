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
                        pOutput.accept(ModItem.PIG_MAKER_ITEM.get());

                        pOutput.accept(ModBlocks.CHUGUNOK_BLOCK.get());
                        pOutput.accept(ModBlocks.CHUGUNOK_ORE.get());
                        pOutput.accept(ModBlocks.PORK_BLOCK.get());
                        pOutput.accept(ModBlocks.COOKED_PORK_BLOCK.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}