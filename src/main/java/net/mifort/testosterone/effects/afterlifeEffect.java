package net.mifort.testosterone.effects;

import com.mojang.blaze3d.systems.RenderSystem;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// TODO: sync effect across clients, message, fix switching dimension, make void bypass totem or tp idk

public class afterlifeEffect extends MobEffect {
    private static final int AFTERLIFE_DURATION = 60 * 20;
    private static final String CORPSE_KEY = "testosterone:corpse";
    private static final String PROGRESS_KEY = "testosterone:progress";

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class afterlifeEvent {
        @SubscribeEvent
        public static void onLivingDeathEvent(LivingDeathEvent event) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player && !event.getSource().type().msgId().equals("genericKill")) {
                ItemStack totem = null;
                boolean hasTotem = false;

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    totem = player.getInventory().getItem(i);
                    if (totem.is(testosteroneModItems.AFTERLIFE_TOTEM.get())) {
                        hasTotem = true;
                        break;
                    }
                }


                if (hasTotem)  {
                    event.setCanceled(true);
                    player.setHealth(player.getMaxHealth());

                    player.removeAllEffects();

                    player.addEffect(new MobEffectInstance(
                            testosteroneModEffects.AFTERLIFE_EFFECT.get(),
                            AFTERLIFE_DURATION,
                            0,
                            true,
                            false,
                            true));


                    totem.shrink(1);
                }
            }
        }

        @SubscribeEvent
        public static void onEffectAdded(MobEffectEvent.Added event) {
            if (event.getEffectInstance().getEffect() == testosteroneModEffects.AFTERLIFE_EFFECT.get()) {
                LivingEntity entity = event.getEntity();

                if (entity instanceof Player player && !player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_POWER_SELECT, SoundSource.PLAYERS, 1, 1f);


                    ArmorStand corpseStand = EntityType.ARMOR_STAND.create(player.level());
                    if (corpseStand != null) {
                        corpseStand.moveTo(player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
                        corpseStand.setNoGravity(true);
                        corpseStand.setSilent(true);
                        corpseStand.setInvisible(true);
                        corpseStand.setInvulnerable(true);

                        ItemStack mobHead = new ItemStack(Items.SKELETON_SKULL);
                        corpseStand.setItemSlot(EquipmentSlot.HEAD, mobHead);

                        player.level().addFreshEntity(corpseStand);
                        player.getPersistentData().putUUID(CORPSE_KEY, corpseStand.getUUID());
                        player.getPersistentData().putInt(PROGRESS_KEY, 0);
                    }
                }
            }
        }





        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Player player = event.getEntity();
            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
            Player player = event.getEntity();
            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
            Player player = event.getEntity();
            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
            Player player = event.getEntity();
            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onAttackEntity(AttackEntityEvent event) {
            Player player = event.getEntity();
            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onItemPickup(EntityItemPickupEvent event) {
            Player player = event.getEntity();
            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                event.setCanceled(true);
            }
        }

//        @SubscribeEvent
//        public static void onItemToss(ItemTossEvent event) {
//            Player player = event.getPlayer();
//            if (player.hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
//                event.setCanceled(true);
//            }
//        }

        @SubscribeEvent
        public static void onEffectRemoved(MobEffectEvent.Remove event) {
            if (event.getEffectInstance().getEffect() == testosteroneModEffects.AFTERLIFE_EFFECT.get()) {
                if (event.getEntity() instanceof Player player) {
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 1, 1f);

                    player.setInvulnerable(false);

                    if (player.level() instanceof ServerLevel serverLevel && player.getPersistentData().hasUUID(CORPSE_KEY)) {
                        UUID playerCorpse = player.getPersistentData().getUUID(CORPSE_KEY);

                        Entity corpse = serverLevel.getEntity(playerCorpse);
                        if (corpse != null) {
                            player.teleportTo(corpse.getX(), corpse.getY(), corpse.getZ());
                            corpse.kill();
                        }
                    }
                }
            }
        }
    }


    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT)
    public static class afterlifePlayerRenderHandler {

        @SubscribeEvent
        public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
            if (event.getEntity().hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.2F);
            }
        }

        @SubscribeEvent
        public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
            if (event.getEntity().hasEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get())) {
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.disableBlend();
            }
        }
    }


    protected afterlifeEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FFFF);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            Entity corpse = null;

            player.setInvulnerable(true);
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 3, true, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 1, true, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, true, false, false));

            if (player.level() instanceof ServerLevel serverLevel && player.getPersistentData().hasUUID(CORPSE_KEY)) {
                UUID playerCorpse = player.getPersistentData().getUUID(CORPSE_KEY);

                corpse = serverLevel.getEntity(playerCorpse);
            }

            if (player.getEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get()).getDuration() < 20 && player.getEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get()).getDuration() >= 0) {
                if (corpse != null) {
                    player.teleportTo(corpse.getX(), corpse.getY(), corpse.getZ());
                    corpse.kill();
                }

                player.setInvulnerable(false);
                player.kill();

            } else {

                if (corpse != null) {
                    int progress = player.getPersistentData().getInt(PROGRESS_KEY);

                    if (player.isShiftKeyDown() && player.distanceToSqr(corpse) < 10) {
                        progress++;

                        player.addEffect(new MobEffectInstance(
                                testosteroneModEffects.AFTERLIFE_EFFECT.get(),
                                player.getEffect(testosteroneModEffects.AFTERLIFE_EFFECT.get()).getDuration() + 1,
                                0,
                                true,
                                false,
                                true));

                        if (progress >= 100) {
                            player.removeAllEffects();

                        }

                    } else if (progress > 0) {
                        progress--;
                    }

                    player.getPersistentData().putInt(PROGRESS_KEY, progress);
                    player.sendSystemMessage(Component.literal(String.valueOf(progress)));
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
