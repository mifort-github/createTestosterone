package net.mifort.testosterone.effects;

import com.simibubi.create.foundation.damageTypes.CreateDamageSources;
import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.particles.airPassingParticleData;
import net.mifort.testosterone.particles.runParticleData;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class roidRageEffect extends MobEffect {
    private static final String SPEED_KEY = "testosterone:speed_key";
    private static final String JUMP_KEY = "testosterone:jump_key";
    private static final String HEIGHT_KEY = "testosterone:height_key";
    private static final String JUMPING_KEY = "testosterone:jumping_key";
    public static final String SWIMMING_KEY = "testosterone:swimming_key";
    public static final String MARKED_KEY = "testosterone:marked_key";
    public static final String MARKED_BY_KEY = "testosterone:marked_by_key";


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

            if (player.isFallFlying()) {
                player.setSprinting(false);
            }

            if (ConfigRegistry.DISPLAY_SPEED.get()) {
                player.displayClientMessage(Component.literal(String.valueOf(speed)), true);
            }

            if (player.onGround()) {
                player.getPersistentData().putBoolean(JUMPING_KEY, false);
                player.getPersistentData().putBoolean(SWIMMING_KEY, false);
            }

            if (player.isCrouching() && speed > ConfigRegistry.ABILITY_SPEED.get()) {
                player.getPersistentData().putBoolean(JUMP_KEY, true);

            } else if (player.getPersistentData().getBoolean(JUMP_KEY)) {
                player.getPersistentData().putBoolean(JUMP_KEY, false);

                if (player.onGround()) {
                    player.addDeltaMovement(new Vec3(0f, speed * ConfigRegistry.JUMP_MULTIPLIER.get(), 0f));
                    player.getPersistentData().putDouble(HEIGHT_KEY, player.getY());
                    player.getPersistentData().putBoolean(JUMPING_KEY, true);
                } else {
                    player.addDeltaMovement(new Vec3(0f, -1 * speed * ConfigRegistry.JUMP_MULTIPLIER.get(), 0f));
                    player.getPersistentData().putBoolean(SWIMMING_KEY, true);
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
                if (stepHeightAttribute.getBaseValue() < amplifier + stepHeight) {
                    stepHeightAttribute.setBaseValue(amplifier + stepHeight);
                }
            }

            AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);

            if (speedAttribute != null) {
                speedAttribute.setBaseValue(0.1 + amplifier * speed * ConfigRegistry.SPEED_MULTIPLIER.get());
            }

            if (player.getPersistentData().getBoolean(SWIMMING_KEY)) {
                player.setPose(Pose.SWIMMING);
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


                    if (new Random().nextInt(ConfigRegistry.MAX_SPEED.get()) < speed) {
                        serverLevel.sendParticles(new airPassingParticleData(player.getUUID()),
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                0,
                                0, 0, 0,
                                0
                        );
                    }
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
                    double mul = ConfigRegistry.TREN_IN_AIR_MUL.get() * 0.001;
                    player.addDeltaMovement(new Vec3(-Math.sin(rotRad) * speed * mul, 0, Math.cos(rotRad) * speed * mul));
                }


                for (Entity other : collidingEntities) {

                    other.getPersistentData().putLong(MARKED_KEY, level.getGameTime());
                    other.getPersistentData().putUUID(MARKED_BY_KEY, player.getUUID());

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
    public void removeAttributeModifiers(@NotNull LivingEntity entity, @NotNull AttributeMap attributeMap, int amplifier) {
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
            player.getPersistentData().putInt(SPEED_KEY, 0);
            player.getPersistentData().putBoolean(JUMPING_KEY, false);
            player.getPersistentData().remove(HEIGHT_KEY);
            player.getPersistentData().putBoolean(SWIMMING_KEY, false);
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
                    if (player.getPersistentData().getBoolean(SWIMMING_KEY)) {
                        if (player.level() instanceof ServerLevel level) {
                            level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(player.getOnPos())),
                                    player.getX(), player.getY(), player.getZ(),
                                    ((int) event.getDistance() * 10),
                                    event.getDistance() / ConfigRegistry.FALL_DAMAGE_RADIUS.get(), 0, event.getDistance() / ConfigRegistry.FALL_DAMAGE_RADIUS.get(),
                                    1);

                            level.getEntities().getAll().forEach(entity -> {
                                if (entity instanceof LivingEntity livingEntity) {
                                    if (livingEntity == player) return;

                                    if (player.distanceTo(livingEntity) < event.getDistance() / ConfigRegistry.FALL_DAMAGE_RADIUS.get()) {
                                        livingEntity.getPersistentData().putLong(MARKED_KEY, level.getGameTime());
                                        livingEntity.getPersistentData().putUUID(MARKED_BY_KEY, player.getUUID());

                                        livingEntity.hurt(CreateDamageSources.runOver(level, player), (float) (event.getDistance() / ConfigRegistry.FALL_DAMAGE_RADIUS.get()));



                                        livingEntity.addDeltaMovement(new Vec3(0, event.getDistance() / 24, 0));
                                    }
                                }
                            });
                        }
                    }

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


        @SubscribeEvent
        public static void onLivingDeathEvent(LivingDeathEvent livingDeathEvent) {

            LivingEntity entity = livingDeathEvent.getEntity();
            Level level = entity.level();



            if (!level.isClientSide) {

                Minecraft.getInstance().player.sendSystemMessage(Component.literal(String.valueOf(level.getGameTime() - entity.getPersistentData().getLong(MARKED_KEY))));

                if (level.getGameTime() - entity.getPersistentData().getLong(MARKED_KEY) < 40) {
                    Player player = level.getPlayerByUUID(entity.getPersistentData().getUUID(MARKED_BY_KEY));

                    if (player != null) {
                        testosteroneAdvancementUtils.ROADKILL.trigger((ServerPlayer) player);
                    }
                }
            }
        }
    }
}
