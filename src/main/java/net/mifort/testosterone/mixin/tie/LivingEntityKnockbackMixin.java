package net.mifort.testosterone.mixin.tie;

import dev.emi.trinkets.api.TrinketsApi;

import net.minecraft.world.entity.LivingEntity;
import net.mifort.testosterone.items.testosteroneModItems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityKnockbackMixin {

	@Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
	private void testosterone$cancelKnockbackWithTie(double strength, double x, double z, CallbackInfo ci) {
		LivingEntity self = (LivingEntity) (Object) this;

		TrinketsApi.getTrinketComponent(self).ifPresent(trinketComponent -> {
			if (!trinketComponent.getEquipped(testosteroneModItems.TIE.get()).isEmpty()) {
				ci.cancel();
			}
		});
	}
}
