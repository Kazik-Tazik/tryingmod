package net.yurkevichkazimir.tryingmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, tryingMod.MOD_ID);

    public static final RegistryObject<SoundEvent> PIG_MAKER_ITEM_SOUND = registerSoundEvents("pig_maker_item_sound");
    public static final RegistryObject<SoundEvent> KAMIZELKA_AMBIENT_SOUND = registerSoundEvents("kamizelka_ambient_sound");
    public static final RegistryObject<SoundEvent> KAMIZELKA_HURT_SOUND = registerSoundEvents("kamizelka_hurt_sound");



    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(tryingMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
