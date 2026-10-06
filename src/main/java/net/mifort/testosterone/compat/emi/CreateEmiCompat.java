package net.mifort.testosterone.compat.emi;

import com.simibubi.create.compat.emi.recipes.ConversionRecipe;
import com.simibubi.create.compat.emi.recipes.MysteriousConversionEmiRecipe;

import net.fabricmc.loader.api.FabricLoader;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.world.item.ItemStack;

public class CreateEmiCompat {
	public static void register() {
		if (!FabricLoader.getInstance().isModLoaded("emi"))
			return;

		MysteriousConversionEmiRecipe.RECIPES.add(ConversionRecipe.create(new ItemStack(testosteroneModBlocks.CRACKED_PILLAR), new ItemStack(testosteroneModBlocks.JOHN_ROCK)));
	}
}
