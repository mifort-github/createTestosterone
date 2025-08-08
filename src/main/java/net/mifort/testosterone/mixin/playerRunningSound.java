package net.mifort.testosterone.mixin;

import net.mifort.testosterone.effects.roidRageEffect;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.sounds.playerMach1Sound;
import net.mifort.testosterone.sounds.playerMach2Sound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO: try to remove the gap in sound

@Mixin(Player.class)
public abstract class playerRunningSound {
    @Unique
    playerMach1Sound createTestosterone$playerMach1Sound = new playerMach1Sound((Player) (Object) this);
    @Unique
    playerMach2Sound createTestosterone$playerMach2Sound = new playerMach2Sound((Player) (Object) this);

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void tick(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!player.level().isClientSide()) {
            SoundManager soundManager = Minecraft.getInstance().getSoundManager();

            if (player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT.get()) && player.isSprinting()) {
                if (!soundManager.isActive(createTestosterone$playerMach1Sound)) {
                    if (roidRageEffect.getSpeed(player) == 0) {
                        soundManager.play(createTestosterone$playerMach1Sound);

                    } else if (!soundManager.isActive(createTestosterone$playerMach2Sound)) {
                        soundManager.play(createTestosterone$playerMach2Sound);
                    }
                }

            } else {
                if (soundManager.isActive(createTestosterone$playerMach1Sound)) {
                    soundManager.stop(createTestosterone$playerMach1Sound);
                }

                if (soundManager.isActive(createTestosterone$playerMach2Sound)) {
                    soundManager.stop(createTestosterone$playerMach2Sound);
                }
            }
        }
    }
}