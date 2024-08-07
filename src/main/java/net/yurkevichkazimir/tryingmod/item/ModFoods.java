package net.yurkevichkazimir.tryingmod.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties DRANIKI = new FoodProperties.Builder().nutrition(5).alwaysEat().saturationMod(1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200), 0.5f).build();

    public static final FoodProperties FRACTAL_GRASS = new FoodProperties.Builder().nutrition(4).alwaysEat().saturationMod(1)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200), 0.1f).build();
}
