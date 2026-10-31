package net.mifort.testosterone.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import net.mifort.testosterone.entities.rat.ratEntity;
import net.mifort.testosterone.testosterone;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.rebar.concrete_lib.ConcreteLib;
import net.rebar.concrete_lib.lib.mobs.CLMobs;

public class testosteroneEntities {
	public static CLMobs MOBS = ConcreteLib.mobs(testosterone.MOD_ID);

	public static CLMobs.MobEntry<ratEntity> RAT =
			MOBS.mob("rat",
					EntityType.Builder.of(ratEntity::new, MobCategory.CREATURE).sized(1, 1),
					ratEntity::createAttributes);

	public static void register() {

	}
}
