package net.mifort.testosterone.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlock;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Direction;

public class AnimatedDecantation extends AnimatedKinetics {

    @Override
    public void draw(GuiGraphics graphics, int xOffset, int yOffset) {
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 0);
        matrixStack.translate(0, 0, 200);
        matrixStack.translate(2, 22, 0);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Axis.YP.rotationDegrees(22.5f + 90));
        int scale = 25;

        blockElement(shaft(Direction.Axis.X))
                .rotateBlock(-getCurrentAngle(), 0, 90)
                .scale(scale)
                .render(graphics);

        blockElement(testosteroneModBlocks.DECANTER_CENTRIFUGE.getDefaultState()
                .setValue(decanterCentrifugeBlock.HORIZONTAL_FACING, Direction.NORTH))
                .rotateBlock(180, 0, 0)
                .scale(scale)
                .render(graphics);

        matrixStack.popPose();
    }

}