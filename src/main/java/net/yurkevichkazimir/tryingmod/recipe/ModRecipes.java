package net.yurkevichkazimir.tryingmod.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, tryingMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PotatoExplosionMakerRecipe>> POTATO_EXPLOSION_MAKER_SERIALIZER =
            SERIALIZERS.register("potato_explosion_maker", () -> PotatoExplosionMakerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}