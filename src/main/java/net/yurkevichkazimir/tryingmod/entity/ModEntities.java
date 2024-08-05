package net.yurkevichkazimir.tryingmod.entity;

import net.yurkevichkazimir.tryingmod.entity.custom.BinhliEntity;
import net.yurkevichkazimir.tryingmod.entity.custom.ZopsikEntity;
import net.yurkevichkazimir.tryingmod.entity.custom.ZufikEntity;
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

    public static final RegistryObject<EntityType<ZufikEntity>> ZUFIK =
            ENTITY_TYPES.register("zufik", () -> EntityType.Builder.of(ZufikEntity::new, MobCategory.CREATURE)
                    .sized(1f, 3f).build("zufik"));

    public static final RegistryObject<EntityType<BinhliEntity>> BINHLI =
            ENTITY_TYPES.register("binhli", () -> EntityType.Builder.of(BinhliEntity::new, MobCategory.CREATURE)
                    .sized(1.2f, 2f).build("binhli"));

    public static final RegistryObject<EntityType<ZopsikEntity>> ZOPSIK =
            ENTITY_TYPES.register("zopsik", () -> EntityType.Builder.of(ZopsikEntity::new, MobCategory.CREATURE)
                    .sized(1.2f, 2f).build("zopsik"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}