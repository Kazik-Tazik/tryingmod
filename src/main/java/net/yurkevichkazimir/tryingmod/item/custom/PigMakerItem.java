package net.yurkevichkazimir.tryingmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.yurkevichkazimir.tryingmod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class PigMakerItem extends Item {
    public PigMakerItem(Properties pProperties) {
        super(pProperties.durability(10));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player1 = pContext.getPlayer();
            ServerLevel world = (ServerLevel) pContext.getLevel();

            BlockState clickedBlock = world.getBlockState(positionClicked);
            world.setBlock(positionClicked, Blocks.AIR.defaultBlockState(), 3);

            Pig pig = new Pig(EntityType.PIG, world);
            pig.setPos(positionClicked.getX() + 0.5, positionClicked.getY(), positionClicked.getZ() + 0.5);
            world.addFreshEntity(pig);

            ItemStack itemStack = pContext.getItemInHand();
            itemStack.hurtAndBreak(1, player1, (p) -> p.broadcastBreakEvent(pContext.getHand()));

            pContext.getLevel().playSeededSound(null, positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(), ModSounds.PIG_MAKER_ITEM_SOUND.get(), SoundSource.BLOCKS, 1f, 1f, 0);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide()) {
            ServerLevel world = (ServerLevel) target.level();
            BlockPos position = target.blockPosition();

            Pig pig = new Pig(EntityType.PIG, world);
            pig.setPos(position.getX() + 0.5, position.getY(), position.getZ() + 0.5);
            world.addFreshEntity(pig);

            target.discard();

            stack.hurtAndBreak(1, attacker, (p) -> p.broadcastBreakEvent(attacker.getUsedItemHand()));

        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE; // Choose from COMMON, UNCOMMON, RARE, EPIC
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.tryingmod.pig_maker_item.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

