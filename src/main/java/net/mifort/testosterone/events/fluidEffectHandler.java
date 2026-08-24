package net.mifort.testosterone.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;

public class fluidEffectHandler {

	public static void register() {
		ServerTickEvents.END_WORLD_TICK.register(fluidEffectHandler::onWorldTick);
	}

	private static void onWorldTick(ServerLevel level) {
		for (Entity entity : level.getAllEntities()) {
			if (entity instanceof LivingEntity living) {
				applyPotionEffect(living);
			}
		}
	}

	private static void applyPotionEffect(LivingEntity entity) {
		FluidState fluidState = entity.level().getFluidState(entity.blockPosition());

		if (fluidState.getType() == testosteroneFluids.TESTOSTERONE_FLUID.get() || fluidState.getType() == testosteroneFluids.TESTOSTERONE_FLUID.getSource()) {

			entity.addEffect(new MobEffectInstance(
					testosteroneModEffects.TESTOSTERONE_EFFECT,
					20,
					0
			));
		}

		if (fluidState.getType() == testosteroneFluids.TRENBOLONE_FLUID.get() || fluidState.getType() == testosteroneFluids.TRENBOLONE_FLUID.getSource()) {

			entity.addEffect(new MobEffectInstance(
					testosteroneModEffects.ROID_RAGE_EFFECT,
					20,
					0
			));
		}
	}
}
