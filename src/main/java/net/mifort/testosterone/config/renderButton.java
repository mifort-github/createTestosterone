package net.mifort.testosterone.config;


import com.simibubi.create.infrastructure.gui.CreateMainMenuScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class renderButton {
    @SubscribeEvent
    public static void onGuiInit(ScreenEvent.Init event) {
        if (ConfigRegistry.SHOW_BUTTON.get()) {
            Screen gui = event.getScreen();
            if (gui instanceof CreateMainMenuScreen) {
                for (Object widget : gui.children()) {
                    if (widget instanceof Button button) {
                        if (button.getMessage().getContents() instanceof TranslatableContents translatableContents) {
                            if (translatableContents.getKey().equals("create.menu.configure")) {
                                event.addListener(new goToConfigButton(button.getX() + ConfigRegistry.BUTTON_X_OFFSET.get(), button.getY() + ConfigRegistry.BUTTON_Y_OFFSET.get()));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
