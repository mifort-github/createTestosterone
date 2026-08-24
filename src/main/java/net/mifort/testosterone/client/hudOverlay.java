package net.mifort.testosterone.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.mifort.testosterone.network.packet.ClientEffectData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;

public class hudOverlay {

	public static final float ALPHA_MULTIPLIER = 0.5f;
	public static final float ALPHA_BASE = 0.3f;

	private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(testosterone.MOD_ID, "textures/overlay/overlay.png");

	public static void register() {
		HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> render(guiGraphics));
	}

	public static void render(GuiGraphics guiGraphics) {

		Minecraft minecraft = Minecraft.getInstance();
		LocalPlayer player = minecraft.player;

		if (player == null || minecraft.level == null) {
			return;
		}

		long endOfCooldownTick = ClientEffectData.getEndOfCooldownTick();
		long actualBeginTick = ClientEffectData.getActualBeginTick();
		long beginTick = ClientEffectData.getBeginTick();
		long duration = ClientEffectData.getDuration();

		long currentTick = minecraft.level.getGameTime();

		if (player.isDeadOrDying()) {
			ClientEffectData.resetHudData();
			return;
		}

		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();

		long ticksLeft = endOfCooldownTick - currentTick;

		float value = beginTick > 0 ? Mth.clamp((ticksLeft / (float) beginTick) * ALPHA_MULTIPLIER + ALPHA_BASE, 0.0f, ALPHA_MULTIPLIER + ALPHA_BASE) : ALPHA_BASE;

		if (currentTick < actualBeginTick + duration && player.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT)) {

			if (ConfigRegistry.RENDER_TESTOSTERONE_INVINCIBLE.get()) {

				float r = ConfigRegistry.TESTOSTERONE_R_INVINCIBLE.get() / 255.0f;
				float g = ConfigRegistry.TESTOSTERONE_G_INVINCIBLE.get() / 255.0f;
				float b = ConfigRegistry.TESTOSTERONE_B_INVINCIBLE.get() / 255.0f;
				float a = ALPHA_MULTIPLIER + ALPHA_BASE;

				drawOverlay(guiGraphics, screenWidth, screenHeight, r, g, b, a);
			}
		}

		else if (ticksLeft > 0 && ConfigRegistry.RENDER_TESTOSTERONE_COOLDOWN.get()) {

			float r = ConfigRegistry.TESTOSTERONE_R_COOLDOWN.get() / 255.0f;
			float g = ConfigRegistry.TESTOSTERONE_G_COOLDOWN.get() / 255.0f;
			float b = ConfigRegistry.TESTOSTERONE_B_COOLDOWN.get() / 255.0f;

			drawOverlay(guiGraphics, screenWidth, screenHeight, r, g, b, value);
		}
	}

	private static void drawOverlay(GuiGraphics guiGraphics, int screenWidth, int screenHeight,
									float r, float g, float b, float a) {

		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(
				GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
				GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO
		);

		guiGraphics.setColor(r, g, b, a);

		guiGraphics.blit(
				OVERLAY_TEXTURE,
				0, 0, 0, 0,
				screenWidth, screenHeight,
				screenWidth, screenHeight
		);

		guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.disableBlend();
	}
}
