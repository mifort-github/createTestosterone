package net.mifort.testosterone.config;

import com.simibubi.create.infrastructure.gui.CreateMainMenuScreen;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.mifort.testosterone.mixin.ScreenAccessor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.contents.TranslatableContents;

public class renderButton {

	public static void register() {
		ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (!ConfigRegistry.SHOW_BUTTON.get()) return;

			if (screen instanceof CreateMainMenuScreen) {
				for (var widget : screen.children()) {
					if (widget instanceof Button button) {
						if (button.getMessage().getContents() instanceof TranslatableContents translatableContents) {
							if (translatableContents.getKey().equals("create.menu.configure")) {
								goToConfigButton newButton = new goToConfigButton(
										button.getX() + ConfigRegistry.BUTTON_X_OFFSET.get(),
										button.getY() + ConfigRegistry.BUTTON_Y_OFFSET.get()
								);
								((ScreenAccessor) screen).testosterone$addRenderableWidget(newButton);
								break;
							}
						}
					}
				}
			}
		});
	}
}
