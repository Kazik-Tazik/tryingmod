package net.yurkevichkazimir.tryingmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.yurkevichkazimir.tryingmod.item.ModItem;
import net.yurkevichkazimir.tryingmod.loot.AddItemModifier;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, tryingMod.MOD_ID);
    }


    @Override
    protected void start() {
        add("zufik_leg_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.7f).build()}, ModItem.ZUFIK_LEG.get()));

        add("zopsik_eye_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.5f).build()}, ModItem.ZOPSIK_EYE.get()));

        add("binhli_antenna_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.6f).build()}, ModItem.BINHLI_ANTENNA.get()));

        add("kamizelka_hat_from_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.9f).build()}, ModItem.KAMIZELKA_HAT.get()));
    }
}
