package net.mifort.testosterone.items.curios;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

public class tie extends Item implements ICurioItem {

    public tie(Properties pProperties) {
        super(new Item.Properties().stacksTo(1).durability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getDisplayName().getString().equals("[matej]")) {
            MobEffectInstance effect = slotContext.entity().getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT);

            if (effect != null) {
                if (effect.getAmplifier() == 0) {
                    MobEffectInstance effectInstance = new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT,
                            effect.getDuration(), 1, effect.isAmbient(), effect.isVisible(), effect.showIcon());

                    slotContext.entity().addEffect(effectInstance);
                }
            }
        }
    }

    public static ItemStack getTieByColor(String color) {
        ItemStack stack = new ItemStack(testosteroneModItems.TIE.get());
        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("color", color);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbtData));
        return stack;
    }

    @EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onKnockBackEvent(LivingKnockBackEvent event) {
            if (CuriosApi.getCuriosInventory(event.getEntity()).isPresent()) {
                ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(event.getEntity()).get();

                if (curiosInventory.findFirstCurio(testosteroneModItems.TIE.get()).isPresent()) {
                    event.setCanceled(true);
                }
            }
        }
    }
}