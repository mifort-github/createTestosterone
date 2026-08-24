package net.mifort.testosterone.mixin.roidRage;

import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.effects.roidRageEffect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDeathMixin {

    @Inject(method = "die", at = @At("HEAD"))
    private void testosterone$onDeath(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        Level level = self.level();

        if (level.isClientSide) {
            return;
        }

        if (level.getGameTime() - self.getCustomData().getLong(roidRageEffect.MARKED_KEY) >= 20) {
            return;
        }

        Player player = level.getPlayerByUUID(self.getCustomData().getUUID(roidRageEffect.MARKED_BY_KEY));

        if (player instanceof ServerPlayer serverPlayer) {
            testosteroneAdvancementUtils.ROADKILL.trigger(serverPlayer);
        }
    }
}
