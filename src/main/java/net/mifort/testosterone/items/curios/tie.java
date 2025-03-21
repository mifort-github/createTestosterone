package net.mifort.testosterone.items.curios;

import net.mifort.testosterone.client.beardModel;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class tie extends Item implements ICurioItem {
    beardModel BeardModel;

    public tie() {
        super(new Item.Properties().stacksTo(1).defaultDurability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        // Your ticking logic here
        LivingEntity entity = slotContext.entity();

        MobEffectInstance effect = slotContext.entity().getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get());

        if (effect != null) {
            if (effect.getAmplifier() == 0) {
                MobEffectInstance effectInstance = new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(),
                        effect.getDuration(), 1, effect.isAmbient(), effect.isVisible(), effect.showIcon());

                entity.addEffect(effectInstance);
            }
        }
    }
}