package net.mifort.testosterone.network.packet;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public record effectCheckerC2SPacket(int livingEntityID) {

	public effectCheckerC2SPacket(FriendlyByteBuf buf) {
		this(buf.readInt());
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(livingEntityID);
	}

	public void handle(ServerPlayer player) {
		if (player == null) return;

		LivingEntity livingEntity = (LivingEntity) player.level().getEntity(livingEntityID);
		if (livingEntity == null) return;

		MobEffectInstance testosterone = livingEntity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);
		MobEffectInstance roidRage = livingEntity.getEffect(testosteroneModEffects.ROID_RAGE_EFFECT);

		boolean hasTestosterone = testosterone != null;
		boolean hasRoidRage = roidRage != null;

		int effectInt;
		if (hasTestosterone && hasRoidRage) {
			int testosteroneScore = (testosterone.getAmplifier() + 1) * testosterone.getDuration();
			int roidRageScore = (roidRage.getAmplifier() + 1) * roidRage.getDuration();
			effectInt = testosteroneScore > roidRageScore ? 1 : 2;
		} else if (hasTestosterone) {
			effectInt = 1;
		} else if (hasRoidRage) {
			effectInt = 2;
		} else {
			effectInt = 0;
		}

		int[] nums = {livingEntityID, effectInt};
		testosteroneModMessages.sendToPlayer(new effectCheckerS2CPacket(nums), player);
	}
}
