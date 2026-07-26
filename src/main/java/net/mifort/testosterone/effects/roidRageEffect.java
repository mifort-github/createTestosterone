package net.mifort.testosterone.effects;

import com.simibubi.create.foundation.damageTypes.CreateDamageSources;
import net.mifort.testosterone.advancements.testosteroneModTriggers;
import net.mifort.testosterone.config.testosteroneConfigs;
import net.mifort.testosterone.particles.airPassingParticleData;
import net.mifort.testosterone.particles.runParticleData;
import net.mifort.testosterone.sounds.testosteroneModSounds;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;
import java.util.Random;

public class roidRageEffect extends MobEffect {
    public static final String MARKED_KEY = "testosterone:marked_key";
    public static final String MARKED_BY_KEY = "testosterone:marked_by_key";

    private static final String SPEED_KEY = "testosterone:speed_key";
    private static final String JUMPED_TICK_KEY  = "testosterone:jumped_tick_key";
    private static final String READY_TO_JUMP_KEY = "testosterone:ready_to_jump_key";
    private static final String IN_JUMP_KEY = "testosterone:in_jump_key";
    private static final String SWIMMING_KEY = "testosterone:swimming_key";
    private static final String SLAM_PENDING_KEY = "testosterone:slam_pending_key";

    public static final ResourceLocation SPEED_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, "tren_speed");
    private static final ResourceLocation STEP_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, "tren_step");

    public static int getSpeed(Player player) {
        return player.getPersistentData().getInt(SPEED_KEY);
    }

    public static boolean isSwimming(Player player) {
        return player.getPersistentData().getBoolean(SWIMMING_KEY);
    }

    public roidRageEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xCC0000);

        addAttributeModifier(
                Attributes.STEP_HEIGHT,
                STEP_MODIFIER_ID,
                1,
                AttributeModifier.Operation.ADD_VALUE
        );
    }


    @Override
    public boolean applyEffectTick(LivingEntity p_19467_, int p_19468_) {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true;
    }

    private static void applyForce(Player player, double x, double y, double z) {
        if (player instanceof ServerPlayer serverPlayer) {
            player.addDeltaMovement(new Vec3(x, y, z));
            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(player));
        }
    }

    public static void updateSpeedModifier(Player player, int speed) {
        AttributeInstance attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attr == null) return;

        attr.removeModifier(SPEED_MODIFIER_ID);

        if (speed > 0) {
            attr.addTransientModifier(new AttributeModifier(
                    SPEED_MODIFIER_ID,
                    speed * testosteroneConfigs.server().speedMultiplier.get(),
                    AttributeModifier.Operation.ADD_VALUE
            ));
        }
    }

    @Override
    public void onMobRemoved(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        if (entity instanceof Player player) {
            AttributeInstance attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (attr != null) attr.removeModifier(SPEED_MODIFIER_ID);
            player.getPersistentData().putInt(SPEED_KEY, 0);
        }
    }

    @EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class events {
        @SubscribeEvent
        public static void fov(ComputeFovModifierEvent event) {
            if (event.getPlayer().hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT)) {
                event.setNewFovModifier(1.1f);
            }
        }

        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Post event) {
            Player player = event.getEntity();
            if (!player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT)) return;

            int amplifier = player.getEffect(testosteroneModEffects.ROID_RAGE_EFFECT).getAmplifier();

            Level level = player.level();
            int speed = player.getPersistentData().getInt(SPEED_KEY);
            boolean swimming = player.getPersistentData().getBoolean(SWIMMING_KEY);
            boolean slamPending = player.getPersistentData().getBoolean(SLAM_PENDING_KEY);
            long jumpedTick = player.getPersistentData().getLong(JUMPED_TICK_KEY);
            boolean readyToJump = player.getPersistentData().getBoolean(READY_TO_JUMP_KEY);
            boolean inJump = player.getPersistentData().getBoolean(IN_JUMP_KEY);

            if (level.isClientSide() && testosteroneConfigs.client().displaySpeed.get()) {
                player.displayClientMessage(Component.literal(String.valueOf(speed)), true);
            }

            if (player.isFallFlying() && !testosteroneConfigs.server().allowElytra.get()) {
                player.setSprinting(false);
            }

            if (swimming) {
                player.setPose(Pose.SWIMMING);
            }

            if (player.isSprinting()) {
                int cap = testosteroneConfigs.server().maxSpeed.get() * (amplifier + 1);
                if (speed < cap) speed++;
            } else if (!player.isCrouching()) {
                speed = 0;
            }

            updateSpeedModifier(player, speed);

            if (player.onGround()) {
                if (inJump && level.getGameTime() - jumpedTick > 5) {
                    inJump = false;
                }
                swimming = false;
            }

            // Jump
            if (player.isCrouching()) {
                if (inJump) {
                    applyForce(player, 0, -1 * speed * testosteroneConfigs.server().jumpMultiplier.get(), 0);
                    swimming = true;
                    slamPending = true;
                } else {
                    readyToJump = true;
                }
            } else if (readyToJump && player.onGround()) {
                applyForce(player, 0, speed * testosteroneConfigs.server().jumpMultiplier.get(), 0);
                readyToJump = false;
                inJump = true;
                jumpedTick = level.getGameTime();
            }

            if (speed > testosteroneConfigs.server().abilitySpeed.get()) {

                if (!level.isClientSide() && !player.isCrouching()) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    serverLevel.sendParticles(
                            new runParticleData(player.getUUID(),
                                    testosteroneConfigs.server().trailDuration.get(),
                                    serverLevel.getGameTime()),
                            player.getX(), player.getY(), player.getZ(),
                            0, 0, 0, 0, 0);

                    if (new Random().nextInt(testosteroneConfigs.server().maxSpeed.get()) < speed) {
                        serverLevel.sendParticles(
                                new airPassingParticleData(player.getUUID(), testosteroneConfigs.server().trailDuration.get()),
                                player.getX(), player.getY(), player.getZ(),
                                0, 0, 0, 0, 0);
                    }
                }

                // Entity collision
                AABB playerBB = player.getBoundingBox();
                List<Entity> collidingEntities = level.getEntities(
                        player, playerBB,
                        each -> each != player && each.getBoundingBox().intersects(playerBB)
                );

                float rot = player.getYRot();
                double rotRad = Math.toRadians(rot);

                // Air momentum
                if (!inJump && !player.onGround()) {
                    double mul = testosteroneConfigs.server().trenInAirMul.get() * 0.001;
                    player.addDeltaMovement(new Vec3(
                            -Math.sin(rotRad) * speed * mul,
                            0,
                            Math.cos(rotRad) * speed * mul));
                }

                // Hit entities
                for (Entity other : collidingEntities) {
                    if (other instanceof LivingEntity) {
                        other.getPersistentData().putLong(MARKED_KEY, level.getGameTime());
                        other.getPersistentData().putUUID(MARKED_BY_KEY, player.getUUID());
                        other.hurt(CreateDamageSources.runOver(level, player), (float) speed / 50);
                        level.playSound(null, player.blockPosition(),
                                testosteroneModSounds.ENEMY_HIT_SFX.get(), SoundSource.PLAYERS);
                        other.addDeltaMovement(new Vec3(
                                -Math.sin(rotRad) * speed * 0.01,
                                speed * 0.002,
                                Math.cos(rotRad) * speed * 0.01));
                    }
                }

                // Fluid running
                BlockPos blockAtFeet = BlockPos.containing(player.getX(), player.getY(), player.getZ());
                if (level.getBlockState(blockAtFeet)
                        .getCollisionShape(level, blockAtFeet, CollisionContext.of(player))
                        .isEmpty()) {

                    BlockPos blockBelow = BlockPos.containing(
                            player.getX(), player.getY() - 0.2, player.getZ());

                    if (!level.getFluidState(blockBelow).isEmpty()) {
                        if (player.isCrouching()) {
                            speed = 0;
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

            player.getPersistentData().putInt(SPEED_KEY, speed);
            player.getPersistentData().putLong(JUMPED_TICK_KEY, jumpedTick);
            player.getPersistentData().putBoolean(READY_TO_JUMP_KEY, readyToJump);
            player.getPersistentData().putBoolean(IN_JUMP_KEY, inJump);
            player.getPersistentData().putBoolean(SWIMMING_KEY, swimming);
            player.getPersistentData().putBoolean(SLAM_PENDING_KEY, slamPending);
        }

        @SubscribeEvent
        public static void onFallDamage(LivingFallEvent event) {
            if (!(event.getEntity() instanceof Player player)) return;
            if (!player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT)) return;


            boolean slamPending = player.getPersistentData().getBoolean(SLAM_PENDING_KEY);
            if (slamPending && player.level() instanceof ServerLevel level) {
                player.getPersistentData().putBoolean(SLAM_PENDING_KEY, false);
                player.getPersistentData().putBoolean(SWIMMING_KEY, false);
                player.setPose(Pose.STANDING);
                double radius = event.getDistance() * testosteroneConfigs.server().fallDamageRadius.get() * 0.5;

                level.playSound(null, player.blockPosition(),
                        testosteroneModSounds.GROUND_SLAM_SFX.get(), SoundSource.PLAYERS);
                level.sendParticles(ParticleTypes.SPIT,
                        player.getX(), player.getY(), player.getZ(),
                        (int) event.getDistance() * 10,
                        radius, 0, radius, 1);

                level.getEntities().getAll().forEach(entity -> {
                    if (!(entity instanceof LivingEntity livingEntity) || livingEntity == player) return;
                    if (player.distanceTo(livingEntity) < radius) {
                        livingEntity.getPersistentData().putLong(MARKED_KEY, level.getGameTime());
                        livingEntity.getPersistentData().putUUID(MARKED_BY_KEY, player.getUUID());
                        livingEntity.hurt(CreateDamageSources.runOver(level, player), (float) radius);
                        livingEntity.addDeltaMovement(new Vec3(0, event.getDistance() / 24.0, 0));
                    }
                });
            }

            event.setCanceled(true);
        }

        @SubscribeEvent
        public static void onLivingDeathEvent(LivingDeathEvent event) {
            LivingEntity entity = event.getEntity();
            Level level = entity.level();
            if (level.isClientSide) return;

            if (level.getGameTime() - entity.getPersistentData().getLong(MARKED_KEY) < 20) {
                Player player = level.getPlayerByUUID(
                        entity.getPersistentData().getUUID(MARKED_BY_KEY));
                if (player instanceof ServerPlayer sp) {
                    testosteroneModTriggers.ROADKILL.get().trigger(sp);
                }
            }
        }

        @SubscribeEvent
        public static void onRemovedEffect(MobEffectEvent.Remove event) {
            if (event.getEffect().is(testosteroneModEffects.ROID_RAGE_EFFECT) && event.getEntity() instanceof Player player) {
                AttributeInstance attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
                if (attr != null) {
                    attr.removeModifier(SPEED_MODIFIER_ID);
                }
            }
        }

        @SubscribeEvent
        public static void onExpiredEffect(MobEffectEvent.Expired event) {
            if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().is(testosteroneModEffects.ROID_RAGE_EFFECT) && event.getEntity() instanceof Player player) {
                AttributeInstance attr = player.getAttribute(Attributes.MOVEMENT_SPEED);
                if (attr != null) {
                    attr.removeModifier(SPEED_MODIFIER_ID);
                }
            }
        }
    }
}