package net.mifort.testosterone.config;


import com.simibubi.create.infrastructure.gui.CreateMainMenuScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class renderButton {
    @SubscribeEvent
    public static void onGuiInit(ScreenEvent.Init.Post event) {
        if (testosteroneConfigs.client().showButton.get()) {
            Screen gui = event.getScreen();
            if (gui instanceof CreateMainMenuScreen) {
                for (Object widget : gui.children()) {
                    if (widget instanceof Button button) {
                        if (button.getMessage().getContents() instanceof TranslatableContents translatableContents) {
                            if (translatableContents.getKey().equals("create.menu.configure")) {
                                event.addListener(new goToConfigButton(button.getX() + testosteroneConfigs.client().buttonXOffset.get(), button.getY() + testosteroneConfigs.client().buttonYOffset.get()));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
