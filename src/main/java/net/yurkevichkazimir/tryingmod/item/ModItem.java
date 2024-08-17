package net.yurkevichkazimir.tryingmod.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.entity.ModEntities;
import net.yurkevichkazimir.tryingmod.item.custom.*;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, tryingMod.MOD_ID);

    public static final RegistryObject<Item> CHUGUNOK = ITEMS.register("chugunok",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHUGUNOK_STICK = ITEMS.register("chugunok_stick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIG_MAKER_ITEM = ITEMS.register("pig_maker_item",
            () -> new PigMakerItem(new Item.Properties().durability(10)));

    public static final RegistryObject<Item> DRANIKI = ITEMS.register("draniki",
            () -> new Item(new Item.Properties().food(ModFoods.DRANIKI)));

    public static final RegistryObject<Item> FRACTAL_GRASS = ITEMS.register("fractal_grass",
            () -> new Item(new Item.Properties().food(ModFoods.FRACTAL_GRASS)));

    public static final RegistryObject<Item> TWENTY_FOUR_HOUR_FUEL = ITEMS.register("twenty_four_hour_fuel",
            () -> new FuelItem(new Item.Properties(), 1728000));

    public static final RegistryObject<Item> PIG_SEEDS = ITEMS.register("pig_seeds",
            () -> new ItemNameBlockItem(ModBlocks.PIG_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> KAMIZELKA_SPAWN_EGG = ITEMS.register("kamizelka_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KAMIZELKA, 0x58be3c, 0xc9be26,
                    new Item.Properties()));

    public static final RegistryObject<Item> ZUFIK_SPAWN_EGG = ITEMS.register("zufik_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ZUFIK, 0x791f7f, 0x2a0b2d,
                    new Item.Properties()));

    public static final RegistryObject<Item> BINHLI_SPAWN_EGG = ITEMS.register("binhli_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BINHLI, 0x784d19, 0x1a1105,
                    new Item.Properties()));

    public static final RegistryObject<Item> ZOPSIK_SPAWN_EGG = ITEMS.register("zopsik_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ZOPSIK, 0xffe100, 0x9b8909,
                    new Item.Properties()));

    public static final RegistryObject<Item> ZOPSIK_EYE = ITEMS.register("zopsik_eye",
            () -> new ZopsikEye(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> POTATO_PROJECTILE = ITEMS.register("potato_projectile",
            () -> new PotatoItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> CARROT_PROJECTILE = ITEMS.register("carrot_projectile",
            () -> new CarrotItem(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> HEALTH_WRITE_ITEM = ITEMS.register("health_write_item",
            () -> new HealthWriteItem(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
