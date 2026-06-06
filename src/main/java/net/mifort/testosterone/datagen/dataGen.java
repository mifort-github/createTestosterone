package net.mifort.testosterone.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.packages.TestosteronePackageStyles;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

public class dataGen {

    public static void registerLang(RegistrateLangProvider provider) {

        addResourcePackTranslatable(provider,
                "programmer_art",
                "Testosterone Programmer Art",
                "§fProgrammer Art for the Testosterone mod.");

        addEffects(provider, testosteroneModEffects.TESTOSTERONE_EFFECT);
        addEffects(provider, testosteroneModEffects.ROID_RAGE_EFFECT);
//        addEffects(provider, testosteroneModEffects.AFTERLIFE_EFFECT, false);

        addSoundTranslatable(provider, "john_rock_deactivation", "John Rock Deactivated");
        addSoundTranslatable(provider, "john_rock_activation", "John Rock Activated");
        addSoundTranslatable(provider, "mach_1_sfx", "Mach I Run");
        addSoundTranslatable(provider, "mach_2_sfx", "Mach II Run");
        addSoundTranslatable(provider, "ground_slam_sfx", "Ground pound");
        addSoundTranslatable(provider, "enemy_hit_sfx", "Run hit");
        addSoundTranslatable(provider, "rat_sounds", "Rat sounds");
        addSoundTranslatable(provider, "rat_run", "Ratty spins");
        addSoundTranslatable(provider, "rat_hurts", "Rat hurts");

        addAdvancementTranslatable(provider, "beer_mug", "Rock And Stone!", "Brew a mug of beer.");
        addAdvancementTranslatable(provider, "cheese_block", "The Big Cheese", "Make a cheese block.");
        addAdvancementTranslatable(provider, "cholesterol", "Heart Stopper", "Collect a bucket of Cholesterol.");
        addAdvancementTranslatable(provider, "damage", "Dumb Ways To... Live?", "Negate 100 damage with the Man Power effect");
        addAdvancementTranslatable(provider, "inebriate", "Why Would You Do That", "Get poisoned from drinking too much beer.");
        addAdvancementTranslatable(provider, "ride", "The Gustavo & Brick Hour", "Ride a Stupid Rat.");
        addAdvancementTranslatable(provider, "roadkill", "Can't stop the A-Train, baby!", "Run over an enemy with roid rage.");
        addAdvancementTranslatable(provider, "testosterone", "Create: Testosterone", "An addon for the Estrogen create addon that adds testosterone items.");
        addAdvancementTranslatable(provider, "testosterone_liquid", "Bro Juice", "Collect a bucket of Testosterone.");
        addAdvancementTranslatable(provider, "testosterone_pill", "I'm- [TITLE CARD]", "Make a Testosterone Pill.");
        addAdvancementTranslatable(provider, "testosterone_protein_bar", "Nutritious Steroids", "Eat a bar with high protein (and testosterone) content.");
        addAdvancementTranslatable(provider, "tie", "Nanomachines, son!", "Craft a stylish Tie.");
        addAdvancementTranslatable(provider, "trenbolone_liquid", "Not a Gear that spins...", "Collect a bucket of Trenbolone.");

        addCategoryTranslatable(provider, "decantation", "Decantation");

        addPackagesToLang(provider, TestosteronePackageStyles.TESTOSTERONE_TYPE, "Testosterone Pill Package");
        addPackagesToLang(provider, TestosteronePackageStyles.TRENBOLONE_TYPE, "Trenbolone Package");

        addItemDescription(provider, testosteroneModItems.TESTOSTERONE_PILL.get(), "Temporary Source Of _Man Power_.");
        addItemDescription(provider, testosteroneModItems.TESTOSTERONE_SHOT.get(), "Temporary Source Of _Man Power_ II.");

        addItemDescription(provider, testosteroneModItems.TRENBOLONE_SHOT.get(),
                "Temporary Source Of _Roid Rage_. Run on _Water_, hit enemies to _Damage_ them, release shift for a _Super Jump_, shift in air to _Ground Slam_");
        addItemDescription(provider, testosteroneModItems.BETTER_TRENBOLONE_SHOT.get(),
                "Temporary Source Of _Roid Rage_ II. Run on _Water_, hit enemies to _Damage_ them, release shift for a _Super Jump_, shift in air to _Ground Slam_");

        addItemDescription(provider, testosteroneModItems.BEER_MUG.get(),
                "The perfect drink to share with the lads - _Don't_ drink _Too_ much Though");

//        addItemDescription(provider, testosteroneModItems.AFTERLIFE_TOTEM.get(),
//                "WORK IN PROGRESS NOT INTENDED FOR USE");

        addItemDescription(provider, testosteroneFluids.ESTRONE_FLUID.getBucket().get(),
                "W.I.P.");

        addItemDescription(provider, testosteroneModItems.TIE.get(),
                "A Tie that gives you _Knockback Resistance_. Useful with the _Man Power_ effect.");

        addItemDescription(provider, testosteroneModItems.WHEY_PROTEIN.get(),
                "Blaze burner _Fuel_.");

        addItemDescription(provider, testosteroneModBlocks.FRAGILE_COPYCAT_BLOCK.asItem(),
                "Only true Italians can _Run Through_ these");

        addItemDescription(provider, testosteroneModBlocks.AEQUALIS.asItem(),
                "A _strange stone_ formed when §kestrone§r and §ktestosterone§r touch.");

        addPonderTranslatable(provider, "bell_conversion.header", "Bell Conversion");
        addPonderTranslatable(provider, "john_rock.header", "John Rock");
        addPonderTranslatable(provider, "decanter_main.header", "Converting Fluids in The Decanter Centrifuge");

        addPonderTranslatable(provider, "bell_conversion.text_1", "When a Peculiar Bell is rung...");
        addPonderTranslatable(provider, "bell_conversion.text_2", "...any cracked pillar block in an 7x1x7 area of the bell...");
        addPonderTranslatable(provider, "bell_conversion.text_3", "...has a chance to turn into John Rock");
        addPonderTranslatable(provider, "bell_conversion.text_4", "This process can be automated with redstone/deployers and drills");

        addPonderTranslatable(provider, "john_rock.text_1", "John Rocks change state when given a redstone signal");
        addPonderTranslatable(provider, "john_rock.text_2", "Multiple John Rocks can be placed next to each other");
        addPonderTranslatable(provider, "john_rock.text_3", "While in the inactive state, any entity can go through the block");

        addPonderTranslatable(provider, "decanter_main.text_1", "The Decanter Centrifuge converts fluids into other fluids between Tanks");
        addPonderTranslatable(provider, "decanter_main.text_2", "The centrifuge requires at least 100 RPM to work, connected at either the top or the bottom...");
        addPonderTranslatable(provider, "decanter_main.text_3", "...with the sides used for fluid conversion.");
        addPonderTranslatable(provider, "decanter_main.text_4", "Centrifuges can be stacked on top of each other.");
    }

    private static void addItemDescription(RegistrateLangProvider provider, Item item, String translation) {
        provider.add(item.getDescriptionId() + ".tooltip.summary", translation);
    }

    private static void addPackagesToLang(RegistrateLangProvider provider, ResourceLocation id, String translation) {
        provider.add("item." + id.getNamespace() + "." + id.getPath() + "_package", translation);
    }

    private static void addEffects(RegistrateLangProvider provider, Holder<MobEffect> effect) {
        ResourceLocation id = effect.unwrapKey().orElseThrow().location();

        provider.add("effect." + id.getNamespace() + "." + id.getPath(), toTitleCase(id));

        String s = id.getPath();

        provider.add("item.minecraft.potion.effect." + s + "_potion", "Potion of " + toTitleCase(id));
        provider.add("item.minecraft.splash_potion.effect." + s + "_potion", "Splash Potion of " + toTitleCase(id));
        provider.add("item.minecraft.lingering_potion.effect." + s + "_potion", "Lingering Potion of " + toTitleCase(id));
        provider.add("item.minecraft.tipped_arrow.effect." + s + "_potion", "Arrow of " + toTitleCase(id));
    }

    private static void addEffects(RegistrateLangProvider provider, Holder<MobEffect> effect, boolean addPotions) {
        ResourceLocation id = effect.unwrapKey().orElseThrow().location();

        provider.add("effect." + id.getNamespace() + "." + id.getPath(), toTitleCase(id));

        String s = id.getPath();

        if (addPotions) {
            provider.add("item.minecraft.potion.effect." + s + "_potion", "Potion of " + toTitleCase(id));
            provider.add("item.minecraft.splash_potion.effect." + s + "_potion", "Splash Potion of " + toTitleCase(id));
            provider.add("item.minecraft.lingering_potion.effect." + s + "_potion", "Lingering Potion of " + toTitleCase(id));
            provider.add("item.minecraft.tipped_arrow.effect." + s + "_potion", "Arrow of " + toTitleCase(id));
        }
    }

    private static String toTitleCase(ResourceLocation resourceLocation) {
        String input = resourceLocation.getPath().replace('_', ' ');
        String[] parts = input.split(" ");

        StringBuilder sb = new StringBuilder();

        for (String p : parts) {
            if (p.isEmpty()) continue;

            sb.append(Character.toUpperCase(p.charAt(0)))
                    .append(p.substring(1))
                    .append(" ");
        }

        return sb.toString().trim();
    }

    private static void addAdvancementTranslatable(RegistrateLangProvider provider,
                                                   String advancementId,
                                                   String title,
                                                   String description) {

        provider.add(
                "advancement." + testosterone.MOD_ID + "." + advancementId + ".title",
                title);

        provider.add(
                "advancement." + testosterone.MOD_ID + "." + advancementId + ".description",
                description);
    }

    private static void addResourcePackTranslatable(RegistrateLangProvider provider,
                                                    String packId,
                                                    String title,
                                                    String description) {

        provider.add(
                "pack." + testosterone.MOD_ID + "." + packId + ".title",
                title);

        provider.add(
                "pack." + testosterone.MOD_ID + "." + packId + ".description",
                description);
    }

    private static void addSoundTranslatable(RegistrateLangProvider provider,
                                             String id,
                                             String subtitle) {

        provider.add(
                "sounds." + testosterone.MOD_ID + "." + id,
                subtitle);
    }

    private static void addCategoryTranslatable(RegistrateLangProvider provider,
                                                String categoryId,
                                                String title) {

        provider.add(
                "category." + testosterone.MOD_ID + "." + categoryId,
                title);
    }

    private static void addPonderTranslatable(RegistrateLangProvider provider,
                                              String key,
                                              String title) {
        provider.add(testosterone.MOD_ID + ".ponder." + key, title);
    }
}