package net.mifort.testosterone.config;

import com.simibubi.create.foundation.gui.ScreenOpener;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class goToConfigButton extends Button {
    public static final ItemStack ICON = testosteroneModItems.TESTOSTERONE_PILL.asStack();

    public goToConfigButton(int x, int y) {
        super(x, y, 20, 20, Component.empty(), goToConfigButton::click, DEFAULT_NARRATION);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        graphics.renderItem(ICON, this.getX() + 2, this.getY() + 2);
    }

    public static void click(Button button) {
        ScreenOpener.open(new testosteroneScreen(Minecraft.getInstance().screen, testosterone.MOD_ID));
    }
}
