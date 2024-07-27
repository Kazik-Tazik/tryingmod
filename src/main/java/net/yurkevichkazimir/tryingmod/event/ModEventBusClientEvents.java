package net.yurkevichkazimir.tryingmod.event;

import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.entity.client.ModModelLayers;
import net.yurkevichkazimir.tryingmod.entity.client.KamizelkaModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = tryingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.KAMIZELKA_LAYER, KamizelkaModel::createBodyLayer);
    }
}