package net.mifort.testosterone.effects;

import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class afterlifeEffect extends MobEffect {
    private static final int AFTERLIFE_DURATION = 200;

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class deathEvent {
        @SubscribeEvent
        public static void onLivingDeathEvent(LivingDeathEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player && !event.getSource().type().msgId().equals("genericKill")) {
                if (entity.isHolding(testosteroneModItems.AFTERLIFE_TOTEM.get())) {
                    event.setCanceled(true);
                    entity.setHealth(entity.getMaxHealth());
                    entity.addEffect(new MobEffectInstance(testosteroneModEffects.AFTERLIFE_EFFECT.get(), AFTERLIFE_DURATION, 0, true, false, true));
                }
            }
        }
    }

    protected afterlifeEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xD69E8C);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.getEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get()).getDuration() < 20) {
            pLivingEntity.sendSystemMessage(Component.literal("DEAD"));
            pLivingEntity.kill();
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
