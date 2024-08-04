package net.yurkevichkazimir.tryingmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yurkevichkazimir.tryingmod.entity.ai.BinhliBreakGoal;

@Mod.EventBusSubscriber(modid = "tryingmod")
public class BlockPlaceEventHandler {

    @SubscribeEvent
    public static void onBlockPlacedByPlayer(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof Player) {
            BlockPos pos = event.getPos();
            BinhliBreakGoal.markBlockAsPlayerPlaced(pos);
        }
    }
}
