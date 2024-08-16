package net.yurkevichkazimir.tryingmod.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.effect.ModEffects;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, tryingMod.MOD_ID);

    public static final RegistryObject<Potion> PIG_POTION = POTIONS.register("pig_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PIG_EFFECT.get(), 70, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}