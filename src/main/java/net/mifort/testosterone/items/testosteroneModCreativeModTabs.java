package net.mifort.testosterone.items;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.curios.tie;
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

    public static final RegistryObject<CreativeModeTab> TESTOSTERONE_TAB = CREATIVE_MOD_TABS.register("testosterone_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(testosteroneModItems.TESTOSTERONE_PILL.get()))
                    .title(Component.translatable("creativetab.testosterone_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(testosteroneModItems.TESTOSTERONE_PILL);
                        pOutput.accept(testosteroneModItems.TESTOSTERONE_SHOT);
                        pOutput.accept(testosteroneModItems.TESTOSTERONE_PROTEIN_BAR);
                        pOutput.accept(testosteroneModItems.BEER_MUG);
                        pOutput.accept(testosteroneModItems.TRENBOLONE_SHOT);
//                      pOutput.accept(testosteroneModItems.AFTERLIFE_TOTEM);
                        pOutput.accept(testosteroneModBlocks.TESTOSTERONE_PILL_BLOCK.get());
                        pOutput.accept(testosteroneModBlocks.DECANTER_CENTRIFUGE.get());
                        pOutput.accept(testosteroneModBlocks.TRENBOLONE_VIAL.get());
                        pOutput.accept(testosteroneModBlocks.JOHN_ROCK.get());
                        pOutput.accept(testosteroneModBlocks.FRAGILE_COPYCAT_BLOCK.get());


                        pOutput.accept(testosteroneModBlocks.CHEESE_BLOCK.get());
                        pOutput.accept(addBooleanNbt(testosteroneModItems.CHEESE_ON_A_STICK.get().getDefaultInstance(), "Boost", false));
                        pOutput.accept(testosteroneModItems.WHEY_PROTEIN);
                        pOutput.accept(testosteroneModItems.CHEESE_CURDS);

                        pOutput.accept(testosteroneModItems.RAT_FUR.get());
                        pOutput.accept(testosteroneModItems.STUPID_RAT_SPAWN_EGG.get());

                        //pOutput.accept(testosteroneModItems.STRONGMAN_PATCH.get());

                        pOutput.accept(tippedArrow(testosteroneModPotions.TESTOSTERONE_POTION.get()));
                        pOutput.accept(tippedArrow(testosteroneModPotions.TRENBOLONE_POTION.get()));

                        pOutput.accept(testosteroneFluids.CHOLESTEROL_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.DILUTED_ZINC_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.TESTOSTERONE_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.TRENBOLONE_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.BEER_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.WHEY_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.CHEESE_FLUID.getBucket().get());
                        pOutput.accept(testosteroneFluids.ESTRONE_FLUID.getBucket().get());



                        pOutput.accept(testosteroneModItems.TIE.get());
                        pOutput.accept(tie.getTieByColor(DyeColor.byId(7).name().toLowerCase()));

                        for (int pId = 0; pId < 16; pId++) {
                            pOutput.accept(tie.getTieByColor(DyeColor.byId(pId).name().toLowerCase()));
                        }

                        pOutput.accept(testosteroneModBlocks.CRACKED_PILLAR.get());

                        pOutput.accept(testosteroneModBlocks.AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_STAIRS.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_SLAB.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_WALL.get());

                        pOutput.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS_STAIRS.get());
                        pOutput.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS_SLAB.get());
                        pOutput.accept(testosteroneModBlocks.POLISHED_CUT_AEQUALIS_WALL.get());

                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICKS.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICK_STAIRS.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICK_SLAB.get());
                        pOutput.accept(testosteroneModBlocks.CUT_AEQUALIS_BRICK_WALL.get());

                        pOutput.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICKS.get());
                        pOutput.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICK_STAIRS.get());
                        pOutput.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICK_SLAB.get());
                        pOutput.accept(testosteroneModBlocks.SMALL_AEQUALIS_BRICK_WALL.get());
                        pOutput.accept(testosteroneModBlocks.LAYERED_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.AEQUALIS_PILLAR.get());


                        pOutput.accept(testosteroneModBlocks.SMOOTH_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.SMOOTH_DIAMOND_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.SMOOTH_DARK_DIAMOND_AEQUALIS.get());
                        pOutput.accept(testosteroneModBlocks.BIG_AEQUALIS_BRICKS.get());
                    })
                    .build());


    public static ItemStack tippedArrow(Potion potion) {
        ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
        PotionUtils.setPotion(stack, potion);
        return stack;
    }


    public static ItemStack addBooleanNbt(ItemStack itemStack, String key, boolean bool) {
        CompoundTag nbtData = new CompoundTag();
        nbtData.putBoolean(key, bool);
        itemStack.setTag(nbtData);
        return itemStack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
