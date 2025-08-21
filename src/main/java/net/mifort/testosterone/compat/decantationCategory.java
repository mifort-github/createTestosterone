package net.mifort.testosterone.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class decantationCategory implements IRecipeCategory<decantation> {
    public static final ResourceLocation UID = new ResourceLocation(testosterone.MOD_ID, "decantation");
    public static final ResourceLocation TEXTURE = new ResourceLocation(testosterone.MOD_ID,
            "textures/gui/decantation_jei.png");

    public static final RecipeType<decantation> DECANTATION_RECIPE_TYPE =
            new RecipeType<>(UID, decantation.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final AnimatedDecantation animation = new AnimatedDecantation();

    int xOffset = 0;
    int yOffset = -7;

    public decantationCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 77+yOffset);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(testosteroneModBlocks.DECANTER_CENTRIFUGE.get()));
    }

    @Override
    public @NotNull RecipeType<decantation> getRecipeType() {
        return DECANTATION_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() { return Component.translatable("category.testosterone.decantation"); }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, decantation recipe, @NotNull IFocusGroup focuses) {
        FluidStack input = new FluidStack(recipe.getFluidIngredients().get(0).getMatchingFluidStacks().get(0), recipe.getFluidIngredients().get(0).getRequiredAmount());

        builder.addSlot(RecipeIngredientRole.INPUT, 28+xOffset, 45+yOffset)
                .setFluidRenderer(1, false, 14, 4) // ui tank capacity, show capacity, width, height
                .addIngredient(ForgeTypes.FLUID_STACK, input);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 134+xOffset, 68+yOffset)
                .setFluidRenderer(1, false, 14, 4) // ui tank capacity, show capacity, width, height
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidResults().get(0));
    }
    @Override
    public void draw(decantation recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        if (animation != null) {
            try {
                animation.draw(graphics, 78+xOffset, 40+yOffset);
            } catch (Exception e) {
                // Ne
            }
        }
    }
}