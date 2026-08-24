package net.mifort.testosterone.events;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.mifort.testosterone.client.hudOverlay;

public class overlayRegistry {

	public static void register() {
		HudRenderCallback.EVENT.register((graphics, tickDelta) -> hudOverlay.render(graphics));
	}
}
