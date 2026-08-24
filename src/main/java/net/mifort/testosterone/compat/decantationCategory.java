package net.mifort.testosterone.compat;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;

import mezz.jei.api.fabric.constants.FabricTypes;
import mezz.jei.api.fabric.ingredients.fluids.IJeiFluidIngredient;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.testosterone;

import java.util.Optional;

public class decantationCategory implements IRecipeCategory<decantation> {

	public static final ResourceLocation UID = new ResourceLocation(testosterone.MOD_ID, "decantation");

	public static final ResourceLocation TEXTURE = new ResourceLocation(testosterone.MOD_ID, "textures/gui/decantation_jei.png");

	public static final RecipeType<decantation> DECANTATION_RECIPE_TYPE = new RecipeType<>(UID, decantation.class);

	private final IDrawable background;
	private final IDrawable icon;
	private final AnimatedDecantation animation = new AnimatedDecantation();

	int xOffset = 0;
	int yOffset = -7;

	public decantationCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 77 + yOffset);

		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(testosteroneModBlocks.DECANTER_CENTRIFUGE));
	}

	@Override
	public @NotNull RecipeType<decantation> getRecipeType() {
		return DECANTATION_RECIPE_TYPE;
	}

	@Override
	public @NotNull Component getTitle() {
		return Component.translatable(
				"category.testosterone.decantation"
		);
	}

	@SuppressWarnings("removal")
	@Override
	public @NotNull IDrawable getBackground() {
		return this.background;
	}

	@Override
	public @NotNull IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, decantation recipe, @NotNull IFocusGroup focuses) {
		long dropletsPerMb = net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants.BUCKET / 1000L;

		FluidStack input = new FluidStack(
				recipe.getFluidIngredients()
						.get(0)
						.getMatchingFluidStacks()
						.get(0),

				recipe.getFluidIngredients()
						.get(0)
						.getRequiredAmount() * dropletsPerMb
		);

		builder.addSlot(RecipeIngredientRole.INPUT, 28 + xOffset, 45 + yOffset)
				.setFluidRenderer(1, false, 14, 4)
				.addIngredient(FabricTypes.FLUID_STACK, toJeiFluidIngredient(input));

		FluidStack output = recipe.getFluidResults().get(0).copy();
		output.setAmount((int) (output.getAmount() * dropletsPerMb));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 134 + xOffset, 68 + yOffset)
				.setFluidRenderer(1, false, 14, 4)
				.addIngredient(FabricTypes.FLUID_STACK, toJeiFluidIngredient(output));
	}

	private IJeiFluidIngredient toJeiFluidIngredient(FluidStack stack) {
		return new IJeiFluidIngredient() {

			@Override
			public net.minecraft.world.level.material.Fluid getFluid() {
				return stack.getFluid();
			}

			@Override
			public long getAmount() {
				return stack.getAmount();
			}

			@Override
			public Optional<CompoundTag> getTag() {
				return Optional.ofNullable(stack.getTag());
			}
		};
	}

	@Override
	public void draw(decantation recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		try {
			animation.draw(graphics, 78 + xOffset, 40 + yOffset);
		} catch (Exception e) {
			// Ignore animation rendering errors
		}
	}
}
