package net.mifort.testosterone.compat.emi;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.emi.recipes.CreateEmiRecipe;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.api.widget.WidgetHolder;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlock;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

import static com.simibubi.create.compat.emi.CreateEmiAnimations.blockElement;
import static com.simibubi.create.compat.emi.CreateEmiAnimations.getCurrentAngle;
import static com.simibubi.create.compat.emi.CreateEmiAnimations.shaft;

public class AnimatedEmiDecantation extends CreateEmiRecipe<decantation> {

	private static final int ANIM_X = 78;
	private static final int ANIM_Y = 31;

	public static final ResourceLocation TEXTURE = new ResourceLocation(testosterone.MOD_ID, "textures/gui/decantation_jei.png");

	private final EmiStack input;
	private final EmiStack output;

	public AnimatedEmiDecantation(EmiRecipeCategory category, decantation recipe, int width, int height) {
		super(category, recipe, width, height);

		long dropletsPerMb = FluidConstants.BUCKET / 1000;

		var inputFluidStack = recipe.getFluidIngredients().get(0).getMatchingFluidStacks().get(0);
		long inputAmount = recipe.getFluidIngredients().get(0).getRequiredAmount() * dropletsPerMb;

		var outputFluidStack = recipe.getFluidResults().get(0).copy();
		long outputAmount = outputFluidStack.getAmount() * dropletsPerMb;

		this.input = EmiStack.of(inputFluidStack.getFluid(),
				inputFluidStack.getTag() != null ? inputFluidStack.getTag() : new CompoundTag(), inputAmount);
		this.output = EmiStack.of(outputFluidStack.getFluid(),
				outputFluidStack.getTag() != null ? outputFluidStack.getTag() : new CompoundTag(), outputAmount);
	}

	@Override
	public List<EmiIngredient> getInputs() {
		return List.of(input);
	}

	@Override
	public List<EmiStack> getOutputs() {
		return List.of(output);
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		widgets.addTexture(TEXTURE, 0, 0, 176, 70, 0, 0);

		widgets.addTank(input, 27, 38, 16, 6, (int) input.getAmount()).drawBack(false);
		widgets.addTank(output, 133, 60, 16, 6, (int) output.getAmount()).drawBack(false).recipeContext(this);


		widgets.add(new Widget() {
			@Override
			public Bounds getBounds() {
				return new Bounds(ANIM_X, ANIM_Y, 1, 1);
			}

			@Override
			public void render(GuiGraphics draw, int mouseX, int mouseY, float delta) {
				PoseStack matrixStack = draw.pose();
				matrixStack.pushPose();
				matrixStack.translate(ANIM_X, ANIM_Y, 0);
				matrixStack.translate(0, 0, 200);
				matrixStack.translate(2, 22, 0);
				matrixStack.mulPose(Axis.XP.rotationDegrees(-15.5f));
				matrixStack.mulPose(Axis.YP.rotationDegrees(22.5f + 90));
				int scale = 25;

				blockElement(shaft(Direction.Axis.X))
						.rotateBlock(-getCurrentAngle(), 0, 90)
						.scale(scale)
						.render(draw);

				blockElement(testosteroneModBlocks.DECANTER_CENTRIFUGE.getDefaultState()
						.setValue(decanterCentrifugeBlock.HORIZONTAL_FACING, Direction.NORTH))
						.rotateBlock(180, 0, 0)
						.scale(scale)
						.render(draw);

				matrixStack.popPose();
			}
		});
	}
}
