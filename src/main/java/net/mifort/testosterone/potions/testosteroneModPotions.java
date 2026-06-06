package net.mifort.testosterone.potions;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class testosteroneModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, testosterone.MOD_ID);

    public static final DeferredHolder<Potion, Potion> TESTOSTERONE_POTION =
            POTIONS.register("man_power_potion",
                    () -> new Potion(new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT, 12000, 0)));

    public static final DeferredHolder<Potion, Potion> ROID_RAGE_POTION =
            POTIONS.register("roid_rage_potion",
                    () -> new Potion(new MobEffectInstance(testosteroneModEffects.ROID_RAGE_EFFECT, 12000, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}