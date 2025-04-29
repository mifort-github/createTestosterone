package net.mifort.testosterone.items;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
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
                        pOutput.accept(testosteroneModItems.TESTOSTERONE_PILL);
                        pOutput.accept(testosteroneModItems.TESTOSTERONE_SHOT);
                        pOutput.accept(testosteroneModItems.TESTOSTERONE_PROTEIN_BAR);
                        pOutput.accept(testosteroneModItems.BEER_MUG);

//                        pOutput.accept(testosteroneModItems.AFTERLIFE_TOTEM);

                        pOutput.accept(testosteroneModBlocks.TESTOSTERONE_PILL_BLOCK.get());
                        pOutput.accept(testosteroneModBlocks.JOHN_ROCK.get());
                        pOutput.accept(testosteroneModBlocks.AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.AEQUALIS_STONE_STAIRS.get());
                        pOutput.accept(testosteroneModBlocks.AEQUALIS_STONE_SLAB.get());
                        pOutput.accept(testosteroneModBlocks.AEQUALIS_STONE_WALL.get());
                        pOutput.accept(testosteroneModBlocks.SMOOTH_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.SMOOTH_DIAMOND_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.SMOOTH_DARK_DIAMOND_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.BIG_BRICKS.get());

                        pOutput.accept(testosteroneFluids.CHOLESTEROL_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.DILUTED_ZINC_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.TESTOSTERONE_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.BEER_FLUID.getBucket().get());

                        pOutput.accept(tippedArrow(testosteroneModPotions.TESTOSTERONE_POTION.get()));

                        pOutput.accept(testosteroneModItems.TIE.get());
                        pOutput.accept(tie(DyeColor.byId(7).name().toLowerCase()));

                        for (int pId = 0; pId < 16; pId++) {
                            pOutput.accept(tie(DyeColor.byId(pId).name().toLowerCase()));
                        }
                    })
                    .build());

    public static ItemStack tippedArrow(Potion potion) {
        ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
        PotionUtils.setPotion(stack, potion);
        return stack;
    }

    public static ItemStack tie(String color) {
        ItemStack stack = new ItemStack(testosteroneModItems.TIE);
        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("color", color);
        stack.setTag(nbtData);
        return stack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
