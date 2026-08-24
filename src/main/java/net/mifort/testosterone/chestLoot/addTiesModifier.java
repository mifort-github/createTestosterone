package net.mifort.testosterone.chestLoot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.mifort.testosterone.items.trinkets.tie;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;


public final class addTiesModifier {

	private static final float CHANCE = 0.1f;

	private addTiesModifier() {

	}

	public static void register() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (!source.isBuiltin()) {
				return;
			}
			if (!isTargetChestTable(id)) {
				return;
			}

			LootPool.Builder pool = LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1))
					.when(LootItemRandomChanceCondition.randomChance(CHANCE));

			for (DyeColor color : DyeColor.values()) {
				pool.add(LootItem.lootTableItem(tie.getTieByColor(color.getName().toLowerCase()).getItem()).setWeight(1));
			}

			tableBuilder.withPool(pool);
		});
	}
	private static boolean isTargetChestTable(ResourceLocation id) {
		return id.getNamespace().equals("minecraft") && id.getPath().startsWith("chests/");
	}
}
