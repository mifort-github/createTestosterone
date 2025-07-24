package net.mifort.testosterone.sounds;

import net.mifort.testosterone.testosterone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class testosteroneModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, testosterone.MOD_ID);

    public static final RegistryObject<SoundEvent> JOHN_ROCK_DEACTIVATION = registerSoundEvents("john_rock_deactivation");
    public static final RegistryObject<SoundEvent> JOHN_ROCK_ACTIVATION = registerSoundEvents("john_rock_activation");
    public static final RegistryObject<SoundEvent> MACH_1_SFX = registerSoundEvents("mach_1_sfx");
    public static final RegistryObject<SoundEvent> MACH_2_SFX = registerSoundEvents("mach_2_sfx");
    public static final RegistryObject<SoundEvent> GROUND_SLAM_SFX = registerSoundEvents("ground_slam_sfx");
    public static final RegistryObject<SoundEvent> ENEMY_HIT_SFX = registerSoundEvents("enemy_hit_sfx");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(testosterone.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
