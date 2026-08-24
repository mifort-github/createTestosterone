package net.mifort.testosterone.potions;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class testosteroneModPotions {

	public static final Potion TESTOSTERONE_POTION = Registry.register(
			BuiltInRegistries.POTION,
			new ResourceLocation(testosterone.MOD_ID, "man_power_potion"),
			new Potion(
					new MobEffectInstance(
							testosteroneModEffects.TESTOSTERONE_EFFECT,
							12000,
							0
					)
			)
	);

	public static final Potion ROID_RAGE_POTION = Registry.register(
			BuiltInRegistries.POTION,
			new ResourceLocation(testosterone.MOD_ID, "roid_rage_potion"),
			new Potion(
					new MobEffectInstance(
							testosteroneModEffects.ROID_RAGE_EFFECT,
							12000,
							0
					)
			)
	);

	public static void registerPotions() {

	}
}
