package net.mifort.testosterone.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mifort.testosterone.config.testosteroneConfigs;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;


public class hudOverlay {
    public static final String END_OF_COOLDOWN_TICK_KEY = "testosterone:end_of_cooldown_tick_key";
    public static final String ACTUAL_BEGIN_TICK_KEY = "testosterone:actual_begin_tick_key";
    public static final String BEGIN_TICK_KEY = "testosterone:begin_tick_key";
    public static final String DURATION_KEY = "testosterone:duration_key";

    public static final float ALPHA_MULTIPLIER = 0.5f;
    public static final float ALPHA_BASE = 0.3f;

    private static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID,
                    "textures/overlay/overlay.png");

    public static void render(GuiGraphics gui) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.level == null) return;

        long endOfCooldownTick = player.getPersistentData().getLong(END_OF_COOLDOWN_TICK_KEY);
        long actualBeginTick = player.getPersistentData().getLong(ACTUAL_BEGIN_TICK_KEY);
        long beginTick = player.getPersistentData().getLong(BEGIN_TICK_KEY);
        long duration = player.getPersistentData().getLong(DURATION_KEY);
        long currentTick = mc.level.getGameTime();

        if (player.isDeadOrDying()) {
            player.getPersistentData().putLong(END_OF_COOLDOWN_TICK_KEY, 0);
            player.getPersistentData().putLong(ACTUAL_BEGIN_TICK_KEY, 0);
            player.getPersistentData().putLong(BEGIN_TICK_KEY, 0);
            player.getPersistentData().putLong(DURATION_KEY, 0);
            return;
        }

        long ticksLeft = endOfCooldownTick - currentTick;

        float value = (beginTick > 0)
                ? (ticksLeft / (float) beginTick) * ALPHA_MULTIPLIER + ALPHA_BASE
                : ALPHA_BASE;
        value = Math.max(0f, Math.min(1f, value));

        boolean invincible = currentTick < actualBeginTick + duration
                && player.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);
        boolean cooldown   = ticksLeft > 0;

        if (invincible) {
            if (testosteroneConfigs.client().renderTestosteroneInvincible.get()) {
                drawOverlay(gui,
                        testosteroneConfigs.client().testosteroneRInvincible.get(),
                        testosteroneConfigs.client().testosteroneGInvincible.get(),
                        testosteroneConfigs.client().testosteroneBInvincible.get(),
                        ALPHA_MULTIPLIER + ALPHA_BASE);
            }

        } else if (cooldown && testosteroneConfigs.client().renderTestosteroneCooldown.get()) {
            drawOverlay(gui,
                    testosteroneConfigs.client().testosteroneRCooldown.get(),
                    testosteroneConfigs.client().testosteroneGCooldown.get(),
                    testosteroneConfigs.client().testosteroneBCooldown.get(),
                    value);
        }
    }

    private static void drawOverlay(GuiGraphics gui, int r, int g, int b, float alpha) {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(r / 255f, g / 255f, b / 255f, alpha);

        gui.blit(OVERLAY_TEXTURE, 0, 0, 0, 0,
                gui.guiWidth(), gui.guiHeight(),
                gui.guiWidth(), gui.guiHeight());

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
    }

    @EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void onRenderGui(RenderGuiEvent.Post event) {
            hudOverlay.render(event.getGuiGraphics());
        }
    }

}
