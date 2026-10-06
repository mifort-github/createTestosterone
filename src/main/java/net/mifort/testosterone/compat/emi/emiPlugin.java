package net.mifort.testosterone.compat.emi;

import java.util.List;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import static com.simibubi.create.compat.emi.CreateEmiPlugin.synthetic;

@EmiEntrypoint
public class emiPlugin implements EmiPlugin {
	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(decantationEmiCategory.DECANTATION_CATEGORY);

		registry.addWorkstation(
				decantationEmiCategory.DECANTATION_CATEGORY,
				EmiStack.of(testosteroneModBlocks.DECANTER_CENTRIFUGE)
		);

		if (Minecraft.getInstance().level == null) {
			return;
		}

		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
		List<decantation> decantations = recipeManager.getAllRecipesFor(testosteroneModRecipes.DECANTATION.getType());

		for (decantation recipe : decantations) {
			registry.addRecipe(new AnimatedEmiDecantation(
					decantationEmiCategory.DECANTATION_CATEGORY,
					recipe,
					176,
					70
			));
		}

		addCollision(registry, testosteroneFluids.TESTOSTERONE_FLUID.get(), testosteroneFluids.ESTRONE_FLUID.get(), testosteroneModBlocks.AEQUALIS);


		ResourceLocation liquidEstrogenId = new ResourceLocation("estrogen", "liquid_estrogen");
		Fluid liquidEstrogen = BuiltInRegistries.FLUID.get(liquidEstrogenId);

		if (liquidEstrogen != Fluids.EMPTY) {
			addCollision(registry, testosteroneFluids.TESTOSTERONE_FLUID.get(), liquidEstrogen, testosteroneModBlocks.AEQUALIS, "_estrogen");
		}
	}

	private void addCollision(EmiRegistry registry, Fluid fluid1, Fluid fluid2, Block outputBlock, String suffix) {
		EmiStack fluidStack1 = EmiStack.of(fluid1, FluidConstants.BUCKET);
		fluidStack1 = fluidStack1.setRemainder(fluidStack1);

		EmiStack fluidStack2 = EmiStack.of(fluid2, FluidConstants.BUCKET);

		fluidStack2 = fluidStack2.setRemainder(fluidStack2);

		EmiStack output = EmiStack.of(outputBlock);
		String blockName = BuiltInRegistries.BLOCK.getKey(outputBlock).getPath();

		registry.addRecipe(
				EmiWorldInteractionRecipe.builder()
						.id(synthetic("fluid_interaction/" + blockName + suffix))
						.leftInput(fluidStack1)
						.rightInput(fluidStack2, false)
						.output(output)
						.build()
		);
	}

	private void addCollision(EmiRegistry registry, Fluid fluid1, Fluid fluid2, Block outputBlock) {
		addCollision(registry, fluid1, fluid2, outputBlock, "");
	}
}
