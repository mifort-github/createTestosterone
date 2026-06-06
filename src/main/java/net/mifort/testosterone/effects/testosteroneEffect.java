package net.mifort.testosterone.effects;

import net.mifort.testosterone.advancements.testosteroneModTriggers;
import net.mifort.testosterone.config.testosteroneConfigs;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.network.packet.hudS2CPacket;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class testosteroneEffect extends MobEffect {
    @EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class ForgeEvents {
        private static final String BEGIN_TICK = "testosterone:begin_tick";
        private static final String DAMAGE_TAKEN = "testosterone:damage_taken_key";
        private static final String END_OF_BLOCK_TICK = "testosterone:end_of_block_tick";

        @SubscribeEvent
        public static void onLivingHurt(LivingDamageEvent.Pre event) {
            boolean hasTie = false;
            boolean matej = false;

            LivingEntity entity = event.getEntity();

            boolean hasEffect = entity.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);
            boolean notBlocking = !entity.isBlocking();

            if (hasEffect && notBlocking) {
                if (CuriosApi.getCuriosInventory(event.getEntity()).isPresent()) {
                    ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(event.getEntity()).get();


                    if (curiosInventory.findFirstCurio(testosteroneModItems.TIE.get()).isPresent()) {
                        hasTie = true;
                        matej = curiosInventory.findFirstCurio(testosteroneModItems.TIE.get()).get().stack().getDisplayName().getString().equals("[matej]");
                    }
                }

                long currentTick = ServerLifecycleHooks.getCurrentServer().overworld().getGameTime();
                int amplifier = entity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT).getAmplifier() + 1;

                int damageTaken = entity.getPersistentData().getInt(DAMAGE_TAKEN);
                long endOfBlockTick = entity.getPersistentData().getLong(END_OF_BLOCK_TICK);

                if (event.getSource().type().msgId().equals("genericKill") && !matej) {
//                    event.setCanceled(false);

                } else if (currentTick < endOfBlockTick) {
                    damageTaken += (int) event.getOriginalDamage();

                    if (hasTie && damageTaken > 100) {
                        damageTaken = 100;
                    }

                    entity.getPersistentData().putInt(DAMAGE_TAKEN, damageTaken);
                    event.setNewDamage(0);

                    if (entity instanceof ServerPlayer player) {
                        if (event.getOriginalDamage() >= 100) {
                            testosteroneModTriggers.DAMAGE_TAKEN.get().trigger(player);
                        }
                    }
                } else if (currentTick < endOfBlockTick + ((long) damageTaken * testosteroneConfigs.server().multiplier.get()) / amplifier) {
//                    event.setCanceled(false);

                } else {
                    endOfBlockTick = currentTick + ((long) testosteroneConfigs.server().duration.get() * amplifier);
                    entity.getPersistentData().putLong(END_OF_BLOCK_TICK, endOfBlockTick);

                    entity.getPersistentData().putLong(BEGIN_TICK, currentTick);

                    if (hasTie && event.getOriginalDamage() > testosteroneConfigs.server().maxDamage.get()) {
                        damageTaken = testosteroneConfigs.server().maxDamage.get();
                    } else {
                        damageTaken = (int) event.getOriginalDamage();
                    }

                    entity.getPersistentData().putInt(DAMAGE_TAKEN, damageTaken);

                    event.setNewDamage(0);

                    if (entity instanceof ServerPlayer player) {
                        if (event.getOriginalDamage() >= 100) {
                            testosteroneModTriggers.DAMAGE_TAKEN.get().trigger(player);
                        }
                    }
                }

                if (entity instanceof Player) {
                    long endOfCoolDownTick = endOfBlockTick + ((long) damageTaken * testosteroneConfigs.server().multiplier.get()) / amplifier;

                    long[] toSend = {endOfCoolDownTick, entity.getPersistentData().getLong(BEGIN_TICK), (long) testosteroneConfigs.server().duration.get() * amplifier};
                    PacketDistributor.sendToPlayer((ServerPlayer) entity, new hudS2CPacket(toSend));
                }
            }
        }
    }

    public testosteroneEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF8C0A);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        ResourceLocation estrogenEffectId = ResourceLocation.fromNamespaceAndPath("estrogen", "estrogen");
        Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.getHolder(estrogenEffectId);

        if (effect.isPresent() && livingEntity.hasEffect(effect.get())) {
            livingEntity.removeEffect(effect.get());
            livingEntity.removeEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);
        }

        return super.applyEffectTick(livingEntity, amplifier);
    }
}