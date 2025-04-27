package net.mifort.testosterone.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

public class trenEffect extends MobEffect {
    private static final String SPEED_KEY = "testosterone:speed_key";
    private static final int SPEED_LIMIT = 100;

    public trenEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            amplifier += 2;

            int speed = player.getPersistentData().getInt(SPEED_KEY);
            if (player.isSprinting()) {
                if (speed < SPEED_LIMIT * (amplifier )) {
                    player.getPersistentData().putInt(SPEED_KEY, speed + 1);
                }
            } else {
                if (speed > 0) {
                    player.getPersistentData().putInt(SPEED_KEY, 0);
                }
            }

            AttributeInstance stepHeightAttribute = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());

            if (stepHeightAttribute != null) {
                if (stepHeightAttribute.getBaseValue() < amplifier - 1) {
                    stepHeightAttribute.setBaseValue(amplifier - 1);
                }
            }

            AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);

            if (speedAttribute != null) {
                speedAttribute.setBaseValue(0.1 + (amplifier) * speed * 0.001);
            }

            Level level = player.level();
            if (!level.isClientSide() && speed > 50) {
                ServerLevel serverLevel = (ServerLevel) level;
                serverLevel.sendParticles(ParticleTypes.SCULK_SOUL,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        8,
                        0,
                        0,
                        0,
                        0.1
                );
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        if (entity instanceof Player player) {
            AttributeInstance stepHeightAttribute = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
            if (stepHeightAttribute != null) {
                stepHeightAttribute.setBaseValue(0);
            }

            AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (speedAttribute != null) {
                speedAttribute.setBaseValue(0.1);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
