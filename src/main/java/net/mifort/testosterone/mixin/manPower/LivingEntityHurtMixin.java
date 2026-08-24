package net.mifort.testosterone.mixin.manPower;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;

import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.network.packet.hudS2CPacket;
import net.mifort.testosterone.network.testosteroneModMessages;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Tuple;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityHurtMixin {

	@Unique
	private static final String BEGIN_TICK_KEY = "testosterone:begin_tick";

	@Unique
	private static final String DAMAGE_TAKEN_KEY = "testosterone:damage_taken_key";

	@Unique
	private static final String END_OF_BLOCK_TICK_KEY = "testosterone:end_of_block_tick";

	@Unique
	private boolean testosterone$blockCurrentHit = false;

	@Inject(method = "hurt", at = @At("HEAD"))
	private void testosterone$onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity) (Object) this;

		testosterone$blockCurrentHit = false;

		if (!entity.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT) || entity.isBlocking()) {
			return;
		}

		if (!(entity.level() instanceof ServerLevel currentLevel)) {
			return;
		}

		MinecraftServer server = currentLevel.getServer();

		if (server == null) {
			return;
		}

		boolean hasTie = false;
		boolean matej = false;

		Optional<TrinketComponent> trinketComponent =
				TrinketsApi.getTrinketComponent(entity);

		if (trinketComponent.isPresent()) {

			List<Tuple<SlotReference, ItemStack>> equipped = trinketComponent.get().getEquipped(testosteroneModItems.TIE.get());

			if (!equipped.isEmpty()) {

				hasTie = true;

				matej = equipped.get(0).getB().getDisplayName().getString().equals("[matej]");
			}
		}


		long currentTick = server.overworld().getGameTime();

		MobEffectInstance testosteroneInstance = entity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);

		int amplifier = (testosteroneInstance != null ? testosteroneInstance.getAmplifier() : 0) + 1;

		int damageTaken = entity.getCustomData().getInt(DAMAGE_TAKEN_KEY);

		long endOfBlockTick = entity.getCustomData().getLong(END_OF_BLOCK_TICK_KEY);

		boolean genericKill = source.type().msgId().equals("genericKill");

		if (genericKill && !matej) {

		} else if (currentTick < endOfBlockTick) {

			damageTaken += (int) amount;

			if (hasTie && damageTaken > 100) {
				damageTaken = 100;
			}

			entity.getCustomData().putInt(DAMAGE_TAKEN_KEY, damageTaken);

			if (entity instanceof ServerPlayer player && amount >= 100) {
				testosteroneAdvancementUtils.DAMAGE_TAKEN.trigger(player);
			}

			testosterone$blockCurrentHit = true;
		} else if (currentTick < endOfBlockTick + ((long) damageTaken * ConfigRegistry.TESTOSTERONE_MULTIPLIER.get()) / amplifier) {

		} else {
			endOfBlockTick = currentTick + ((long) ConfigRegistry.TESTOSTERONE_DURATION.get() * amplifier);

			entity.getCustomData().putLong(END_OF_BLOCK_TICK_KEY, endOfBlockTick);

			entity.getCustomData().putLong(BEGIN_TICK_KEY, currentTick);

			if (hasTie && amount > ConfigRegistry.TESTOSTERONE_MAX_DAMAGE.get()) {

				damageTaken = ConfigRegistry.TESTOSTERONE_MAX_DAMAGE.get();

			} else {

				damageTaken = (int) amount;
			}

			entity.getCustomData().putInt(DAMAGE_TAKEN_KEY, damageTaken);

			if (entity instanceof ServerPlayer player && amount >= 100) {
				testosteroneAdvancementUtils.DAMAGE_TAKEN.trigger(player);
			}

			testosterone$blockCurrentHit = true;
		}

		if (entity instanceof ServerPlayer player) {

			long endOfCoolDownTick = endOfBlockTick + ((long) damageTaken * ConfigRegistry.TESTOSTERONE_MULTIPLIER.get()) / amplifier;

			long[] toSend = {endOfCoolDownTick, entity.getCustomData().getLong(BEGIN_TICK_KEY), (long) ConfigRegistry.TESTOSTERONE_DURATION.get() * amplifier};

			testosteroneModMessages.sendToPlayer(new hudS2CPacket(toSend), player);
		}
	}

	@ModifyArg(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"), index = 1)
	private float testosterone$modifyActualHurtAmount(float amount) {
		if (testosterone$blockCurrentHit) {
			return 0.0F;
		}

		return amount;
	}
}
