package net.mifort.testosterone.datagen;

import com.tterrag.registrate.providers.ProviderType;
import net.createmod.ponder.foundation.PonderIndex;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.packages.TestosteronePackageStyles;
import net.mifort.testosterone.ponder.testosteronePonder;
import net.mifort.testosterone.testosterone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static net.mifort.testosterone.testosterone.REGISTRATE;

@Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class dataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        addResourcePackTranslatable("programmer_art", "Testosterone Programmer Art", "§fProgrammer Art for the Testosterone mod.");

        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            PonderIndex.addPlugin(new testosteronePonder());
            PonderIndex.getLangAccess().provideLang(testosterone.MOD_ID, provider::add);
        });

        addItemToLang(testosteroneModItems.STUPID_RAT_SPAWN_EGG);

        addEffects(testosteroneModEffects.TESTOSTERONE_EFFECT);
        addEffects(testosteroneModEffects.ROID_RAGE_EFFECT);
        addEffects(testosteroneModEffects.AFTERLIFE_EFFECT, false);

//            event.getGenerator().addProvider(
//                    event.includeServer(),
//                    new ForgeAdvancementProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(),
//                            event.getExistingFileHelper(), List.of(new advancementGenerator()))
//            );

        addSoundTranslatable("john_rock_deactivation", "John Rock Deactivated");
        addSoundTranslatable("john_rock_activation", "John Rock Activated");
        addSoundTranslatable("mach_1_sfx", "Mach I Run");
        addSoundTranslatable("mach_2_sfx", "Mach II Run");
        addSoundTranslatable("ground_slam_sfx", "Ground pound");
        addSoundTranslatable("enemy_hit_sfx", "Run hit");
        addSoundTranslatable("rat_sounds", "Rat sounds");
        addSoundTranslatable("rat_run", "Ratty spins");
        addSoundTranslatable("rat_hurts", "Rat hurts");


        addAdvancementTranslatable("beer_mug", "Rock And Stone!", "Brew a mug of beer.");
        addAdvancementTranslatable("cheese_block", "The Big Cheese", "Make a cheese block.");
        addAdvancementTranslatable("cholesterol", "Heart Stopper", "Collect a bucket of Cholesterol.");
        addAdvancementTranslatable("damage", "Dumb Ways To... Live?", "Negate 100 damage with the Man Power effect");
        addAdvancementTranslatable("inebriate", "Why Would You Do That", "Get poisoned from drinking too much beer.");
        addAdvancementTranslatable("ride", "The Gustavo & Brick Hour", "Ride a Stupid Rat.");
        addAdvancementTranslatable("roadkill", "Can't stop the A-Train, baby!", "Run over an enemy with roid rage.");
        addAdvancementTranslatable("testosterone", "Create: Testosterone", "An addon for the Estrogen create addon that adds testosterone items.");
        addAdvancementTranslatable("testosterone_liquid", "Bro Juice", "Collect a bucket of Testosterone.");
        addAdvancementTranslatable("testosterone_pill", "I'm- [TITLE CARD]", "Make a Testosterone Pill.");
        addAdvancementTranslatable("testosterone_protein_bar", "Nutritious Steroids", "Eat a bar with high protein (and testosterone) content.");
        addAdvancementTranslatable("tie", "Nanomachines, son!", "Craft a stylish Tie.");
        addAdvancementTranslatable("trenbolone_liquid", "Not a Gear that spins...", "Collect a bucket of Trenbolone.");

        addCategoryTranslatable("decantation","Decantation");

        addPackagesToLang(TestosteronePackageStyles.TESTOSTERONE_TYPE, "Testosterone Pill Package");
        addPackagesToLang(TestosteronePackageStyles.TRENBOLONE_TYPE, "Trenbolone Package");

        addItemDescription(testosteroneModItems.TESTOSTERONE_PILL.get(), "Temporary Source Of _Man Power_.");
        addItemDescription(testosteroneModItems.TESTOSTERONE_SHOT.get(), "Temporary Source Of _Man Power_ II.");

        addItemDescription(testosteroneModItems.TRENBOLONE_SHOT.get(), "Temporary Source Of _Roid Rage_. Run on _Water_, hit enemies to _Damage_ them, release shift for a _Super Jump_, shift in air to _Ground Slam_");
        addItemDescription(testosteroneModItems.BETTER_TRENBOLONE_SHOT.get(), "Temporary Source Of _Roid Rage_ II. Run on _Water_, hit enemies to _Damage_ them, release shift for a _Super Jump_, shift in air to _Ground Slam_");

        addItemDescription(testosteroneModItems.BEER_MUG.get(), "The perfect drink to share with the lads - _Don't_ drink _Too_ much Though");

        addItemDescription(testosteroneModItems.AFTERLIFE_TOTEM.get(), "WORK IN PROGRESS NOT INTENDED FOR USE");

        addItemDescription(testosteroneFluids.ESTRONE_FLUID.getBucket().get(), "W.I.P.");

        addItemDescription(testosteroneModItems.TIE.get(), "A Tie that gives you _Knockback Resistance_. Useful with the _Man Power_ effect.");

        addItemDescription(testosteroneModItems.WHEY_PROTEIN.get(), "Blaze burner _Fuel_.");

        addItemDescription(testosteroneModBlocks.FRAGILE_COPYCAT_BLOCK.asItem(), "Only true Italians can _Run Through_ these");

        addItemDescription(testosteroneModBlocks.AEQUALIS.asItem(), "A _strange stone_ formed when §kestrone§r and §ktestosterone§r touch.");

        addEntityName(testosteroneEntities.RAT.getId(), "Stupid Rat");
    }

    private static void addEntityName(ResourceLocation entity, String translation) {
        REGISTRATE.addLang("entity", entity, "Stupid Rat");
    }

    private static void addItemDescription(Item item, String translation) {
        REGISTRATE.addLang(item.getDescriptionId(), new ResourceLocation("tooltip", "summary"), translation);
    }

    private static void addPackagesToLang(ResourceLocation id, String translation) {
        REGISTRATE.addLang("item", id.withSuffix("_package"), translation);
    }

    private static void addItemToLang(RegistryObject<Item> item) {
        if (item.getId() != null) {
            REGISTRATE.addLang("item", item.getId(), toTitleCase(item.getId()));
        }
    }

    private static void addEffects(RegistryObject<MobEffect> effect) {
        if (effect.getId() != null) {
            REGISTRATE.addLang("effect", effect.getId(), toTitleCase(effect.getId()));

            String s = effect.getId().toString().replaceFirst("testosterone.", "");

            REGISTRATE.addLang("item", new ResourceLocation("potion.effect." + s + "_potion"), "Potion of " + toTitleCase(effect.getId()));
            REGISTRATE.addLang("item", new ResourceLocation("splash_potion.effect." + s + "_potion"), "Splash Potion of " + toTitleCase(effect.getId()));
            REGISTRATE.addLang("item", new ResourceLocation("lingering_potion.effect." + s + "_potion"), "Lingering Potion of " + toTitleCase(effect.getId()));
            REGISTRATE.addLang("item", new ResourceLocation("tipped_arrow.effect." + s + "_potion"), "Arrow of " + toTitleCase(effect.getId()));
        }
    }

    private static void addEffects(RegistryObject<MobEffect> effect, boolean addPotions) {
        if (effect.getId() != null) {
            REGISTRATE.addLang("effect", effect.getId(), toTitleCase(effect.getId()));

            String s = effect.getId().toString().replaceFirst("testosterone.", "");

            if (addPotions) {
                REGISTRATE.addLang("item", new ResourceLocation("potion.effect." + s + "_potion"), "Potion of " + toTitleCase(effect.getId()));
                REGISTRATE.addLang("item", new ResourceLocation("splash_potion.effect." + s + "_potion"), "Arrow of " + toTitleCase(effect.getId()));
                REGISTRATE.addLang("item", new ResourceLocation("lingering_potion.effect." + s + "_potion"), "Arrow of " + toTitleCase(effect.getId()));
                REGISTRATE.addLang("item", new ResourceLocation("tipped_arrow.effect." + s + "_potion"), "Arrow of " + toTitleCase(effect.getId()));
            }
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

    private static void addAdvancementTranslatable(String advancementId, String title, String description) {
        REGISTRATE.addLang("advancement", new ResourceLocation(testosterone.MOD_ID, advancementId + ".title"), title);
        REGISTRATE.addLang("advancement", new ResourceLocation(testosterone.MOD_ID, advancementId + ".description"), description);
    }

    private static void addResourcePackTranslatable(String packId, String title, String description) {
        REGISTRATE.addLang("pack", new ResourceLocation(testosterone.MOD_ID, packId + ".title"), title);
        REGISTRATE.addLang("pack", new ResourceLocation(testosterone.MOD_ID, packId + ".description"), description);
    }

    private static void addSoundTranslatable(String id, String subtitle) {
        REGISTRATE.addLang("sounds", new ResourceLocation(testosterone.MOD_ID, id), subtitle);
    }
    private static void addCategoryTranslatable(String categoryId, String title) {
        REGISTRATE.addLang("category", new ResourceLocation(testosterone.MOD_ID, categoryId), title);
    }
}
