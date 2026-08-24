package net.mifort.testosterone.items;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.Set;

public class addItemModifier {

	private static final Set<ResourceLocation> TARGET_TABLES = Set.of(
			new ResourceLocation("minecraft", "chests/buried_treasure"),
			new ResourceLocation("minecraft", "chests/woodland_mansion"),
			new ResourceLocation("minecraft", "chests/abandoned_mineshaft"),
			new ResourceLocation("minecraft", "chests/shipwreck_supply"),
			new ResourceLocation("minecraft", "chests/end_city_treasure"),
			new ResourceLocation("minecraft", "chests/ancient_city")
	);

	public static void register() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (TARGET_TABLES.contains(id)) {
				LootPool.Builder pool = LootPool.lootPool()
						.add(LootItem.lootTableItem(testosteroneModItems.TIE.get()))
						.when(LootItemRandomChanceCondition.randomChance(0.1f));

				tableBuilder.withPool(pool);
			}
		});
	}
}
