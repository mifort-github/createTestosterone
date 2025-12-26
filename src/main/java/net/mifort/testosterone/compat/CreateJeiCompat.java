package net.mifort.testosterone.compat;

import com.simibubi.create.compat.jei.ConversionRecipe;
import com.simibubi.create.compat.jei.category.MysteriousItemConversionCategory;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraftforge.fml.ModList;

public class CreateJeiCompat {
    public static void register() {
        if (!ModList.get().isLoaded("jei"))
            return;

        MysteriousItemConversionCategory.RECIPES.add(
            ConversionRecipe.create(
                testosteroneModBlocks.CRACKED_PILLAR.asStack(),
                testosteroneModBlocks.JOHN_ROCK.asStack()
            )
        );
    }
}
