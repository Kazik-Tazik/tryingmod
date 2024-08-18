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
    public static final RegistryObject<SoundEvent> KAMIZELKA_DEATH_SOUND = registerSoundEvents("kamizelka_death_sound");

    public static final RegistryObject<SoundEvent> ZUFIK_AMBIENT_SOUND = registerSoundEvents("zufik_ambient_sound");
    public static final RegistryObject<SoundEvent> ZUFIK_HURT_SOUND = registerSoundEvents("zufik_hurt_sound");
    public static final RegistryObject<SoundEvent> ZUFIK_DEATH_SOUND = registerSoundEvents("zufik_death_sound");

    public static final RegistryObject<SoundEvent> ZOPSIK_AMBIENT_SOUND = registerSoundEvents("zopsik_ambient_sound");
    public static final RegistryObject<SoundEvent> ZOPSIK_HURT_SOUND = registerSoundEvents("zopsik_hurt_sound");
    public static final RegistryObject<SoundEvent> ZOPSIK_DEATH_SOUND = registerSoundEvents("zopsik_death_sound");

    public static final RegistryObject<SoundEvent> BINHLI_AMBIENT_SOUND = registerSoundEvents("binhli_ambient_sound");
    public static final RegistryObject<SoundEvent> BINHLI_HURT_SOUND = registerSoundEvents("binhli_hurt_sound");
    public static final RegistryObject<SoundEvent> BINHLI_DEATH_SOUND = registerSoundEvents("binhli_death_sound");

    public static final RegistryObject<SoundEvent> FRENCH_STAFF_ITEM_SOUND = registerSoundEvents("french_staff_item_sound");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(tryingMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
