package net.mifort.testosterone.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.trinkets.tie;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class testosteroneModCreativeModTabs {

	public static final CreativeModeTab TESTOSTERONE_TAB = Registry.register(
			BuiltInRegistries.CREATIVE_MODE_TAB,
			new ResourceLocation(testosterone.MOD_ID, "testosterone_tab"),
			FabricItemGroup.builder()
					.icon(() -> new ItemStack(testosteroneModItems.TESTOSTERONE_PILL))
					.title(Component.literal("Testosterone"))

					.displayItems((parameters, output) -> {
						output.accept(testosteroneModItems.TESTOSTERONE_PILL);
						output.accept(testosteroneModItems.TESTOSTERONE_SHOT);
						output.accept(testosteroneModItems.TESTOSTERONE_PROTEIN_BAR);
						output.accept(testosteroneModItems.BEER_MUG);
						output.accept(testosteroneModItems.TRENBOLONE_SHOT);
						output.accept(testosteroneModItems.BETTER_TRENBOLONE_SHOT);
//                      output.accept(testosteroneModItems.AFTERLIFE_TOTEM);
						output.accept(testosteroneModBlocks.DECANTER_CENTRIFUGE);
						output.accept(testosteroneModBlocks.JOHN_ROCK);
						output.accept(testosteroneModBlocks.TESTOSTERONE_PILL_BLOCK);
						output.accept(testosteroneModBlocks.TRENBOLONE_VIAL);
						output.accept(testosteroneModBlocks.FRAGILE_COPYCAT_BLOCK);

						output.accept(testosteroneModBlocks.CHEESE_BLOCK);
						output.accept(addBooleanNbt(testosteroneModItems.CHEESE_ON_A_STICK.get().getDefaultInstance(), "Boost", false));
						output.accept(testosteroneModItems.WHEY_PROTEIN);
						output.accept(testosteroneModItems.CHEESE_CURDS);

						output.accept(testosteroneModItems.RAT_FUR);
						output.accept(testosteroneModItems.STUPID_RAT_SPAWN_EGG);

						//output.accept(testosteroneModItems.STRONGMAN_PATCH);

						output.accept(tippedArrow(testosteroneModPotions.TESTOSTERONE_POTION));
						output.accept(tippedArrow(testosteroneModPotions.ROID_RAGE_POTION));

						output.accept(testosteroneFluids.CHOLESTEROL_FLUID.getBucket().get());
						output.accept(testosteroneFluids.DILUTED_ZINC_FLUID.getBucket().get());
						output.accept(testosteroneFluids.TESTOSTERONE_FLUID.getBucket().get());
						output.accept(testosteroneFluids.TRENBOLONE_FLUID.getBucket().get());
						output.accept(testosteroneFluids.BEER_FLUID.getBucket().get());
						output.accept(testosteroneFluids.WHEY_FLUID.getBucket().get());
						output.accept(testosteroneFluids.CHEESE_FLUID.getBucket().get());
						output.accept(testosteroneFluids.ESTRONE_FLUID.getBucket().get());

						for (int pId = 0; pId < 16; pId++) {
							output.accept(tie.getTieByColor(DyeColor.byId(pId).name().toLowerCase()));
						}

						output.accept(testosteroneModBlocks.CRACKED_PILLAR);

						output.accept(testosteroneModBlocks.AEQUALIS);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS_STAIRS);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS_SLAB);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS_WALL);

						output.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS);
						output.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS_STAIRS);
						output.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS_SLAB);
						output.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS_WALL);

						output.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICKS);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICK_STAIRS);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICK_SLAB);
						output.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICK_WALL);

						output.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICKS);
						output.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICK_STAIRS);
						output.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICK_SLAB);
						output.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICK_WALL);
						output.accept(testosteroneModBlocks.LAYERED_AEQUALIS);
						output.accept(testosteroneModBlocks.AEQUALIS_PILLAR);

						output.accept(testosteroneModBlocks.SMOOTH_AEQUALIS);
						output.accept(testosteroneModBlocks.SMOOTH_DIAMOND_AEQUALIS);
						output.accept(testosteroneModBlocks.SMOOTH_DARK_DIAMOND_AEQUALIS);
						output.accept(testosteroneModBlocks.BIG_AEQUALIS_BRICKS);
					})
					.build());

	public static ItemStack tippedArrow(Potion potion) {
		ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
		PotionUtils.setPotion(stack, potion);
		return stack;
	}

	public static ItemStack addBooleanNbt(ItemStack itemStack, String key, boolean bool) {
		CompoundTag nbtData = new CompoundTag();
		nbtData.putBoolean(key, bool);
		itemStack.setTag(nbtData);
		return itemStack;
	}

	public static void register() {

	}
}
