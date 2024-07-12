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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.FLUPEL.get()))
                    .title(Component.translatable("creativetab.trying_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItem.FLUPEL.get());
                        pOutput.accept(ModItem.KAZIK.get());
                        pOutput.accept(ModItem.MAKAR.get());
                        pOutput.accept(ModItem.LYOSHA.get());
                        pOutput.accept(ModItem.PUDDLE_OF_CUM.get());
                        pOutput.accept(ModItem.BAKED_CUM.get());

                        pOutput.accept(ModBlocks.CUM_BLOCK.get());
                        pOutput.accept(ModBlocks.BAKED_CUM_BLOCK.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}