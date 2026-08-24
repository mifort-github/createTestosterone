package net.mifort.testosterone.compat;

import com.simibubi.create.compat.jei.ConversionRecipe;
import com.simibubi.create.compat.jei.category.MysteriousItemConversionCategory;

import net.fabricmc.loader.api.FabricLoader;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.world.item.ItemStack;

public class CreateJeiCompat {
	public static void register() {
		if (!FabricLoader.getInstance().isModLoaded("jei"))
			return;

		MysteriousItemConversionCategory.RECIPES.add(ConversionRecipe.create(new ItemStack(testosteroneModBlocks.CRACKED_PILLAR), new ItemStack(testosteroneModBlocks.JOHN_ROCK)));
	}
}
