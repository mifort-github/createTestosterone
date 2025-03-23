package net.mifort.testosterone.items;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class testosteroneModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, testosterone.MOD_ID);

    // add items to creative tab
    public static final RegistryObject<CreativeModeTab> TESTOSTERONE_TAB = CREATIVE_MOD_TABS.register("testosterone_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(testosteroneModItems.TESTOSTERONE_PILL.get()))
                    .title(Component.translatable("creativetab.testosterone_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(testosteroneFluids.CHOLESTEROL_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.DILUTED_ZINC_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.TESTOSTERONE_FLUID.getBucket().get());

                        pOutput.accept(tippedArrow(testosteroneModPotions.TESTOSTERONE_POTION.get()));

                        pOutput.accept(testosteroneModBlocks.TESTOSTERONE_PILL_BLOCK.get());
                    })
                    .build());

    public static ItemStack tippedArrow(Potion potion) {
        ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
        PotionUtils.setPotion(stack, potion);
        return stack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
