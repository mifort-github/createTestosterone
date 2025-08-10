package net.mifort.testosterone.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.mifort.testosterone.recipes.centrifugingRecipe;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;


import java.util.List;

@JeiPlugin
public class jeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(testosterone.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new centrifugingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<centrifugingRecipe> polishingRecipes = recipeManager.getAllRecipesFor(testosteroneModRecipes.CENTRIFUGING.getType());
        registration.addRecipes(centrifugingCategory.CENTRIFUGING_RECIPE_TYPE, polishingRecipes);
    }
}