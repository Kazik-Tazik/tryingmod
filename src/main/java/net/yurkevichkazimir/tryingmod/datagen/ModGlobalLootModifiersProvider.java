package net.yurkevichkazimir.tryingmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
        add("zopsik_eye_from_zopsik", new AddItemModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(new ResourceLocation("entities/zopsikentity")).build() }, ModItem.FRACTAL_GRASS.get()));
    }
}
