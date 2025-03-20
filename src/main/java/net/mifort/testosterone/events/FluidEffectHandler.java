package net.mifort.testosterone.events;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
public class FluidEffectHandler {
    @SubscribeEvent
    public static void onLivingEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        applyPotionEffect(entity);
    }

    private static void applyPotionEffect(LivingEntity entity) {
        if (entity.isInFluidType(testosteroneFluids.TESTOSTERONE_FLUID.getType())) {
            entity.addEffect(new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(), 20, 0));
        }
    }
}