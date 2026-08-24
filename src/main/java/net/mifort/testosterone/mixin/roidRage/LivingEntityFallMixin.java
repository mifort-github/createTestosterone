package net.mifort.testosterone.mixin.roidRage;

import com.simibubi.create.foundation.damageTypes.CreateDamageSources;

import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.effects.roidRageEffect;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.sounds.testosteroneModSounds;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityFallMixin {

	@Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
	private void testosterone$onCauseFallDamage(float distance, float multiplier, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity self = (LivingEntity) (Object) this;

		if (!(self instanceof Player player)) {
			return;
		}

		if (!player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT)) {
			return;
		}

		if (roidRageEffect.isSwimming(player) && player.level() instanceof ServerLevel level) {
			level.playSound(null, player.blockPosition(), testosteroneModSounds.GROUND_SLAM_SFX, SoundSource.PLAYERS);

			float radius = (float) (distance / ConfigRegistry.FALL_DAMAGE_RADIUS.get());

			level.sendParticles(ParticleTypes.SPIT,
					player.getX(), player.getY(), player.getZ(),
					(int) (distance * 10),
					radius, 0, radius,
					1
			);

			AABB searchBox = player.getBoundingBox().inflate(radius);

			level.getEntitiesOfClass(LivingEntity.class, searchBox, livingEntity -> livingEntity != player)
					.forEach(livingEntity -> {
						if (player.distanceTo(livingEntity) < radius) {
							livingEntity.getCustomData().putLong(roidRageEffect.MARKED_KEY, level.getGameTime());
							livingEntity.getCustomData().putUUID(roidRageEffect.MARKED_BY_KEY, player.getUUID());

							livingEntity.hurt(CreateDamageSources.runOver(level, player), radius);

							livingEntity.addDeltaMovement(new Vec3(0, distance / 24, 0));
						}
					});
		}

		cir.setReturnValue(false);
	}
}
