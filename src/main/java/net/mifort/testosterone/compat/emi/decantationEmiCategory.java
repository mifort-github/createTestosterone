package net.mifort.testosterone.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.testosterone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class decantationEmiCategory {

    public static final ResourceLocation UID = new ResourceLocation(testosterone.MOD_ID, "decantation");

    public static final EmiRecipeCategory DECANTATION_CATEGORY = new EmiRecipeCategory(
            UID,
            EmiStack.of(new ItemStack(testosteroneModBlocks.DECANTER_CENTRIFUGE))
    );
}
