package net.mifort.testosterone.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class hudOverlay {
    public static final String END_OF_COOLDOWN_TICK_KEY = "end_of_cooldown_tick_key";
    public static final String ACTUAL_BEGIN_TICK_KEY = "actual_begin_tick_key";
    public static final String BEGIN_TICK_KEY = "begin_tick_key";
    public static final String DURATION_KEY = "duration_key";

    public static final float ALPHA_MULTIPLIER = 0.5f;
    public static final float ALPHA_BASE = 0.3f;

    private static final ResourceLocation TEMP = new ResourceLocation(testosterone.MOD_ID,
            "textures/overlay/overlay.png");

    public static final IGuiOverlay HUD_TEMP = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        LocalPlayer player = Minecraft.getInstance().player;

        long endOfCooldownTick = player.getPersistentData().getLong(END_OF_COOLDOWN_TICK_KEY);
        long actualBeginTick = player.getPersistentData().getLong(ACTUAL_BEGIN_TICK_KEY);
        long beginTick = player.getPersistentData().getLong(BEGIN_TICK_KEY);
        long duration = player.getPersistentData().getLong(DURATION_KEY);

//        player.sendSystemMessage(Component.literal("---------------------------------------------"));
//
//        player.sendSystemMessage(Component.literal("endOfCooldownTick: " + endOfCooldownTick));
//        player.sendSystemMessage(Component.literal("actualBeginTick: " + actualBeginTick));
//        player.sendSystemMessage(Component.literal("beginTick: " + beginTick));
//        player.sendSystemMessage(Component.literal("duration: " + duration));

        long currentTick = Minecraft.getInstance().level.getGameTime();

        // player.sendSystemMessage(Component.literal("current tick: " + currentTick));

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

        if (currentTick < actualBeginTick + duration && player.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get())) {
            // player.sendSystemMessage(Component.literal("AVAILABLE FOR: " + (actualBeginTick + duration - currentTick)));

            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1f, 0.82f, 0.467f, ALPHA_MULTIPLIER + ALPHA_BASE);
            guiGraphics.blit(TEMP, x, y, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

        } else if (ticksLeft > 0) {
            // player.sendSystemMessage(Component.literal("COOLDOWN LEFT: " + ticksLeft));

            RenderSystem.setShaderColor(1f, 1f, 0f, (ticksLeft / (float) beginTick) * ALPHA_MULTIPLIER + ALPHA_BASE);
            guiGraphics.blit(TEMP, x, y, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

        }
    };
}