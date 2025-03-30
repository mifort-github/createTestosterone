package net.mifort.testosterone.effects;

import dev.mayaqq.estrogen.registry.effects.EstrogenEffect;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.network.packet.hudS2CPacket;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.testosterone;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class testosteroneEffect extends MobEffect {
    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class ForgeEvents {
        private static final String BEGIN_TICK = "begin_tick";
        private static final String DAMAGE_TAKEN = "damage_taken_key";
        private static final String END_OF_BLOCK_TICK = "end_of_block_tick";

        public static final int DURATION = 40;
        public static final int MULTIPLIER = 10;

        private static final int DAMAGE_LIMIT = 100;


        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            boolean hasTie = false;
            boolean matej = false;

            LivingEntity entity = event.getEntity();

            boolean hasEffect = entity.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get());
            boolean notBlocking = !entity.isBlocking();

            if (hasEffect && notBlocking) {
                if (CuriosApi.getCuriosInventory(event.getEntity()).resolve().isPresent()) {
                    ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(event.getEntity()).resolve().get();


                    if (curiosInventory.findFirstCurio(testosteroneModItems.TIE.get()).isPresent()) {
                        hasTie = true;
                        matej = curiosInventory.findFirstCurio(testosteroneModItems.TIE.get()).get().stack().getDisplayName().getString().equals("[matej]");
                    }
                }

                long currentTick = ServerLifecycleHooks.getCurrentServer().overworld().getGameTime();
                int amplifier = entity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get()).getAmplifier() + 1;

                int damageTaken = entity.getPersistentData().getInt(DAMAGE_TAKEN);
                long endOfBlockTick = entity.getPersistentData().getLong(END_OF_BLOCK_TICK);

                if (event.getSource().type().msgId().equals("genericKill") && !matej) {
                    event.setCanceled(false);

                } else if (currentTick < endOfBlockTick) {
                    damageTaken += (int) event.getAmount();

                    if (hasTie && damageTaken > 100) {
                        damageTaken = 100;
                    }

                    entity.getPersistentData().putInt(DAMAGE_TAKEN, damageTaken);
                    event.setCanceled(true);

                } else if (currentTick < endOfBlockTick + ((long) damageTaken * MULTIPLIER) / amplifier) {
                    event.setCanceled(false);

                } else {
                    endOfBlockTick = currentTick + ((long) DURATION * amplifier);
                    entity.getPersistentData().putLong(END_OF_BLOCK_TICK, endOfBlockTick);

                    entity.getPersistentData().putLong(BEGIN_TICK, currentTick);

                    // Update DAMAGE_TAKEN with the current damage amount
                    if (hasTie && event.getAmount() > DAMAGE_LIMIT) {
                        damageTaken = DAMAGE_LIMIT;
                    } else {
                        damageTaken = (int) event.getAmount();
                    }

                    entity.getPersistentData().putInt(DAMAGE_TAKEN, damageTaken);

                    event.setCanceled(true);
                }

                if (entity instanceof Player) {
                    long endOfCoolDownTick = endOfBlockTick + ((long) damageTaken * MULTIPLIER) / amplifier;

                    long[] toSend = {endOfCoolDownTick, entity.getPersistentData().getLong(BEGIN_TICK), (long) DURATION * amplifier};

                    testosteroneModMessages.sendToPlayer(new hudS2CPacket(toSend), (ServerPlayer) entity);
                }
            }
        }
    }

    public testosteroneEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xD69E8C);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (EstrogenEffect.byId(0) != null) {
            if (livingEntity.hasEffect(EstrogenEffect.byId(0))) {
                livingEntity.removeEffect(EstrogenEffect.byId(0));
                livingEntity.removeEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}