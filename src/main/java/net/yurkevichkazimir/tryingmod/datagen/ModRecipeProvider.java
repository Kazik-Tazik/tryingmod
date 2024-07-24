package net.yurkevichkazimir.tryingmod.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.yurkevichkazimir.tryingmod.block.ModBlocks;
import net.yurkevichkazimir.tryingmod.item.ModItem;
import net.yurkevichkazimir.tryingmod.tryingMod;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //BLOCKS

        //Chugunok block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHUGUNOK_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItem.CHUGUNOK.get())
                .unlockedBy(getHasName(ModItem.CHUGUNOK.get()), has(ModItem.CHUGUNOK.get()))
                .save(pWriter);

        //Pork block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PORK_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', Items.PORKCHOP)
                .unlockedBy(getHasName(Items.PORKCHOP), has(Items.PORKCHOP))
                .save(pWriter);


        //Cooked pork block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COOKED_PORK_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', Items.COOKED_PORKCHOP)
                .unlockedBy(getHasName(Items.COOKED_PORKCHOP), has(Items.COOKED_PORKCHOP))
                .save(pWriter);

        //ITEMS

        //Chugunok from chugunok block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.CHUGUNOK.get(), 9)
                .requires(ModBlocks.CHUGUNOK_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CHUGUNOK_BLOCK.get()), has(ModBlocks.CHUGUNOK_BLOCK.get()))
                .save(pWriter);

        //Chugunok stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CHUGUNOK_STICK.get())
                .pattern("#")
                .pattern("#")
                .define('#', ModItem.CHUGUNOK.get())
                .unlockedBy(getHasName(ModItem.CHUGUNOK.get()), has(ModItem.CHUGUNOK.get()))
                .save(pWriter);

        //Cooked pork
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COOKED_PORKCHOP, 9)
                .requires(ModBlocks.COOKED_PORK_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.COOKED_PORK_BLOCK.get()), has(ModBlocks.COOKED_PORK_BLOCK.get()))
                .save(pWriter);

        //Pork
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PORKCHOP, 9)
                .requires(ModBlocks.PORK_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PORK_BLOCK.get()), has(ModBlocks.PORK_BLOCK.get()))
                .save(pWriter);

        //Draniki
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.DRANIKI.get())
                .pattern("##")
                .pattern("##")
                .define('#', Items.BAKED_POTATO)
                .unlockedBy(getHasName(Items.BAKED_POTATO), has(Items.BAKED_POTATO))
                .save(pWriter);

        //Pig Maker Item
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.PIG_MAKER_ITEM.get())
                .pattern(" GO")
                .pattern(" SG")
                .pattern("S  ")
                .define('G', Items.PORKCHOP)
                .define('O', Items.NETHER_STAR)
                .define('S', ModItem.CHUGUNOK_STICK.get())
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(pWriter);

    }


    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, tryingMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
