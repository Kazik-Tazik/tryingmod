package net.yurkevichkazimir.tryingmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModBlocksEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, tryingMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<PotatoExplosionMakerEntity>> POTATO_EXPLOSION_MAKER_BE =
            BLOCK_ENTITIES.register("potato_explosion_maker_be", () ->
                    BlockEntityType.Builder.of(PotatoExplosionMakerEntity::new,
                            ModBlocks.POTATO_EXPLOSION_MAKER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
