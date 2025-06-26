package net.mifort.testosterone.items;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.curios.tie;
import net.mifort.testosterone.items.custom.CheeseOnAStick;
import net.mifort.testosterone.items.custom.testosteroneShot;
import net.mifort.testosterone.items.custom.trenboloneShot;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.mifort.testosterone.items.custom.beerMug;
import net.minecraftforge.registries.RegistryObject;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, testosterone.MOD_ID);


    public static final ItemEntry<Item> TESTOSTERONE_PILL =
            REGISTRATE.item("testosterone_pill", Item::new)
                    .properties(p -> p.food(testosteroneModFoods.TESTOSTERONE_PILL).rarity(Rarity.RARE).stacksTo(16))
                    .register();

    public static final ItemEntry<Item> TESTOSTERONE_PROTEIN_BAR =
            REGISTRATE.item("testosterone_protein_bar", Item::new)
                    .properties(p -> p.food(testosteroneModFoods.TESTOSTERONE_PROTEIN_BAR).rarity(Rarity.RARE))
                    .register();


    public static final ItemEntry<testosteroneShot> TESTOSTERONE_SHOT =
            REGISTRATE.item("testosterone_shot", testosteroneShot::new)
                    .properties(p -> p.food(testosteroneModFoods.BLANK).rarity(Rarity.EPIC).stacksTo(16))
                    .register();
    //for mifart (hec chillaxx)
    public static final ItemEntry<trenboloneShot> TRENBOLONE_SHOT =
            REGISTRATE.item("trenbolone_shot", trenboloneShot::new)
                    .properties(p -> p.food(testosteroneModFoods.BLANK).rarity(Rarity.EPIC).stacksTo(16))
                    .register();

    public static final ItemEntry<beerMug> BEER_MUG =
            REGISTRATE.item("beer_mug", beerMug::new)
                    .properties(p -> p.food(testosteroneModFoods.BLANK).stacksTo(16))
                    .register();


    public static final ItemEntry<tie> TIE =
            REGISTRATE.item("tie", tie::new)
                    .register();

    public static final ItemEntry<Item> AFTERLIFE_TOTEM =
            REGISTRATE.item("totem_of_afterlife", Item::new)
                    .properties(p -> p.rarity(Rarity.EPIC).stacksTo(1))
                    .register();

    public static final ItemEntry<CheeseOnAStick> CHEESE_ON_A_STICK =
            REGISTRATE.item("cheese_on_a_stick", CheeseOnAStick::new)
                    .properties(p -> p.stacksTo(1).defaultDurability(256))
                    .register();

    public static final ItemEntry<Item> CHEESE_CURDS =
            REGISTRATE.item("cheese_curds", Item::new)
                    .properties(p -> p.stacksTo(64).food(testosteroneModFoods.CHEESE_CURDS))
                    .register();

    public static final ItemEntry<Item> WHEY_PROTEIN =
            REGISTRATE.item("whey_protein", Item::new)
                    .properties(p -> p.stacksTo(64))
                    .register();

    public static final ItemEntry<Item> RAT_FUR =
            REGISTRATE.item("rat_fur", Item::new)
                    .properties(p -> p.stacksTo(64))
                    .register();

    public static final RegistryObject<Item> STUPID_RAT_SPAWN_EGG = ITEMS.register("stupid_rat_spawn_egg",
            () -> new ForgeSpawnEggItem(testosteroneEntities.RAT, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}