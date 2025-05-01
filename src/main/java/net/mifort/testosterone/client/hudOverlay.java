package net.mifort.testosterone.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class hudOverlay {
    public static final String END_OF_COOLDOWN_TICK_KEY = "testosterone:end_of_cooldown_tick_key";
    public static final String ACTUAL_BEGIN_TICK_KEY = "testosterone:actual_begin_tick_key";
    public static final String BEGIN_TICK_KEY = "testosterone:begin_tick_key";
    public static final String DURATION_KEY = "testosterone:duration_key";

    public static final float ALPHA_MULTIPLIER = 0.5f;
    public static final float ALPHA_BASE = 0.3f;

    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(testosterone.MOD_ID,
            "textures/overlay/overlay.png");

    public static final IGuiOverlay OVERLAY = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        LocalPlayer player = Minecraft.getInstance().player;

        long endOfCooldownTick = player.getPersistentData().getLong(END_OF_COOLDOWN_TICK_KEY);
        long actualBeginTick = player.getPersistentData().getLong(ACTUAL_BEGIN_TICK_KEY);
        long beginTick = player.getPersistentData().getLong(BEGIN_TICK_KEY);
        long duration = player.getPersistentData().getLong(DURATION_KEY);

        long currentTick = Minecraft.getInstance().level.getGameTime();

        if (player.isDeadOrDying()) {
            player.getPersistentData().putLong(END_OF_COOLDOWN_TICK_KEY, 0);
            player.getPersistentData().putLong(ACTUAL_BEGIN_TICK_KEY, 0);
            player.getPersistentData().putLong(BEGIN_TICK_KEY, 0);
            player.getPersistentData().putLong(DURATION_KEY, 0);
        }


        int x = 0;
        int y = 0;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);


        long ticksLeft = endOfCooldownTick - currentTick;

        float value = (ticksLeft / (float) beginTick) * ALPHA_MULTIPLIER + ALPHA_BASE;

        if (currentTick < actualBeginTick + duration && player.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get())) {
            if (ConfigRegistry.RENDER_TESTOSTERONE_INVINCIBLE.get()) {
                RenderSystem.enableBlend();
                RenderSystem.setShaderColor((float) ConfigRegistry.TESTOSTERONE_R_INVINCIBLE.get() / 255, (float) ConfigRegistry.TESTOSTERONE_G_INVINCIBLE.get() / 255, (float) ConfigRegistry.TESTOSTERONE_B_INVINCIBLE.get() / 255, ALPHA_MULTIPLIER + ALPHA_BASE);
                guiGraphics.blit(OVERLAY_TEXTURE, x, y, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
            }

        } else if (ticksLeft > 0 && ConfigRegistry.RENDER_TESTOSTERONE_COOLDOWN.get()) {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor((float) ConfigRegistry.TESTOSTERONE_R_COOLDOWN.get() / 255, (float) ConfigRegistry.TESTOSTERONE_G_COOLDOWN.get() / 255, (float) ConfigRegistry.TESTOSTERONE_B_COOLDOWN.get() / 255, value);
            guiGraphics.blit(OVERLAY_TEXTURE, x, y, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

        }
    };
}