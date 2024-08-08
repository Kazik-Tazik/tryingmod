package net.yurkevichkazimir.tryingmod.event;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yurkevichkazimir.tryingmod.entity.custom.ZufikEntity;

@Mod.EventBusSubscriber(modid = "tryingmod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ZufikEntity) {
            event.setCanceled(true);
        }
    }
}