package net.yurkevichkazimir.tryingmod.event;

import net.yurkevichkazimir.tryingmod.entity.custom.BinhliEntity;
import net.yurkevichkazimir.tryingmod.entity.custom.ZopsikEntity;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.entity.custom.KamizelkaEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = tryingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.KAMIZELKA.get(), KamizelkaEntity.createAttributes().build());
        event.put(ModEntities.ZUFIK.get(), KamizelkaEntity.createAttributes().build());
        event.put(ModEntities.BINHLI.get(), BinhliEntity.createAttributes().build());
        event.put(ModEntities.ZOPSIK.get(), ZopsikEntity.createAttributes().build());
    }
}