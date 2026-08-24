package net.mifort.testosterone.items.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;

import net.mifort.testosterone.testosterone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

public class trinketsTieRenderer implements TrinketRenderer {

	public static final ResourceLocation TIE_TEXTURE = new ResourceLocation(testosterone.MOD_ID, "textures/models/tie_texture.png");

	@Override
	public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> entityModel, PoseStack poseStack, MultiBufferSource buffer, int light, LivingEntity livingEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		ModelPart bakedModel = Minecraft.getInstance().getEntityModels().bakeLayer(tieModel.LAYER_LOCATION);

		tieModel model = new tieModel(bakedModel);

		VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(TIE_TEXTURE));

		poseStack.pushPose();

		if (entityModel instanceof PlayerModel<?> playerModel) {
			playerModel.body.translateAndRotate(poseStack);
		}

		long currentTick = 0;

		if (Minecraft.getInstance().level != null) {
			currentTick = Minecraft.getInstance().level.getGameTime();
		}

		short colorId = (short) ((currentTick / 12) % 16);

		float[] color = DyeColor.byId(colorId).getTextureDiffuseColors();

		if (itemStack.hasTag()) {

			String nbtColor = itemStack.getTag().getString("color");

			for (int pId = 0; pId < 16; pId++) {
				DyeColor dyeColor = DyeColor.byId(pId);

				if (dyeColor.getName().equalsIgnoreCase(nbtColor)) {

					color = dyeColor.getTextureDiffuseColors();

					break;
				}
			}
		}

		model.renderToBuffer(
				poseStack,
				vertexConsumer,
				light,
				OverlayTexture.NO_OVERLAY,
				color[0],
				color[1],
				color[2],
				1.0F
		);

		poseStack.popPose();
	}
}
