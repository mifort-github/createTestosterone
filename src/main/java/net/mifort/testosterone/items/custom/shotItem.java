package net.mifort.testosterone.items.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class shotItem extends Item {
    MobEffect mobEffect;
    int amplifier;
    boolean glint;

    public shotItem(Properties pProperties, MobEffect mobEffect, int amplifier, boolean glint) {
        super(pProperties);
        this.mobEffect = mobEffect;
        this.amplifier = amplifier;
        this.glint = glint;
    }

    public shotItem(Properties pProperties, MobEffect mobEffect, int amplifier) {
        super(pProperties);
        this.mobEffect = mobEffect;
        this.amplifier = amplifier;
        this.glint = false;
    }

    public shotItem(Properties pProperties, MobEffect mobEffect) {
        super(pProperties);
        this.mobEffect = mobEffect;
        this.amplifier = 0;
        this.glint = false;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            pLivingEntity.addEffect(new MobEffectInstance(mobEffect, 6000, amplifier, false, false, true));
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

    @Override
    public boolean isFoil(ItemStack pStack) {
        return this.glint;
    }
}
