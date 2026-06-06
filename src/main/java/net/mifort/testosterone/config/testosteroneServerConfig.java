package net.mifort.testosterone.config;

import net.createmod.catnip.config.ConfigBase;
import org.jetbrains.annotations.NotNull;

public class testosteroneServerConfig extends ConfigBase {
    @Override
    public @NotNull String getName() {
        return "server";
    }

    public final ConfigGroup testosterone_effect = group(1, "testosterone_effect",
            Comments.testosterone_effect);

    public final ConfigInt duration = i(40, 0, Integer.MAX_VALUE, "duration",
            Comments.duration);
    public final ConfigInt multiplier = i(10, 1, Integer.MAX_VALUE, "multiplier",
            Comments.multiplier);
    public final ConfigInt maxDamage = i(100, 1, Integer.MAX_VALUE, "max_damage",
            Comments.maxDamage);

    public final ConfigGroup roid_rage_mechanics = group(1, "roid_rage_mechanics",
            Comments.roid_rage_mechanics);

    public final ConfigInt maxSpeed = i(200, 0, Integer.MAX_VALUE, "max_speed",
            Comments.maxSpeed);
    public final ConfigInt abilitySpeed = i(50, 0, Integer.MAX_VALUE, "ability_speed",
            Comments.abilitySpeed);
    public final ConfigFloat jumpMultiplier = f(0.012f, 0, Float.MAX_VALUE, "jump_multiplier",
            Comments.jumpMultiplier);
    public final ConfigFloat speedMultiplier = f(0.0015f, 0, Float.MAX_VALUE, "speed_multiplier",
            Comments.speedMultiplier);
    public final ConfigFloat trenInAirMul = f(0.5f, 0, 10, "tren_in_air_mul",
            Comments.trenInAirMul);
    public final ConfigInt trailDuration = i(5, 0, Integer.MAX_VALUE, "trail_duration",
            Comments.trailDuration);
    public final ConfigFloat fallDamageRadius = f(1f, 0, Float.MAX_VALUE, "fall_damage_radius",
            Comments.fallDamageRadius);
    public final ConfigBool allowElytra = b(false, "allow_elytra",
            Comments.allowElytra);

    public final ConfigGroup john_rock = group(1, "john_rock",
            Comments.john_rock);

    public final ConfigInt johnRockLimit = i(4096, 4, Integer.MAX_VALUE, "john_rock_limit",
            Comments.johnRockLimit);
    public final ConfigBool johnRockVertical = b(false, "john_rock_vertical",
            Comments.johnRockVertical);
    public final ConfigInt johnRockRange = i(3, 1, Integer.MAX_VALUE, "john_rock_range",
            Comments.johnRockRange);

    public final ConfigGroup rat = group(1, "rat",
            Comments.rat);

    public final ConfigFloat ratBoostMultiplier = f(5f, 1, Float.MAX_VALUE, "rat_boost_multiplier",
            Comments.ratBoostMultiplier);

    private static class Comments {
        static String testosterone_effect = "Testosterone Effect";
        static String duration = "Duration of invincibility per level of testosterone in ticks.";
        static String multiplier = "By how much is damage taken multiplied. (affects cooldown duration)";
        static String maxDamage = "The damage limit when using testosterone and a tie.";

        static String roid_rage_mechanics = "Roid Rage Mechanics";
        static String maxSpeed = "Maximum speed achieved per level of roid rage.";
        static String abilitySpeed = "The speed that needs to be achieved to get abilities. (running on fluids, high jumping)";
        static String jumpMultiplier = "How much does the speed counter affect jump height.";
        static String speedMultiplier = "How much does the speed counter affect speed.";
        static String trenInAirMul = "How far you jump with the roid rage effect.";
        static String trailDuration = "Duration of the trenbolone trail.";
        static String fallDamageRadius = "Radius of the ground slam.";
        static String allowElytra = "Allow using elytra while roid rage is active.";

        static String john_rock = "John Rock";
        static String johnRockLimit = "How many John rocks can get powered from 1 source.";
        static String johnRockVertical = "Should it also convert vertically.";
        static String johnRockRange = "Range of conversion of John rocks.";

        static String rat = "Rat";
        static String ratBoostMultiplier = "How much faster the rat goes when ridden and boosted using cheese on a stick.";
    }
}