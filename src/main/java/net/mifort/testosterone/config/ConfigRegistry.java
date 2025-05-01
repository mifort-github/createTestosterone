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





    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_DURATION;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_MAX_DAMAGE;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Integer> ABILITY_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Double> JUMP_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> SPEED_MULTIPLIER;

    public static final ForgeConfigSpec.ConfigValue<Integer> JOHN_ROCK_LIMIT;







    static {
        CLIENT_BUILDER.push("Beard");

        RENDER_BEARD = CLIENT_BUILDER.comment("Whether the beard should render on yourself and others.").define("Beard", true);

        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.push("Testosterone Button");

        BUTTON_X_OFFSET = CLIENT_BUILDER.comment("Move left and right.").define("X Offset", -23);
        BUTTON_Y_OFFSET = CLIENT_BUILDER.comment("Move up and down.").define("Y Offset", 24);
        SHOW_BUTTON = CLIENT_BUILDER.comment("Show the button on the create main menu.").define("Show Button", true);

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
        JUMP_MULTIPLIER = SERVER_BUILDER.comment("How much does the speed counter affect jump height.").defineInRange("Jump Height", 0.01, 0, Double.MAX_VALUE);
        SPEED_MULTIPLIER = SERVER_BUILDER.comment("How much does the speed counter affect speed.").defineInRange("Speed Multiplier", 0.001, 0, Double.MAX_VALUE);

        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("John Rock");

        JOHN_ROCK_LIMIT = SERVER_BUILDER.comment("How many John rocks can get powered from 1 source.").defineInRange("Powered Amount", 4096, 4, Integer.MAX_VALUE);


        SERVER_BUILDER.pop();
        SERVER_SPEC = SERVER_BUILDER.build();
    }
}
