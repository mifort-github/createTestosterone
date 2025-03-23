package net.mifort.testosterone.items.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class curioTieRenderer implements ICurioRenderer {
    public static final ResourceLocation tieTexture = new ResourceLocation(testosterone.MOD_ID, "textures/models/tie_texture.png");
    tieModel TieModel = new tieModel(Minecraft.getInstance().getEntityModels().bakeLayer(tieModel.LAYER_LOCATION));

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack,
                                                                          SlotContext slotContext,
                                                                          PoseStack matrixStack,
                                                                          RenderLayerParent<T, M> renderLayerParent,
                                                                          MultiBufferSource renderTypeBuffer,
                                                                          int light, float limbSwing,
                                                                          float limbSwingAmount,
                                                                          float partialTicks,
                                                                          float ageInTicks,
                                                                          float netHeadYaw,
                                                                          float headPitch) {
        // Render code goes here

        VertexConsumer vBuff = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(tieTexture));
        matrixStack.pushPose();

        ICurioRenderer.translateIfSneaking(matrixStack, slotContext.entity());
        ICurioRenderer.rotateIfSneaking(matrixStack, slotContext.entity());

        TieModel.renderToBuffer(matrixStack, vBuff, light, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStack.popPose();
    }
}
