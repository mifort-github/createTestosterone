package net.mifort.testosterone.items;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class testosteroneModFoods {
    public static final FoodProperties TESTOSTERONE_PILL = new FoodProperties.Builder()
            .nutrition(0)
            .saturationMod(0f)
            .alwaysEat()
            .fast()
            .effect(() -> new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(), 6000, 0, false, false, true), 1f)
            .build();

    public static final FoodProperties TESTOSTERONE_SHOT = new FoodProperties.Builder()
            .nutrition(0)
            .saturationMod(0f)
            .alwaysEat()
            .fast()
            .effect(() -> new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(), 6000, 1, false, false, true), 1f)
            .build();

    public static final FoodProperties TESTOSTERONE_PROTEIN_BAR = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(1.5f)
            .alwaysEat()
            .fast()
            .effect(() -> new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(), 6000, 0, false, false, true), 1f)
            .build();

    public static final FoodProperties ADD_SLOT_ITEM = new FoodProperties.Builder()
            .nutrition(0)
            .saturationMod(0f)
            .alwaysEat()
            .fast()
            .build();

    public static final FoodProperties RESET_SLOTS_ITEM = new FoodProperties.Builder()
            .nutrition(0)
            .saturationMod(0f)
            .alwaysEat()
            .fast()
            .build();
}
