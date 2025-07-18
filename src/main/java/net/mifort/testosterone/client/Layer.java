package net.mifort.testosterone.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.network.packet.effectCheckerC2SPacket;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class Layer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public static final String EFFECT_CHECKER_KEY = "testosterone:effect_checker_key";

    public static final ResourceLocation BEARD_TEXTURE = new ResourceLocation(testosterone.MOD_ID, "textures/models/beard_texture.png");

    public static final ResourceLocation MUSTACHE_TEXTURE = new ResourceLocation(testosterone.MOD_ID, "textures/models/italianman_texture.png");

    beardModel BeardModel;
    mustacheModel MustacheModel;

    public Layer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer) {
        super(pRenderer);
        BeardModel = new beardModel(Minecraft.getInstance().getEntityModels().bakeLayer(beardModel.LAYER_LOCATION));
        MustacheModel = new mustacheModel(Minecraft.getInstance().getEntityModels().bakeLayer(mustacheModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        testosteroneModMessages.sendToServer(new effectCheckerC2SPacket(pLivingEntity.getId()));

        int hasEffectInt = pLivingEntity.getPersistentData().getInt(EFFECT_CHECKER_KEY);

        if (!ConfigRegistry.RENDER_BEARD.get()) {
            return;
        }

        pPoseStack.pushPose();
        pPoseStack.translate(0, 0, 0); // move the model

        if (hasEffectInt == 1) {
            VertexConsumer vBuff = pBuffer.getBuffer(RenderType.entityTranslucent(BEARD_TEXTURE));
            pPoseStack.scale(1f, 1f, 1f);
            getParentModel().head.translateAndRotate(pPoseStack);
            BeardModel.renderToBuffer(pPoseStack, vBuff, pPackedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        } else if (hasEffectInt == 2) {
            VertexConsumer vBuff = pBuffer.getBuffer(RenderType.entityTranslucent(MUSTACHE_TEXTURE));
            pPoseStack.scale(1f, 1f, 1f);
            getParentModel().head.translateAndRotate(pPoseStack);
            MustacheModel.renderToBuffer(pPoseStack, vBuff, pPackedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        } else {
            VertexConsumer vBuff = pBuffer.getBuffer(RenderType.entityTranslucent(BEARD_TEXTURE));
            pPoseStack.scale(0, 0, 0);
            getParentModel().head.translateAndRotate(pPoseStack);
            BeardModel.renderToBuffer(pPoseStack, vBuff, pPackedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 0f);
        }
        pPoseStack.popPose();
    }
}
