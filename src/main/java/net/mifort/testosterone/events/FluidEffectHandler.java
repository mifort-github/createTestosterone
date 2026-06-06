package net.mifort.testosterone.events;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = testosterone.MOD_ID)
public class FluidEffectHandler {
    @SubscribeEvent
    public static void onLivingEntityTick(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            applyPotionEffect(livingEntity);
        }
    }

    private static void applyPotionEffect(LivingEntity entity) {
        if (entity.isInFluidType(testosteroneFluids.TESTOSTERONE_FLUID.getType())) {
            entity.addEffect(new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT, 20, 0));
        }
        if (entity.isInFluidType(testosteroneFluids.TRENBOLONE_FLUID.getType())) {
            entity.addEffect(new MobEffectInstance(testosteroneModEffects.ROID_RAGE_EFFECT, 20, 0));
        }
    }
}