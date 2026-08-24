package net.mifort.testosterone.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import net.mifort.testosterone.entities.rat.ratEntity;
import net.mifort.testosterone.testosterone;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class testosteroneEntities {

	public static final EntityType<ratEntity> RAT = Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(testosterone.MOD_ID, "rat"),
			EntityType.Builder
					.of(ratEntity::new, MobCategory.CREATURE)
					.sized(1.0F, 1.0F)
					.build("rat")
	);

	public static void registerEntities() {
		FabricDefaultAttributeRegistry.register(RAT, ratEntity.createAttributes());
	}
}
