package net.mifort.testosterone.recipes;

import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.CreateLang;

import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public enum testosteroneModRecipes implements IRecipeTypeInfo {
	DECANTATION(decantation::new);

	private final ResourceLocation id;
	private final RecipeSerializer<?> serializer;
	private final RecipeType<?> type;

	testosteroneModRecipes(Supplier<RecipeSerializer<?>> serializerSupplier) {
		String name = CreateLang.asId(name());
		id = testosterone.rl(name);
		serializer = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id, serializerSupplier.get());
		type = Registry.register(BuiltInRegistries.RECIPE_TYPE, id, new RecipeType<>() {
			@Override
			public String toString() {
				return id.toString();
			}
		});
	}

	testosteroneModRecipes(ProcessingRecipeBuilder.ProcessingRecipeFactory<?> processingFactory) {
		this(() -> new ProcessingRecipeSerializer<>(processingFactory));
	}

	public static void register() {
		values();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends RecipeSerializer<?>> T getSerializer() {
		return (T) serializer;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends RecipeType<?>> T getType() {
		return (T) type;
	}
}
