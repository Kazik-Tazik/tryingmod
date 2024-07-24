package net.yurkevichkazimir.tryingmod.item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.item.custom.FuelItem;
import net.yurkevichkazimir.tryingmod.item.custom.PigMakerItem;
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

    public static final RegistryObject<Item> TWENTY_FOUR_HOUR_FUEL = ITEMS.register("twenty_four_hour_fuel",
            () -> new FuelItem(new Item.Properties(), 1728000));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
