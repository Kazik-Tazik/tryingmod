package net.yurkevichkazimir.tryingmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.block.entity.ModBlocksEntities;
import net.yurkevichkazimir.tryingmod.effect.ModEffects;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.entity.client.*;
import net.yurkevichkazimir.tryingmod.event.ModEventHandlers;
import net.yurkevichkazimir.tryingmod.item.ModCreativeModTabs;
import net.yurkevichkazimir.tryingmod.item.ModItem;
import net.yurkevichkazimir.tryingmod.item.custom.PotatoItem;
import net.yurkevichkazimir.tryingmod.loot.ModLootModifiers;
import net.yurkevichkazimir.tryingmod.potion.ModPotions;
import net.yurkevichkazimir.tryingmod.recipe.ModRecipes;
import net.yurkevichkazimir.tryingmod.screen.ModMenuTypes;
import net.yurkevichkazimir.tryingmod.screen.PotatoExplosionMakerScreen;
import net.yurkevichkazimir.tryingmod.sound.ModSounds;
import net.yurkevichkazimir.tryingmod.villager.ModVillagers;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(tryingMod.MOD_ID)
public class tryingMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "tryingmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public tryingMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItem.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);

        ModEntities.register(modEventBus);

        ModSounds.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlocksEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(ModEventHandlers.class);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItem.KAMIZELKA_SPAWN_EGG);
            event.accept(ModItem.ZUFIK_SPAWN_EGG);
            event.accept(ModItem.BINHLI_SPAWN_EGG);
            event.accept(ModItem.ZOPSIK_SPAWN_EGG);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.KAMIZELKA.get(), KamizelkaRenderer::new);
            EntityRenderers.register(ModEntities.ZUFIK.get(), ZufikRenderer::new);
            EntityRenderers.register(ModEntities.BINHLI.get(), BinhliRenderer::new);
            EntityRenderers.register(ModEntities.ZOPSIK.get(), ZopsikRenderer::new);

            EntityRenderers.register(ModEntities.POTATO_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.CARROT_PROJECTILE.get(), ThrownItemRenderer::new);
            MenuScreens.register(ModMenuTypes.POTATO_EXPLOSION_MAKER_MENU.get(), PotatoExplosionMakerScreen::new);


        }
    }
}
