package net.mifort.testosterone.effects;

import net.mifort.testosterone.testosterone;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class testosteroneModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, testosterone.MOD_ID);

    public static final RegistryObject<MobEffect> TESTOSTERONE_EFFECT = EFFECTS.register("testosterone", testosteroneEffect::new);

    public static final RegistryObject<MobEffect> AFTERLIFE_EFFECT = EFFECTS.register("afterlife_effect", afterlifeEffect::new);

    public static void registerEffects() {
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
