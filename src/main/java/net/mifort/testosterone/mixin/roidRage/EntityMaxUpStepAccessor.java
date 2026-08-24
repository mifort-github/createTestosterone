package net.mifort.testosterone.mixin.roidRage;

import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityMaxUpStepAccessor {

    @Accessor("maxUpStep")
    float testosterone$getMaxUpStep();

    @Accessor("maxUpStep")
    void testosterone$setMaxUpStep(float value);
}
