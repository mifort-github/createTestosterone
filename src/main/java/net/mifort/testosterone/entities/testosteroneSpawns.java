package net.mifort.testosterone.entities;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.mifort.testosterone.testosterone;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class testosteroneSpawns {
	public static void register() {
		BiomeModifications.create(testosterone.rl("rat_spawn"))
				.add(ModificationPhase.ADDITIONS,
						BiomeSelectors.tag(BiomeTags.IS_FOREST),
						ctx -> ctx.getSpawnSettings().addSpawn(
								MobCategory.CREATURE,
								new MobSpawnSettings.SpawnerData(testosteroneEntities.RAT.get(), 5, 2, 5)));
	}
}
