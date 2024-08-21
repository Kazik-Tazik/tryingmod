package net.yurkevichkazimir.tryingmod.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.block.custom.PigCropBlock;
import net.yurkevichkazimir.tryingmod.block.custom.PotatoExplosionMaker;
import net.yurkevichkazimir.tryingmod.item.ModItem;
import net.yurkevichkazimir.tryingmod.tryingMod;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, tryingMod.MOD_ID);

    public static final RegistryObject<Block> CHUGUNOK_BLOCK = registerBlock("chugunok_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> CHUGUNOK_ORE = registerBlock("chugunok_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    public static final RegistryObject<Block> PORK_BLOCK = registerBlock("pork_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> PORK_STAIRS = registerBlock("pork_stairs",
            () -> new StairBlock(() -> ModBlocks.PORK_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> PORK_SLAB = registerBlock("pork_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> PORK_BUTTON = registerBlock("pork_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.SLIME_BLOCK),
                    BlockSetType.IRON, 10, true));

    public static final RegistryObject<Block> PORK_PRESSURE_PLATE = registerBlock("pork_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK),
                    BlockSetType.IRON));

    public static final RegistryObject<Block> PORK_FENCE = registerBlock("pork_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> PORK_FENCE_GATE = registerBlock("pork_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK),
                    SoundEvents.ANVIL_PLACE, SoundEvents.ANVIL_DESTROY));

    public static final RegistryObject<Block> PORK_WALL = registerBlock("pork_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> PORK_DOOR = registerBlock("pork_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK).noOcclusion(), BlockSetType.STONE));

    public static final RegistryObject<Block> PORK_TRAPDOOR = registerBlock("pork_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK).noOcclusion(), BlockSetType.STONE));

    public static final RegistryObject<Block> COOKED_PORK_BLOCK = registerBlock("cooked_pork_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.SLIME_BLOCK)));

    public static final RegistryObject<Block> PIG_CROP = BLOCKS.register("pig_crop",
            () -> new PigCropBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS).noOcclusion().noCollission()));

    public static final RegistryObject<Block> POTATO_EXPLOSION_MAKER = registerBlock("potato_explosion_maker",
            () -> new PotatoExplosionMaker(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> FRENCH_DIRT = registerBlock("french_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.MOSS)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
