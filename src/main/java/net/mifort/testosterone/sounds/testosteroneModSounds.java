package net.mifort.testosterone.sounds;

import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class testosteroneModSounds {

	public static final SoundEvent JOHN_ROCK_DEACTIVATION = registerSoundEvent("john_rock_deactivation");
	public static final SoundEvent JOHN_ROCK_ACTIVATION = registerSoundEvent("john_rock_activation");
	public static final SoundEvent MACH_1_SFX = registerSoundEvent("mach_1_sfx");
	public static final SoundEvent MACH_2_SFX = registerSoundEvent("mach_2_sfx");
	public static final SoundEvent GROUND_SLAM_SFX = registerSoundEvent("ground_slam_sfx");
	public static final SoundEvent ENEMY_HIT_SFX = registerSoundEvent("enemy_hit_sfx");
	public static final SoundEvent RAT_SNIFF1 = registerSoundEvent("rat_sniff1");
	public static final SoundEvent RAT_SNIFF2 = registerSoundEvent("rat_sniff2");
	public static final SoundEvent RAT_SQUEAK1 = registerSoundEvent("rat_squeak1");
	public static final SoundEvent RAT_SQUEAK2 = registerSoundEvent("rat_squeak2");
	public static final SoundEvent RAT_RUN = registerSoundEvent("rat_run");
	public static final SoundEvent RAT_HURT1 = registerSoundEvent("rat_hurt1");
	public static final SoundEvent RAT_HURT2 = registerSoundEvent("rat_hurt2");


	private static SoundEvent registerSoundEvent(String name) {
		ResourceLocation id = new ResourceLocation(testosterone.MOD_ID, name);

		return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
	}

	public static void registerSounds() {

	}
}
