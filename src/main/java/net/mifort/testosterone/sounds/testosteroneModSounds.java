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

    public static final RegistryObject<SoundEvent> RAT_SNIFF1 = registerSoundEvents("rat_sniff1");
    public static final RegistryObject<SoundEvent> RAT_SNIFF2 = registerSoundEvents("rat_sniff2");
    public static final RegistryObject<SoundEvent> RAT_SQUEAK1 = registerSoundEvents("rat_squeak1");
    public static final RegistryObject<SoundEvent> RAT_SQUEAK2 = registerSoundEvents("rat_squeak2");
    public static final RegistryObject<SoundEvent> RAT_RUN = registerSoundEvents("rat_run");
    public static final RegistryObject<SoundEvent> RAT_HURT1 = registerSoundEvents("rat_hurt1");
    public static final RegistryObject<SoundEvent> RAT_HURT2 = registerSoundEvents("rat_hurt2");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(testosterone.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
