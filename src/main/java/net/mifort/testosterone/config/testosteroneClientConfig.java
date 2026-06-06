package net.mifort.testosterone.config;

import net.createmod.catnip.config.ConfigBase;
import org.jetbrains.annotations.NotNull;

public class testosteroneClientConfig extends ConfigBase {
    @Override
    public @NotNull String getName() {
        return "client";
    }

    public final ConfigGroup client = group(0, "client",
            testosteroneClientConfig.Comments.client);


    public final ConfigGroup beardMustache = group(1, "beard_mustache",
            testosteroneClientConfig.Comments.beard_mustache);

    public final ConfigBool renderBeard = b(true, "render_beard",
            testosteroneClientConfig.Comments.render_beard);


    public final ConfigGroup testosteroneButton = group(1, "testosterone_button",
            testosteroneClientConfig.Comments.testosterone_button);

    public final ConfigInt buttonXOffset = i(-23, "x_offset",
            testosteroneClientConfig.Comments.button_x_offset);
    public final ConfigInt buttonYOffset = i(24, "y_offset",
            testosteroneClientConfig.Comments.button_y_offset);
    public final ConfigBool showButton = b(true, "show_button",
            testosteroneClientConfig.Comments.show_button);


    public final ConfigGroup testosteroneOverlay = group(1, "testosterone_overlay",
            testosteroneClientConfig.Comments.testosterone_overlay);

    public final ConfigBool renderTestosteroneInvincible = b(true, "render_testosterone_invincible",
            testosteroneClientConfig.Comments.render_testosterone_invincible);
    public final ConfigBool renderTestosteroneCooldown = b(true, "render_testosterone_cooldown",
            testosteroneClientConfig.Comments.render_testosterone_cooldown);

    public final ConfigInt testosteroneRInvincible = i(255, 0, 255, "red_of_invincible",
            testosteroneClientConfig.Comments.testosterone_r_invincible);
    public final ConfigInt testosteroneGInvincible = i(209, 0, 255, "green_of_invincible",
            testosteroneClientConfig.Comments.testosterone_g_invincible);
    public final ConfigInt testosteroneBInvincible = i(119, 0, 255, "blue_of_invincible",
            testosteroneClientConfig.Comments.testosterone_b_invincible);

    public final ConfigInt testosteroneRCooldown = i(255, 0, 255, "red_of_cooldown",
            testosteroneClientConfig.Comments.testosterone_r_cooldown);
    public final ConfigInt testosteroneGCooldown = i(255, 0, 255, "green_of_cooldown",
            testosteroneClientConfig.Comments.testosterone_g_cooldown);
    public final ConfigInt testosteroneBCooldown = i(0, 0, 255, "blue_of_cooldown",
            testosteroneClientConfig.Comments.testosterone_b_cooldown);


    public final ConfigGroup roidRageVisuals = group(1, "roid_rage_visuals",
            testosteroneClientConfig.Comments.roid_rage_visuals);

    public final ConfigBool displaySpeed = b(false, "display_speed",
            testosteroneClientConfig.Comments.display_speed);
    public final ConfigBool renderTrail = b(true, "render_trail",
            testosteroneClientConfig.Comments.render_trail);
    public final ConfigFloat trailMinRenderDistance = f(2f, 0f, Float.MAX_VALUE,
            "minimum_render_distance",
            testosteroneClientConfig.Comments.trail_min_render_distance);
    public final ConfigFloat trailOffset = f(0f, -2048f, 2048f,
            "trail_offset",
            testosteroneClientConfig.Comments.trail_offset);


    private static class Comments {
        static String client = "Client-only settings - If you're looking for general settings, look inside your worlds serverconfig folder!";


        static String beard_mustache = "Beard / Mustache";
        static String render_beard = "Whether the beard / mustache should render on yourself and others.";


        static String testosterone_button = "Testosterone Button";
        static String button_x_offset = "Move left and right.";
        static String button_y_offset = "Move up and down.";
        static String show_button = "Show Button";


        static String testosterone_overlay = "Testosterone Overlay";

        static String render_testosterone_invincible = "Render the invincibility overlay.";
        static String render_testosterone_cooldown = "Render the overlay while testosterone is on cooldown.";

        static String testosterone_r_invincible = "Red component of the overlay while invincible.";
        static String testosterone_g_invincible = "Green component of the overlay while invincible.";
        static String testosterone_b_invincible = "Blue component of the overlay while invincible.";

        static String testosterone_r_cooldown = "Red component of the overlay while on cooldown.";
        static String testosterone_g_cooldown = "Green component of the overlay while on cooldown.";
        static String testosterone_b_cooldown = "Blue component of the overlay while on cooldown.";


        static String roid_rage_visuals = "Roid Rage Visuals";

        static String display_speed = "Display the speed on the action bar.";
        static String render_trail = "Render the trenbolone trail.";
        static String trail_min_render_distance = "How close to the player the trail should render.";
        static String trail_offset = "The offset of the trail";
    }
}