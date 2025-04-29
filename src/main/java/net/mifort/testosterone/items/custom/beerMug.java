package net.mifort.testosterone.items.custom;

import net.mifort.testosterone.testosterone;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class beerMug extends Item {
    public static final int BEER_DURATION = 3600;
    public static final int BEER_AMPLIFIER = 1;
    private static final String BEER_DOWNSIDE = "testosterone:beer_downside_duration";

    public beerMug(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        if (!level.isClientSide) {
            int current_downside = living.getPersistentData().getInt(BEER_DOWNSIDE);
            living.getPersistentData().putInt(BEER_DOWNSIDE, current_downside + BEER_DURATION);

            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, BEER_DURATION, BEER_AMPLIFIER));

            if (living instanceof Player player) {
                ItemStack bowl = new ItemStack(Items.BOWL);
                if (!player.getInventory().add(bowl)) {
                    player.drop(bowl, false);
                }
            }
        }

        if (living instanceof Player player && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }


        return stack;
    }


    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class downside {
        @SubscribeEvent
        public static void onTick(LivingEvent.LivingTickEvent event) {
            if (event.getEntity() instanceof Player player) {
                int current = player.getPersistentData().getInt(BEER_DOWNSIDE);

                if (current > BEER_DURATION) {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, true, true));
                }

                if (current > 2 * BEER_DURATION) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0, false, true, true));
                }

                if (current > 3 * BEER_DURATION) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0, false, true, true));
                }

                if (current > 4 * BEER_DURATION) {
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0, false, true, true));
                }

                if (current > 5 * BEER_DURATION) {
                    player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, false, true, true));
                }

                if (current > 0) {
                    player.getPersistentData().putInt(BEER_DOWNSIDE, current - 1);
                }
            }
        }

        @SubscribeEvent
        public static void onClear(MobEffectEvent.Remove event) {
            event.getEntity().getPersistentData().putInt(BEER_DOWNSIDE, 0);
        }
    }
}
