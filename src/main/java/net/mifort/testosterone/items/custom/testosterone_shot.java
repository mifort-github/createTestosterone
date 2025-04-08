package net.mifort.testosterone.items.custom;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class testosterone_shot extends Item {
    public testosterone_shot(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            pLivingEntity.addEffect(new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(), 6000, 1, false, false, true));
        }

        if (pLivingEntity instanceof Player player && !player.getAbilities().instabuild) {
            pStack.shrink(1);
        }

        pLevel.playSound(
                null,
                pLivingEntity.getX(),
                pLivingEntity.getY(),
                pLivingEntity.getZ(),
                SoundEvents.BOTTLE_EMPTY,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        return pStack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
}
