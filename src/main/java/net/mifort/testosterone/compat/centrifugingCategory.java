package net.mifort.testosterone.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.recipes.centrifugingRecipe;
import net.mifort.testosterone.testosterone;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class centrifugingCategory implements IRecipeCategory<centrifugingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(testosterone.MOD_ID, "centrifuging");
    public static final ResourceLocation TEXTURE = new ResourceLocation(testosterone.MOD_ID,
            "textures/gui/centrifuging_jei.png");

    public static final RecipeType<centrifugingRecipe> CENTRIFUGING_RECIPE_TYPE =
            new RecipeType<>(UID, centrifugingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public centrifugingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(testosteroneModBlocks.TEST.get()));
    }

    @Override
    public @NotNull RecipeType<centrifugingRecipe> getRecipeType() {
        return CENTRIFUGING_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.testosterone.test");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, centrifugingRecipe recipe, @NotNull IFocusGroup focuses) {
        FluidStack input = new FluidStack(recipe.getFluidIngredients().get(0).getMatchingFluidStacks().get(0), recipe.getFluidIngredients().get(0).getRequiredAmount());

        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11)
                .setFluidRenderer(1, false, 16, 16) // ui tank capacity, show capacity, width, height
                .addIngredient(ForgeTypes.FLUID_STACK, input);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59)
                .setFluidRenderer(1, false, 16, 16) // ui tank capacity, show capacity, width, height
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluidResults().get(0));

    }
}