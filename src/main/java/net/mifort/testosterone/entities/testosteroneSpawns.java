package net.mifort.testosterone.entities;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.mifort.testosterone.testosterone;

public class testosteroneSpawns {

    public static void register() {
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(BiomeTags.IS_FOREST),
                MobCategory.CREATURE,
                testosteroneEntities.RAT,
                5,
                2,
                5
        );
    }
}
