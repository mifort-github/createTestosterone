package net.mifort.testosterone.effects;

import net.mifort.testosterone.testosterone;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class testosteroneModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, testosterone.MOD_ID);

    public static final Holder<MobEffect> TESTOSTERONE_EFFECT = EFFECTS.register("man_power", testosteroneEffect::new);

//    public static final Holder<MobEffect> AFTERLIFE_EFFECT = EFFECTS.register("afterlife", afterlifeEffect::new);

    public static final Holder<MobEffect> ROID_RAGE_EFFECT = EFFECTS.register("roid_rage", roidRageEffect::new);

    public static void registerEffects(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
