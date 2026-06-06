package net.mifort.testosterone.items;

import net.mifort.testosterone.items.curios.tie;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class testosteroneModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, testosterone.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TESTOSTERONE_TAB = CREATIVE_MOD_TABS.register("testosterone_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(testosteroneModItems.TESTOSTERONE_PILL.get()))
                    .title(Component.literal("Testosterone"))
                    .displayItems((pParameters, pOutput) -> {
//                        pOutput.accept(tippedArrow(testosteroneModPotions.TESTOSTERONE_POTION));
//                        pOutput.accept(tippedArrow(testosteroneModPotions.ROID_RAGE_POTION));
                        pOutput.accept(tie.getTieByColor(DyeColor.byId(7).name().toLowerCase()));

                        for (int pId = 0; pId < 16; pId++) {
                            pOutput.accept(tie.getTieByColor(DyeColor.byId(pId).name().toLowerCase()));
                        }

                    })
                    .build());


    public static ItemStack tippedArrow(Holder<Potion> potion) {
        ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
        stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
        return stack;
    }

    public static ItemStack addBooleanNbt(ItemStack itemStack, String key, boolean bool) {
        CompoundTag nbtData = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        nbtData.putBoolean(key, bool);
        itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbtData));
        return itemStack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
