package net.yurkevichkazimir.tryingmod.event;

import net.yurkevichkazimir.tryingmod.entity.client.*;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = tryingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.KAMIZELKA_LAYER, KamizelkaModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ZUFIK_LAYER, ZufikModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BINHLI_LAYER, BinhliModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ZOPSIK_LAYER, ZopsikModel::createBodyLayer);
    }
}