package net.yurkevichkazimir.tryingmod.entity;

import net.yurkevichkazimir.tryingmod.entity.custom.*;
import net.yurkevichkazimir.tryingmod.tryingMod;
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
                    .sized(1.2f, 2.7f).build("binhli"));

    public static final RegistryObject<EntityType<ZopsikEntity>> ZOPSIK =
            ENTITY_TYPES.register("zopsik", () -> EntityType.Builder.of(ZopsikEntity::new, MobCategory.CREATURE)
                    .sized(1.7f, 1.4375f).build("zopsik"));

    public static final RegistryObject<EntityType<PotatoProjectileEntity>> POTATO_PROJECTILE =
            ENTITY_TYPES.register("potato_projectile", () -> EntityType.Builder.<PotatoProjectileEntity>of(PotatoProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("potato_projectile"));

    public static final RegistryObject<EntityType<CarrotProjectileEntity>> CARROT_PROJECTILE =
            ENTITY_TYPES.register("carrot_projectile", () -> EntityType.Builder.<CarrotProjectileEntity>of(CarrotProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("carrot_projectile"));




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}