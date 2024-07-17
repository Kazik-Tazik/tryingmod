package net.yurkevichkazimir.tryingmod.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yurkevichkazimir.tryingmod.tryingMod;

@Mod.EventBusSubscriber(modid = tryingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class CommonEvents {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getTags().contains("fly_to_space")) {
            int flyTime = entity.getPersistentData().getInt("fly_time");
            if (flyTime < 200) { // Apply force for a limited time
                double upwardVelocity = 0.1 + (flyTime * 0.01); // Increase speed over time
                entity.setDeltaMovement(new Vec3(entity.getDeltaMovement().x, upwardVelocity, entity.getDeltaMovement().z));
                entity.getPersistentData().putInt("fly_time", flyTime + 1);
            } else {
                entity.removeTag("fly_to_space"); // Remove tag after the duration
            }
        }
    }
}

