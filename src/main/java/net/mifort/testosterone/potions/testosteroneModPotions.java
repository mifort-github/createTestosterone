package net.mifort.testosterone.potions;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class testosteroneModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, testosterone.MOD_ID);


    public static final RegistryObject<Potion> TESTOSTERONE_POTION =
            POTIONS.register("testosterone_potion",
                    () -> new Potion(new MobEffectInstance(testosteroneModEffects.TESTOSTERONE_EFFECT.get(), 12000, 0)));

    public static final RegistryObject<Potion> TRENBOLONE_POTION =
            POTIONS.register("trenbolone_potion",
                    () -> new Potion(new MobEffectInstance(testosteroneModEffects.ROID_RAGE_EFFECT.get(), 12000, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
