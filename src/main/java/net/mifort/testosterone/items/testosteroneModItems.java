package net.mifort.testosterone.items;

import static net.mifort.testosterone.testosterone.REGISTRATE;

import java.util.List;

import com.simibubi.create.Create;
import com.simibubi.create.content.logistics.box.PackageItem;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.items.custom.CheeseOnAStick;
import net.mifort.testosterone.items.custom.beerMug;
import net.mifort.testosterone.items.custom.shotItem;
import net.mifort.testosterone.items.trinkets.tie;
import net.mifort.testosterone.packages.TestosteronePackageStyles;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;

public class testosteroneModItems {

	public static final List<ItemEntry<PackageItem>> ALL_TESTOSTERONE_PILL_BOXES =
			TestosteronePackageStyles.TESTOSTERONE_PILL_STYLES.stream()
					.map(style ->
							REGISTRATE.item(style.getItemId().getPath(), props -> new PackageItem(props, style))
									.properties(p -> p.stacksTo(1))
									.model((ctx, prov) -> {})
									.setData(ProviderType.LANG, (prov, entry) -> {})
									.register()
					)
					.toList();

	public static final List<ItemEntry<PackageItem>> ALL_TRENBOLONE_BOXES =
			TestosteronePackageStyles.TRENBOLONE_VIAL_STYLES.stream()
					.map(style ->
							REGISTRATE.item(style.getItemId().getPath(), props -> new PackageItem(props, style))
									.properties(p -> p.stacksTo(1))
									.model((ctx, prov) -> {})
									.setData(ProviderType.LANG, (prov, entry) -> {})
									.register()
					)
					.toList();

	public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_ANDESITE_ALLOY =
			sequencedIngredient("incomplete_andesite_alloy");

	private static ItemEntry<SequencedAssemblyItem> sequencedIngredient(String name) {
		return REGISTRATE.item(name, SequencedAssemblyItem::new)
				.register();
	}

	public static final ItemEntry<Item> TESTOSTERONE_PILL =
			REGISTRATE.item("testosterone_pill", Item::new)
					.properties(p -> p
							.food(testosteroneModFoods.TESTOSTERONE_PILL)
							.rarity(Rarity.RARE)
							.stacksTo(16))
					.register();

	public static final ItemEntry<Item> TESTOSTERONE_PROTEIN_BAR =
			REGISTRATE.item("testosterone_protein_bar", Item::new)
					.properties(p -> p
							.food(testosteroneModFoods.TESTOSTERONE_PROTEIN_BAR)
							.rarity(Rarity.RARE))
					.tag(TagKey.create(
							Registries.ITEM,
							new ResourceLocation("c", "cookies")
					))
					.register();

	public static final ItemEntry<shotItem> TESTOSTERONE_SHOT =
			REGISTRATE.item(
							"testosterone_shot",
							properties -> new shotItem(
									properties,
									testosteroneModEffects.TESTOSTERONE_EFFECT,
									1
							))
					.properties(p -> p
							.food(testosteroneModFoods.BLANK)
							.rarity(Rarity.EPIC)
							.stacksTo(16))
					.register();

	public static final ItemEntry<shotItem> TRENBOLONE_SHOT =
			REGISTRATE.item(
							"trenbolone_shot",
							properties -> new shotItem(
									properties,
									testosteroneModEffects.ROID_RAGE_EFFECT
							))
					.properties(p -> p
							.food(testosteroneModFoods.BLANK)
							.rarity(Rarity.EPIC)
							.stacksTo(16))
					.register();

	public static final ItemEntry<shotItem> BETTER_TRENBOLONE_SHOT =
			REGISTRATE.item(
							"better_trenbolone_shot",
							properties -> new shotItem(
									properties,
									testosteroneModEffects.ROID_RAGE_EFFECT,
									1,
									true
							))
					.properties(p -> p
							.food(testosteroneModFoods.BLANK)
							.rarity(Rarity.EPIC)
							.stacksTo(16))
					.register();

	public static final ItemEntry<beerMug> BEER_MUG =
			REGISTRATE.item("beer_mug", beerMug::new)
					.properties(p -> p
							.food(testosteroneModFoods.BLANK)
							.stacksTo(16))
					.register();

	public static final ItemEntry<tie> TIE =
			REGISTRATE.item("tie", tie::new)
					.tag(TagKey.create(Registries.ITEM, new ResourceLocation("trinkets", "neck/necklace")))
					.register();

	public static final ItemEntry<Item> AFTERLIFE_TOTEM =
			REGISTRATE.item("totem_of_afterlife", Item::new)
					.properties(p -> p
							.rarity(Rarity.EPIC)
							.stacksTo(1))
					.register();

	public static final ItemEntry<CheeseOnAStick> CHEESE_ON_A_STICK =
			REGISTRATE.item("cheese_on_a_stick", CheeseOnAStick::new)
					.properties(p -> p
							.stacksTo(1)
							.defaultDurability(256))
					.model((ctx, prov) ->
							prov.withExistingParent(
											ctx.getName(),
											prov.mcLoc("item/handheld_rod")
									)
									.texture(
											"layer0",
											prov.modLoc("item/" + ctx.getName())
									)
					)
					.register();

	public static final ItemEntry<Item> CHEESE_CURDS =
			REGISTRATE.item("cheese_curds", Item::new)
					.properties(p -> p
							.stacksTo(64)
							.food(testosteroneModFoods.CHEESE_CURDS))
					.register();

	public static final ItemEntry<Item> WHEY_PROTEIN =
			REGISTRATE.item("whey_protein", Item::new)
					.properties(p -> p.stacksTo(64))
					.tag(TagKey.create(
							Registries.ITEM,
							new ResourceLocation(
									Create.ID,
									"blaze_burner_fuel/regular"
							)
					))
					.register();

	public static final ItemEntry<Item> RAT_FUR =
			REGISTRATE.item("rat_fur", Item::new)
					.properties(p -> p.stacksTo(64))
					.register();

	public static final ItemEntry<SpawnEggItem> STUPID_RAT_SPAWN_EGG =
			REGISTRATE.item(
							"stupid_rat_spawn_egg",
							properties -> new SpawnEggItem(
									testosteroneEntities.RAT,
									0xFFFFFF,
									0xFFFFFF,
									properties
							))
					.register();

	public static void register() {

	}
}
