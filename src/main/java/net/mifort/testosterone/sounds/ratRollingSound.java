package net.mifort.testosterone.sounds;

import net.mifort.testosterone.entities.rat.ratEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class ratRollingSound extends AbstractTickableSoundInstance {
    private final ratEntity rat;

    public ratRollingSound(ratEntity rat) {
        super(testosteroneModSounds.RAT_RUN.get(), SoundSource.NEUTRAL, RandomSource.create());
        this.rat = rat;
        this.looping = true;
        this.delay = 0;
        this.volume = 1f;
        this.x = rat.getX();
        this.y = rat.getY();
        this.z = rat.getZ();
    }

    @Override
    public boolean canPlaySound() {
        return !this.rat.isSilent();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if (this.rat.isRemoved()) {
            this.stop();
        } else {
            this.x = rat.getX();
            this.y = rat.getY();
            this.z = rat.getZ();
        }
    }
}
