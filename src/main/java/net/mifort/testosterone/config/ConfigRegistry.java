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







    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_DURATION;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> TESTOSTERONE_MAX_DAMAGE;


    public static final ForgeConfigSpec.ConfigValue<Integer> JOHN_ROCK_LIMIT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> JOHN_ROCK_VERTICAL;
    public static final ForgeConfigSpec.ConfigValue<Integer> JOHN_ROCK_RANGE;








    static {
        CLIENT_BUILDER.push("Beard");

        RENDER_BEARD = CLIENT_BUILDER.comment("Whether the beard should render on yourself and others.").define("Beard", true);

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


        CLIENT_SPEC = CLIENT_BUILDER.build();






        SERVER_BUILDER.push("Testosterone Effect");

        TESTOSTERONE_DURATION = SERVER_BUILDER.comment("Duration of invincibility per level of testosterone in ticks.").defineInRange("Duration", 40, 0, Integer.MAX_VALUE);
        TESTOSTERONE_MULTIPLIER = SERVER_BUILDER.comment("By how much is damage taken multiplied. (affects cooldown duration)").defineInRange("Multiplier", 10, 1, Integer.MAX_VALUE);
        TESTOSTERONE_MAX_DAMAGE = SERVER_BUILDER.comment("The damage limit when using testosterone and a tie.").defineInRange("Damage Limit", 100, 1, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.push("Roid Rage Effect");


        SERVER_BUILDER.pop();

        SERVER_BUILDER.push("John Rock");

        JOHN_ROCK_LIMIT = SERVER_BUILDER.comment("How many John rocks can get powered from 1 source.").defineInRange("Powered Amount", 4096, 4, Integer.MAX_VALUE);
        JOHN_ROCK_VERTICAL = SERVER_BUILDER.comment("Should it also convert vertically").define("Vertical", false);
        JOHN_ROCK_RANGE = SERVER_BUILDER.comment("Range of conversion of john rocks").defineInRange("Conversion Range", 3, 1, Integer.MAX_VALUE);


        SERVER_BUILDER.pop();
        SERVER_SPEC = SERVER_BUILDER.build();
    }
}
