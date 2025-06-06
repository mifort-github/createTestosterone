package net.mifort.testosterone.items.curios;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

public class tie extends Item implements ICurioItem {

    public tie(Properties pProperties) {
        super(new Item.Properties().stacksTo(1).defaultDurability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.getDisplayName().getString().equals("[matej]")) {
            MobEffectInstance effect = slotContext.entity().getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get());

            if (effect != null) {
                if (effect.getAmplifier() == 0) {
                    MobEffectInstance effectInstance = new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(),
                            effect.getDuration(), 1, effect.isAmbient(), effect.isVisible(), effect.showIcon());

                    slotContext.entity().addEffect(effectInstance);
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

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onKnockBackEvent(LivingKnockBackEvent event) {
            if (CuriosApi.getCuriosInventory(event.getEntity()).resolve().isPresent()) {
                ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(event.getEntity()).resolve().get();

                if (curiosInventory.findFirstCurio(testosteroneModItems.TIE.get()).isPresent()) {
                    event.setCanceled(true);
                }
            }
        }
    }
}