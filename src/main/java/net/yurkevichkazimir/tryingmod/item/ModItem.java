package net.yurkevichkazimir.tryingmod.item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.item.custom.PigMakerItem;
import net.yurkevichkazimir.tryingmod.tryingMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, tryingMod.MOD_ID);

    public static final RegistryObject<Item> FLUPEL = ITEMS.register("flupel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAKAR = ITEMS.register("makar",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LYOSHA = ITEMS.register("lyosha",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KAZIK = ITEMS.register("kazik",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PUDDLE_OF_CUM = ITEMS.register("puddle_of_cum",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAKED_CUM = ITEMS.register("baked_cum",
                () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHUGUNOK = ITEMS.register("chugunok",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHUGUNOK_STICK = ITEMS.register("chugunok_stick",
            () -> new PigMakerItem(new Item.Properties().durability(10)));

    public static final RegistryObject<Item> PIG_MAKER_ITEM = ITEMS.register("pig_maker_item",
            () -> new PigMakerItem(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
