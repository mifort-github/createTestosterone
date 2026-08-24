package net.mifort.testosterone.items.custom;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.items.testosteroneModItems;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class beerMug extends Item {

	public static final int BEER_DURATION = 3600;
	public static final int BEER_AMPLIFIER = 1;

	private static final String BEER_DOWNSIDE =
			"testosterone:beer_downside_duration";

	public beerMug(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
		if (!level.isClientSide) {

			int currentDownside = getBeerDownside(living);

			setBeerDownside(living, currentDownside + BEER_DURATION);

			living.addEffect(
					new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, BEER_DURATION, BEER_AMPLIFIER));

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
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	public static void registerBeerEvents() {

		ServerTickEvents.END_SERVER_TICK.register(server -> {

			for (ServerPlayer player : server.getPlayerList().getPlayers()) {

				int current = getBeerDownside(player);

				if (current <= 0) {
					continue;
				}

				boolean matej = hasMatejTie(player);

				if (!matej) {
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

						testosteroneAdvancementUtils.INEBRIATE.trigger(player);
					}
				}

				setBeerDownside(player, current - 1);
			}
		});
	}

	private static boolean hasMatejTie(ServerPlayer player) {

		Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player);

		if (component.isEmpty()) {
			return false;
		}

		for (var equipped : component.get().getAllEquipped()) {
			ItemStack stack = equipped.getB();

			if (stack.is(testosteroneModItems.TIE.get()) && stack.getDisplayName().getString().equals("[matej]")) {
				return true;
			}
		}

		return false;
	}

	private static int getBeerDownside(LivingEntity entity) {
		CompoundTag data = entity.getCustomData();

		return data.getInt(BEER_DOWNSIDE);
	}

	private static void setBeerDownside(LivingEntity entity, int value) {
		CompoundTag data = entity.getCustomData();

		data.putInt(BEER_DOWNSIDE, Math.max(0, value));
	}
}
