package net.mifort.testosterone.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigRegistry {
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();


    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec.ConfigValue<Boolean> RENDER_BEARD;

    public static final ForgeConfigSpec.ConfigValue<Integer> BUTTON_X_OFFSET;
    public static final ForgeConfigSpec.ConfigValue<Integer> BUTTON_Y_OFFSET;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_BUTTON;

    public static final ForgeConfigSpec.ConfigValue<Boolean> RENDER_TESTOSTERONE_INVINCIBLE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_R_INVINCIBLE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_G_INVINCIBLE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_B_INVINCIBLE;

    public static final ForgeConfigSpec.ConfigValue<Boolean> RENDER_TESTOSTERONE_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_R_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_G_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_B_COOLDOWN;

    public static final ForgeConfigSpec.ConfigValue<Boolean> RENDER_TRAIL;
    public static final ForgeConfigSpec.ConfigValue<Double> TRAIL_MIN_RENDER_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Double> TRAIL_OFFSET;








    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_DURATION;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_MAX_DAMAGE;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Integer> ABILITY_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Double> JUMP_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> SPEED_MULTIPLIER;

    public static final ForgeConfigSpec.ConfigValue<Integer> JOHN_ROCK_LIMIT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> JOHN_ROCK_VERTICAL;
    public static final ForgeConfigSpec.ConfigValue<Integer> JOHN_ROCK_RANGE;

    public static final ForgeConfigSpec.ConfigValue<Double> RAT_BOOST_MULTIPLIER;

    public static final ForgeConfigSpec.ConfigValue<Boolean> DISPLAY_SPEED;

    public static final ForgeConfigSpec.ConfigValue<Double> TREN_IN_AIR_MUL;
    public static final ForgeConfigSpec.ConfigValue<Integer> TRAIL_DURATION;
    public static final ForgeConfigSpec.ConfigValue<Double> FALL_DAMAGE_RADIUS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ALLOW_ELYTRA;







    static {
        CLIENT_BUILDER.push("Beard / Mustache");

        RENDER_BEARD = CLIENT_BUILDER.comment("Whether the beard / mustache should render on yourself and others.").define("Beard / Mustache", true);

        CLIENT_BUILDER.pop();


        CLIENT_BUILDER.push("Testosterone Button");

        BUTTON_X_OFFSET = CLIENT_BUILDER.comment("Move left and right.").define("X Offset", -23);
        BUTTON_Y_OFFSET = CLIENT_BUILDER.comment("Move up and down.").define("Y Offset", 24);
        SHOW_BUTTON = CLIENT_BUILDER.comment("Show the button on the create main menu.").define("Show Button", true);

        CLIENT_BUILDER.pop();


        CLIENT_BUILDER.push("Testosterone Overlay");

        RENDER_TESTOSTERONE_INVINCIBLE = CLIENT_BUILDER.comment("Render the invincibility overlay.").define("Render While Invincible", true);
        RENDER_TESTOSTERONE_COOLDOWN = CLIENT_BUILDER.comment("Render the overlay while testosterone is on cooldown.").define("Render While On Cooldown", true);

        TESTOSTERONE_R_INVINCIBLE = CLIENT_BUILDER.comment("Red component of the overlay while invincible.").defineInRange("Red Of Invincible", 255, 0, 255);
        TESTOSTERONE_G_INVINCIBLE = CLIENT_BUILDER.comment("Green component of the overlay while invincible.").defineInRange("Green Of Invincible", 209, 0, 255);
        TESTOSTERONE_B_INVINCIBLE = CLIENT_BUILDER.comment("Blue component of the overlay while invincible.").defineInRange("Blue Of Invincible", 119, 0, 255);

        TESTOSTERONE_R_COOLDOWN = CLIENT_BUILDER.comment("Red component of the overlay while invincible.").defineInRange("Red Of Cooldown", 255, 0, 255);
        TESTOSTERONE_G_COOLDOWN = CLIENT_BUILDER.comment("Green component of the overlay while invincible.").defineInRange("Green Of Cooldown", 255, 0, 255);
        TESTOSTERONE_B_COOLDOWN = CLIENT_BUILDER.comment("Blue component of the overlay while invincible.").defineInRange("Blue Of Cooldown", 0, 0, 255);

        CLIENT_BUILDER.pop();


        CLIENT_BUILDER.push("Roid Rage Effect");

        DISPLAY_SPEED = CLIENT_BUILDER.comment("Display the speed on the action bar.").define("Display Speed", true);
        RENDER_TRAIL = CLIENT_BUILDER.comment("Render the trenbolone trail.").define("Render Trail", true);
        TRAIL_MIN_RENDER_DISTANCE = CLIENT_BUILDER.comment("How close to the player the trail should render.").defineInRange("Minimum Render Distance", 2, 0, Double.MAX_VALUE);
        TRAIL_OFFSET = CLIENT_BUILDER.comment("The offset of the trail").defineInRange("Trail Offset", 0, -2048D, 2048D);


        CLIENT_BUILDER.pop();


        CLIENT_SPEC = CLIENT_BUILDER.build();






        SERVER_BUILDER.push("Testosterone Effect");

        TESTOSTERONE_DURATION = SERVER_BUILDER.comment("Duration of invincibility per level of testosterone in ticks.").defineInRange("Duration", 40, 0, Integer.MAX_VALUE);
        TESTOSTERONE_MULTIPLIER = SERVER_BUILDER.comment("By how much is damage taken multiplied. (affects cooldown duration)").defineInRange("Multiplier", 10, 1, Integer.MAX_VALUE);
        TESTOSTERONE_MAX_DAMAGE = SERVER_BUILDER.comment("The damage limit when using testosterone and a tie.").defineInRange("Damage Limit", 100, 1, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.push("Roid Rage Effect");

        MAX_SPEED = SERVER_BUILDER.comment("Maximum speed achieved per level of roid rage.").defineInRange("Max Speed", 200, 0, Integer.MAX_VALUE);
        ABILITY_SPEED = SERVER_BUILDER.comment("The speed that needs to be achieved to get abilities. (running on fluids, high jumping)").defineInRange("Ability Speed", 50, 0, Integer.MAX_VALUE);
        JUMP_MULTIPLIER = SERVER_BUILDER.comment("How much does the speed counter affect jump height.").defineInRange("Jump Height", 0.012, 0, Double.MAX_VALUE);
        SPEED_MULTIPLIER = SERVER_BUILDER.comment("How much does the speed counter affect speed.").defineInRange("Speed Multiplier", 0.0015, 0, Double.MAX_VALUE);

        TREN_IN_AIR_MUL = SERVER_BUILDER.comment("How far you jump with the roid rage effect.").defineInRange("In Air Multiplier", 0.5, 0, 10);
        TRAIL_DURATION = SERVER_BUILDER.comment("Duration of the trenbolone trail").defineInRange("Trail Duration", 5, 0, Integer.MAX_VALUE);
        FALL_DAMAGE_RADIUS = SERVER_BUILDER.comment("Radius of the ground slam.").defineInRange("Ground Slam Radius", 4, 0, Double.MAX_VALUE);
        ALLOW_ELYTRA = SERVER_BUILDER.comment("Radius of the ground slam.").define("Allow Elytra", false);

        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("John Rock");

        JOHN_ROCK_LIMIT = SERVER_BUILDER.comment("How many John rocks can get powered from 1 source.").defineInRange("Powered Amount", 4096, 4, Integer.MAX_VALUE);
        JOHN_ROCK_VERTICAL = SERVER_BUILDER.comment("Should it also convert vertically").define("Vertical", false);
        JOHN_ROCK_RANGE = SERVER_BUILDER.comment("Range of conversion of John rocks").defineInRange("Conversion Range", 3, 1, Integer.MAX_VALUE);


        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("Rat");

        RAT_BOOST_MULTIPLIER = SERVER_BUILDER.comment("How much faster the rat goes when ridden and boosted using cheese on a stick.").defineInRange("Speed Multiplier", 5D, 1D, Double.MAX_VALUE);

        SERVER_BUILDER.pop();
        SERVER_SPEC = SERVER_BUILDER.build();
    }
}
