package net.mifort.testosterone.entities.rat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;

public final class ratEntityClient {

	private ratEntityClient() {

	}

	public static void playRollingSound(ratEntity rat) {
		SoundManager soundManager = Minecraft.getInstance().getSoundManager();

		if (rat.getRollingSound() != null && rat.isBoosting()) {
			if (!soundManager.isActive(rat.getRollingSound())) {
				soundManager.play(rat.getRollingSound());
			}
		} else {
			if (rat.getRollingSound() != null && soundManager.isActive(rat.getRollingSound())) {
				soundManager.stop(rat.getRollingSound());
			}
		}
	}
}
