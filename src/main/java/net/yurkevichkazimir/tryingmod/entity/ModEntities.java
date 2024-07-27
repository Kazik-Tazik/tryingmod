package net.yurkevichkazimir.tryingmod.entity;

import net.yurkevichkazimir.tryingmod.tryingMod;
import net.yurkevichkazimir.tryingmod.entity.custom.KamizelkaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, tryingMod.MOD_ID);

    public static final RegistryObject<EntityType<KamizelkaEntity>> KAMIZELKA =
            ENTITY_TYPES.register("kamizelka", () -> EntityType.Builder.of(KamizelkaEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1.1f).build("kamizelka"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}