package net.mifort.testosterone.compat;

import java.util.List;

import mezz.jei.api.IModPlugin;

import org.jetbrains.annotations.NotNull;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class jeiPlugin implements IModPlugin {
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return new ResourceLocation(testosterone.MOD_ID, "jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new decantationCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (Minecraft.getInstance().level == null) {
			return;
		}

		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

		List<decantation> decantations = recipeManager.getAllRecipesFor(testosteroneModRecipes.DECANTATION.getType());
		registration.addRecipes(decantationCategory.DECANTATION_RECIPE_TYPE, decantations);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		IModPlugin.super.registerRecipeCatalysts(registration);

		registration.addRecipeCatalyst(new ItemStack(testosteroneModBlocks.DECANTER_CENTRIFUGE), decantationCategory.DECANTATION_RECIPE_TYPE);
	}
}
