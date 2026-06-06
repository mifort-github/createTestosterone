package net.mifort.testosterone.sounds;

import net.mifort.testosterone.testosterone;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class testosteroneModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, testosterone.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> JOHN_ROCK_DEACTIVATION = registerSoundEvents("john_rock_deactivation");
    public static final DeferredHolder<SoundEvent, SoundEvent> JOHN_ROCK_ACTIVATION = registerSoundEvents("john_rock_activation");
    public static final DeferredHolder<SoundEvent, SoundEvent> MACH_1_SFX = registerSoundEvents("mach_1_sfx");
    public static final DeferredHolder<SoundEvent, SoundEvent> MACH_2_SFX = registerSoundEvents("mach_2_sfx");
    public static final DeferredHolder<SoundEvent, SoundEvent> GROUND_SLAM_SFX = registerSoundEvents("ground_slam_sfx");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENEMY_HIT_SFX = registerSoundEvents("enemy_hit_sfx");

    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_SNIFF1 = registerSoundEvents("rat_sniff1");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_SNIFF2 = registerSoundEvents("rat_sniff2");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_SQUEAK1 = registerSoundEvents("rat_squeak1");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_SQUEAK2 = registerSoundEvents("rat_squeak2");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_RUN = registerSoundEvents("rat_run");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_HURT1 = registerSoundEvents("rat_hurt1");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAT_HURT2 = registerSoundEvents("rat_hurt2");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(testosterone.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}