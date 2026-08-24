package net.mifort.testosterone.mixin.roidRage;


import net.mifort.testosterone.effects.testosteroneModAttributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerAttributesMixin {

    @Inject(method = "createAttributes", at = @At("RETURN"))
    private static void testosterone$addStepHeightAttribute(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(testosteroneModAttributes.STEP_HEIGHT_ADDITION, 0.0D);
    }
}
