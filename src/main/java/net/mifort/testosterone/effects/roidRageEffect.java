package net.mifort.testosterone.effects;

import com.simibubi.create.foundation.damageTypes.CreateDamageSources;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.particles.runParticleData;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class roidRageEffect extends MobEffect {
    private static final String SPEED_KEY = "testosterone:speed_key";
    private static final String JUMP_KEY = "testosterone:jump_key";
    private static final String HEIGHT_KEY = "testosterone:height_key";
    private static final String JUMPING_KEY = "testosterone:jumping_key";


    public roidRageEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xCC0000);
    }

    public static int getSpeed(Player player) {
        return player.getPersistentData().getInt(SPEED_KEY);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            Level level = player.level();

            amplifier += 2;

            int speed = player.getPersistentData().getInt(SPEED_KEY);

            if (ConfigRegistry.DISPLAY_SPEED.get()) {
                player.displayClientMessage(Component.literal(String.valueOf(speed)), true);
            }

            if (player.onGround()) {
                player.getPersistentData().putBoolean(JUMPING_KEY, false);
            }

            if (player.isCrouching() && speed > ConfigRegistry.ABILITY_SPEED.get()) {
                player.getPersistentData().putBoolean(JUMP_KEY, true);

            } else if (player.getPersistentData().getBoolean(JUMP_KEY)) {
                player.getPersistentData().putBoolean(JUMP_KEY, false);

                if (player.onGround()) {
                    player.addDeltaMovement(new Vec3(0f, speed * ConfigRegistry.JUMP_MULTIPLIER.get(), 0f));
                    player.getPersistentData().putDouble(HEIGHT_KEY, player.getY());
                    player.getPersistentData().putBoolean(JUMPING_KEY, true);
                }

            } else if (player.isSprinting()) {
                if (speed < (ConfigRegistry.MAX_SPEED.get() / 2) * amplifier) {
                    player.getPersistentData().putInt(SPEED_KEY, speed + 1);
                }

            } else {
                if (speed > 0) {
                    player.getPersistentData().putInt(SPEED_KEY, 0);
                }
            }

            AttributeInstance stepHeightAttribute = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());

            Double stepHeight = ConfigRegistry.STEP_HEIGHT.get();

            if (stepHeightAttribute != null) {
                if (stepHeightAttribute.getBaseValue() < amplifier - stepHeight) {
                    stepHeightAttribute.setBaseValue(amplifier - stepHeight);
                }
            }

            AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);

            if (speedAttribute != null) {
                speedAttribute.setBaseValue(0.1 + amplifier * speed * ConfigRegistry.SPEED_MULTIPLIER.get());
            }

            if (speed > ConfigRegistry.ABILITY_SPEED.get()) {
                if (!level.isClientSide() && !player.isCrouching()) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    serverLevel.sendParticles(new runParticleData(player.getUUID(), ConfigRegistry.TRAIL_DURATION.get(), serverLevel.getGameTime()),
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            0,
                            0, 0, 0,
                            0
                    );
                }

                AABB playerBB = player.getBoundingBox();

                List<Entity> collidingEntities = level.getEntities(
                        player,
                        playerBB,
                        each -> each != player && entity.getBoundingBox().intersects(playerBB)
                );

                float rot = player.getYRot();
                double rotRad = Math.toRadians(rot);

                if (!player.getPersistentData().getBoolean(JUMPING_KEY) && !player.onGround()) {
                    double mul = ConfigRegistry.TREN_IN_AIR_MUL.get()*0.001;
                    player.addDeltaMovement(new Vec3(-Math.sin(rotRad) * speed * mul, 0, Math.cos(rotRad) * speed * mul));
                }


                for (Entity other : collidingEntities) {
                    other.hurt(CreateDamageSources.runOver(level, player), (float) speed / 50);


                    other.addDeltaMovement(new Vec3(-Math.sin(rotRad) * speed * 0.01, speed * 0.002, Math.cos(rotRad) * speed * 0.01));
                }

                BlockPos blockAtFeet = new BlockPos(
                        (int) Math.floor(player.getX()),
                        (int) Math.floor(player.getY()),
                        (int) Math.floor(player.getZ())
                );

                if (level.getBlockState(blockAtFeet).getCollisionShape(level, blockAtFeet, CollisionContext.of(player)).isEmpty()) {

                    BlockPos blockBelow = new BlockPos(
                            (int) Math.floor(player.getX()),
                            (int) Math.floor(player.getY() - 0.2),
                            (int) Math.floor(player.getZ())
                    );

                    if (!level.getFluidState(blockBelow).isEmpty()) {
                        if (player.isCrouching()) {
                            player.getPersistentData().putInt(SPEED_KEY, 0);
                        } else {

                            Vec3 motion = player.getDeltaMovement();

                            if (motion.y < 0) {
                                player.setDeltaMovement(motion.x, 0, motion.z);
                            }
                            player.setPos(player.getX(), blockBelow.getY() + 1, player.getZ());
                            player.setOnGround(true);
                            player.fallDistance = 0;
                        }
                    }
                }
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

            player.getPersistentData().putBoolean(JUMP_KEY, false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }


    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class events {
        @SubscribeEvent
        public static void onFallDamage(LivingFallEvent event) {
            if (event.getEntity() instanceof Player player) {
                if (player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT.get()) && player.getPersistentData().contains(HEIGHT_KEY)) {
                    double jump_height = player.getPersistentData().getDouble(HEIGHT_KEY);

                    if (jump_height <= player.getY()) {
                        event.setCanceled(true);

                    } else {
                        event.setDistance((float) (jump_height - player.getY()));
                    }


                    player.getPersistentData().remove(HEIGHT_KEY);
                }
            }
        }
    }
}
