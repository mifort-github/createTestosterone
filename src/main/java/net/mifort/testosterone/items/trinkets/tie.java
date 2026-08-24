package net.mifort.testosterone.items.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.items.testosteroneModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class tie extends TrinketItem {

	public tie(Item.Properties properties) {
		super(new Item.Properties().stacksTo(1));
	}

	@Override
	public void tick(ItemStack stack, SlotReference slot, net.minecraft.world.entity.LivingEntity entity) {
		if (stack.getDisplayName().getString().equals("[matej]")) {
			MobEffectInstance effect = entity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);

			if (effect != null) {
				if (effect.getAmplifier() == 0) {
					MobEffectInstance effectInstance = new MobEffectInstance(
							testosteroneModEffects.TESTOSTERONE_EFFECT,
							effect.getDuration(),
							1,
							effect.isAmbient(),
							effect.isVisible(),
							effect.showIcon()
					);

					entity.addEffect(effectInstance);
				}
			}
		}
	}

	public static ItemStack getTieByColor(String color) {
		ItemStack stack = new ItemStack(testosteroneModItems.TIE);

		CompoundTag nbtData = new CompoundTag();
		nbtData.putString("color", color);
		stack.setTag(nbtData);

		return stack;
	}

	public static boolean hasTie(net.minecraft.world.entity.LivingEntity entity) {
		Optional<dev.emi.trinkets.api.TrinketComponent> component = TrinketsApi.getTrinketComponent(entity);

		return component.map(trinketComponent -> trinketComponent.isEquipped(testosteroneModItems.TIE.get())).orElse(false);
	}
}
