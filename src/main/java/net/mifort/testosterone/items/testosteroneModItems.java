package net.mifort.testosterone.items;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.mifort.testosterone.items.curios.tie;
import net.mifort.testosterone.items.custom.testosterone_shot;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.mifort.testosterone.items.custom.beerPint;

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
                    .tab(CreativeModeTabs.FOOD_AND_DRINKS)
                    .register();


    public static final ItemEntry<testosterone_shot> TESTOSTERONE_SHOT =
            REGISTRATE.item("testosterone_shot", testosterone_shot::new)
                    .properties(p -> p.food(testosteroneModFoods.BLANK).rarity(Rarity.EPIC).stacksTo(16))
                    .register();

    public static final ItemEntry<beerPint> BEER_PINT =
            REGISTRATE.item("beer_pint", beerPint::new)
                    .properties(p -> p.food(testosteroneModFoods.BLANK).stacksTo(16))
                    .register();


    public static final ItemEntry<tie> TIE =
            REGISTRATE.item("tie", tie::new)
                    .register();

    public static final ItemEntry<Item> AFTERLIFE_TOTEM =
            REGISTRATE.item("totem_of_afterlife", Item::new)
                    .properties(p -> p.rarity(Rarity.EPIC).stacksTo(1))
                    .register();


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}