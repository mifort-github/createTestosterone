package net.mifort.testosterone.effects;

import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class testosteroneModEffects {

	public static final MobEffect TESTOSTERONE_EFFECT = Registry.register(
			BuiltInRegistries.MOB_EFFECT,
			new ResourceLocation(testosterone.MOD_ID, "man_power"),
			new testosteroneEffect()
	);

	public static final MobEffect AFTERLIFE_EFFECT = Registry.register(
			BuiltInRegistries.MOB_EFFECT,
			new ResourceLocation(testosterone.MOD_ID, "afterlife"),
			new afterlifeEffect()
	);

	public static final MobEffect ROID_RAGE_EFFECT = Registry.register(
			BuiltInRegistries.MOB_EFFECT,
			new ResourceLocation(testosterone.MOD_ID, "roid_rage"),
			new roidRageEffect()
	);

	public static void registerEffects() {

	}
}
