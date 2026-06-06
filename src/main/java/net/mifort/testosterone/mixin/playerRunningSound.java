package net.mifort.testosterone.mixin;

import net.mifort.testosterone.effects.roidRageEffect;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.sounds.playerMach1Sound;
import net.mifort.testosterone.sounds.playerMach2Sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayer.class)
public abstract class playerRunningSound {

    @Unique private playerMach1Sound testosterone$mach1;
    @Unique private playerMach2Sound testosterone$mach2;
    // TODO: fix bugs
    @Inject(method = "tick", at = @At("HEAD"), remap = false)
    private void testosterone$tick(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!player.level().isClientSide()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.player == null) return;

        if (player != mc.player) return;

        SoundManager soundManager = mc.getSoundManager();

        if (player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT) && player.isSprinting()) {
            if (testosterone$mach1 == null) testosterone$mach1 = new playerMach1Sound(player);
            if (testosterone$mach2 == null) testosterone$mach2 = new playerMach2Sound(player);

            if (!soundManager.isActive(testosterone$mach1)) {
                if (roidRageEffect.getSpeed(player) <= 1) {
                    soundManager.play(testosterone$mach1);
//                    player.sendSystemMessage(Component.literal("mach1"));
                } else if (!soundManager.isActive(testosterone$mach2)) {
//                    player.sendSystemMessage(Component.literal("mach2"));
                    soundManager.play(testosterone$mach2);
                }
            }
        } else {
            if (testosterone$mach1 != null && soundManager.isActive(testosterone$mach1)) {
//                player.sendSystemMessage(Component.literal("mach1 stop"));
                soundManager.stop(testosterone$mach1);
            }
            if (testosterone$mach2 != null && soundManager.isActive(testosterone$mach2)) {
//                player.sendSystemMessage(Component.literal("mach2 stop"));
                soundManager.stop(testosterone$mach2);
            }
        }
    }
}
