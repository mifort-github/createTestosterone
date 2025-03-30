package net.mifort.testosterone.blocks;

import dev.mayaqq.estrogen.registry.EstrogenSoundTypes;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class testosteroneModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, testosterone.MOD_ID);


    public static final RegistryObject<Block> TESTOSTERONE_PILL_BLOCK = registerBlock("testosterone_pill_box",
            () -> new testosterone_pill_box(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(1f, 1f).sound(EstrogenSoundTypes.PILL_BOX)));



//    public static final RegistryObject<Block> AEQUALIS_STONE = registerBlock("aequalis_stone",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
//
//    public static final RegistryObject<Block> AEQUALIS_STONE_STAIRS = registerBlock("aequalis_stone_stairs",
//            () -> new StairBlock(() -> testosteroneModBlocks.AEQUALIS_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
//
//    public static final RegistryObject<Block> AEQUALIS_STONE_SLAB = registerBlock("aequalis_stone_slab",
//            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
//
//    public static final RegistryObject<Block> AEQUALIS_STONE_WALL = registerBlock("aequalis_stone_wall",
//            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return testosteroneModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
