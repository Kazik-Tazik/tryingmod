package net.yurkevichkazimir.tryingmod.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.phys.Vec3;
import net.yurkevichkazimir.tryingmod.entity.custom.CarrotProjectileEntity;
import net.yurkevichkazimir.tryingmod.sound.ModSounds;

public class FrenchStaffItem extends Item {

    public FrenchStaffItem(Properties properties) {
        super(properties.stacksTo(1).durability(512).rarity(Rarity.EPIC));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            CarrotProjectileEntity carrotProjectile = new CarrotProjectileEntity(level, player);

            Vec3 lookDirection = player.getLookAngle();
            carrotProjectile.setPos(player.getX(), player.getEyeY() - 0.1F, player.getZ());
            carrotProjectile.setDeltaMovement(lookDirection.x * 1.5, lookDirection.y * 1.5, lookDirection.z * 1.5);

            level.addFreshEntity(carrotProjectile);

            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.FRENCH_STAFF_ITEM_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

            player.getItemInHand(hand).hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}