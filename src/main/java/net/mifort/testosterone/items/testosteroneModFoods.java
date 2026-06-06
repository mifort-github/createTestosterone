package net.mifort.testosterone.items;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class testosteroneModFoods {
    public static final FoodProperties TESTOSTERONE_PILL = new FoodProperties.Builder()
            .nutrition(0)
            .saturationModifier(0f)
            .alwaysEdible()
            .fast()
            .effect(() -> new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT, 6000, 0, false, false, true), 1f)
            .build();

    public static final FoodProperties TESTOSTERONE_PROTEIN_BAR = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(1.5f)
            .alwaysEdible()
            .fast()
            .effect(() -> new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT, 6000, 0, false, false, true), 1f)
            .build();

    public static final FoodProperties CHEESE_CURDS = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(1f)
            .build();

    public static final FoodProperties CHEESE_BLOCK = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(1f)
            .build();

    public static final FoodProperties BLANK = new FoodProperties.Builder().alwaysEdible().build();
}
