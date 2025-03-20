package net.mifort.testosterone.items;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.mifort.testosterone.items.advanced.addSlotItem;
import net.mifort.testosterone.items.advanced.resetSlotsItem;
import net.mifort.testosterone.items.curios.tie;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, testosterone.MOD_ID);


    public static final ItemEntry<Item> TESTOSTERONE_PILL =
            REGISTRATE.item("testosterone_pill", Item::new)
                    .properties(p -> p.food(testosteroneModFoods.TESTOSTERONE_PILL).rarity(Rarity.RARE).stacksTo(16))
                    .tab(testosteroneModCreativeModTabs.TESTOSTERONE_TAB.getKey())
                    .register();

    public static final ItemEntry<Item> TESTOSTERONE_PROTEIN_BAR =
            REGISTRATE.item("testosterone_protein_bar", Item::new)
                    .properties(p -> p.food(testosteroneModFoods.TESTOSTERONE_PROTEIN_BAR).rarity(Rarity.RARE))
                    .tab(testosteroneModCreativeModTabs.TESTOSTERONE_TAB.getKey())
                    .register();

    public static final ItemEntry<Item> TESTOSTERONE_SHOT =
            REGISTRATE.item("testosterone_shot", Item::new)
                    .properties(p -> p.food(testosteroneModFoods.TESTOSTERONE_SHOT).rarity(Rarity.EPIC).stacksTo(16))
                    .tab(testosteroneModCreativeModTabs.TESTOSTERONE_TAB.getKey())
                    .register();

    public static final ItemEntry<addSlotItem> ADD_SLOT_ITEM =
            REGISTRATE.item("add_slot_item", addSlotItem::new)
                    .properties(p -> p.food(testosteroneModFoods.ADD_SLOT_ITEM).stacksTo(1))
                    .tab(testosteroneModCreativeModTabs.TESTOSTERONE_TAB.getKey())
                    .register();


    public static final ItemEntry<resetSlotsItem> RESET_SLOTS_ITEM =
            REGISTRATE.item("reset_slots_item", resetSlotsItem::new)
                    .properties(p -> p.food(testosteroneModFoods.RESET_SLOTS_ITEM).stacksTo(1))
                    .tab(testosteroneModCreativeModTabs.TESTOSTERONE_TAB.getKey())
                    .register();

    public static final RegistryObject<Item> TIE = ITEMS.register("tie", tie::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}