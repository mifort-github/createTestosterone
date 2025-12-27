package net.mifort.testosterone.packages;

import com.simibubi.create.content.logistics.box.PackageStyles.PackageStyle;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.items.testosteroneModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class TestosteronePackageStyles {
    public static final String TYPE = "testosterone:testosterone_cardboard";
    private static final Random RANDOM = new Random();


    public static final List<PackageStyle> TESTOSTERONE_PILL_STYLES = List.of(
            new PackageStyle(TYPE, 12, 12, 23f, false),
            new PackageStyle(TYPE, 10, 12, 22f, false),
            new PackageStyle(TYPE, 10, 8, 18f, false),
            new PackageStyle(TYPE, 12, 10, 21f, false)
    );

    private static Set<Item> allowedItemsToBeCounted;

    private static Set<Item> getAllowedItemsToBeCounted() {
        if (allowedItemsToBeCounted == null) {
            allowedItemsToBeCounted = Set.of(
                    testosteroneModItems.TESTOSTERONE_PILL.get(),
                    testosteroneModItems.TRENBOLONE_SHOT.get(),
                    testosteroneModBlocks.TESTOSTERONE_PILL_BLOCK.asItem(),
                    testosteroneModBlocks.TRENBOLONE_VIAL.asItem()
            );
        }
        return allowedItemsToBeCounted;
    }

    public static ItemStack containing(TestosteroneItemHandler stacks) {
        if (isMajorityOfItemsTestosteroneItems(stacks)) {
            ItemStack box = new ItemStack(
                    testosteroneModItems.ALL_TESTOSTERONE_PILL_BOXES.get(RANDOM.nextInt(testosteroneModItems.ALL_TESTOSTERONE_PILL_BOXES.size()))
            );

            CompoundTag compound = new CompoundTag();
            compound.put("Items", stacks.serializeNBT());
            box.setTag(compound);

            return box;
        }
        return null;
    }

    private static boolean isMajorityOfItemsTestosteroneItems(TestosteroneItemHandler stacks) {
        Map<Item, Integer> itemToAmount = new HashMap<>();

        for (int i = 0; i < stacks.getSlots(); i++) {
            ItemStack stack = stacks.getStackInSlot(i);
            Item item = stack.getItem();

            int amount = itemToAmount.getOrDefault(item, 0);
            amount += stack.getCount();
            itemToAmount.put(item, amount);
        }

        int testosteroneCount = 0;

        for (Item x : getAllowedItemsToBeCounted()) {
            if (x instanceof BlockItem) {
                testosteroneCount += itemToAmount.getOrDefault(x, 0) * 9;
            } else {
                testosteroneCount += itemToAmount.getOrDefault(x, 0);
            }
        }

        int otherCount = itemToAmount.entrySet().stream()
                .filter(e -> !getAllowedItemsToBeCounted().contains(e.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();

        return testosteroneCount > otherCount;
    }
}
