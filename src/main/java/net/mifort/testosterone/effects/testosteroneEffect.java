package net.mifort.testosterone.effects;

import net.minecraft.core.registries.BuiltInRegistries;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class testosteroneEffect extends MobEffect {

    public testosteroneEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF8C0A);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        ResourceLocation estrogenEffectId = new ResourceLocation("estrogen", "estrogen");
        MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(estrogenEffectId);

        if (effect != null && livingEntity.hasEffect(effect)) {
            livingEntity.removeEffect(effect);
            livingEntity.removeEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);
        }
    }


    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
