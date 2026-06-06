package net.mifort.testosterone.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import net.mifort.testosterone.blocks.testosteroneModBlocks;


import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class jeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new decantationCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<decantation> decantations = recipeManager
                .getAllRecipesFor(testosteroneModRecipes.DECANTATION.getType())
                .stream()
                .map(RecipeHolder::value)
                .map(r -> (decantation)(Object) r)
                .collect(Collectors.toList());
        registration.addRecipes(decantationCategory.DECANTATION_RECIPE_TYPE, decantations);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        IModPlugin.super.registerRecipeCatalysts(registration);

        registration.addRecipeCatalyst(testosteroneModBlocks.DECANTER_CENTRIFUGE.asStack(), decantationCategory.DECANTATION_RECIPE_TYPE);
    }
}