package net.mifort.testosterone.sounds;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;

public class playerMach1Sound extends AbstractTickableSoundInstance {
    private final Player player;

    public playerMach1Sound(Player player) {
        super(testosteroneModSounds.MACH_1_SFX.get(), SoundSource.PLAYERS, RandomSource.create());
        this.player = player;
        this.volume = 1f;
        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();
    }

    @Override
    public boolean canPlaySound() {
        return !this.player.isSilent();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if (this.player.isRemoved() || !this.player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT.get())) {
            this.stop();
        } else {
            this.x = player.getX();
            this.y = player.getY();
            this.z = player.getZ();
        }
    }
}
