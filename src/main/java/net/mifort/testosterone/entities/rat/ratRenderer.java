package net.mifort.testosterone.entities.rat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mifort.testosterone.entities.testosteroneModelLayers;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ratRenderer extends MobRenderer<ratEntity, ratModel<ratEntity>> {
    public ratRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ratModel<>(pContext.bakeLayer(testosteroneModelLayers.RAT_MODEL_LAYER)), 0.8f);
    }

    @Override
    public ResourceLocation getTextureLocation(ratEntity pEntity) {
        return new ResourceLocation(testosterone.MOD_ID, "textures/entity/rat.png");
    }

    @Override
    public void render(ratEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();

        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.popPose();
    }

}