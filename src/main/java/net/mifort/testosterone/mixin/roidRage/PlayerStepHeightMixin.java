package net.mifort.testosterone.mixin.roidRage;

import net.mifort.testosterone.effects.testosteroneModAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PlayerStepHeightMixin {

    @Unique
    private static final String LAST_APPLIED_KEY = "testosterone:step_height_last_applied";

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void testosterone$applyStepHeight(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!(self instanceof Player player)) {
            return;
        }

        AttributeInstance instance = player.getAttribute(testosteroneModAttributes.STEP_HEIGHT_ADDITION);
        if (instance == null) {
            return;
        }

        float newDelta = (float) instance.getValue();
        float lastDelta = player.getCustomData().getFloat(LAST_APPLIED_KEY);



        if (newDelta == lastDelta) {
            return;
        }

        EntityMaxUpStepAccessor accessor = (EntityMaxUpStepAccessor) self;
        float currentStepHeight = accessor.testosterone$getMaxUpStep();

        float baseStepHeight = currentStepHeight - lastDelta;
        accessor.testosterone$setMaxUpStep(baseStepHeight + newDelta);

        player.getCustomData().putFloat(LAST_APPLIED_KEY, newDelta);
    }
}
