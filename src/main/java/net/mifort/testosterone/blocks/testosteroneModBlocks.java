package net.mifort.testosterone.blocks;

import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.mayaqq.estrogen.registry.EstrogenSoundTypes;
import net.mifort.testosterone.blocks.CT.testosteroneSpriteShifts;
import net.mifort.testosterone.items.testosteroneModFoods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneModBlocks {
    public static final BlockEntry<Block> LAYERED_AEQUALIS = REGISTRATE.block("layered_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .onRegister(CreateRegistrate.blockModel(() -> testosteroneSpriteShifts.LAYERED_AEQUALIS_PROVIDER))
            .simpleItem()
            .register();

    public static final BlockEntry<ConnectedPillarBlock> AEQUALIS_PILLAR = REGISTRATE.block("aequalis_pillar", ConnectedPillarBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .onRegister(CreateRegistrate.blockModel(() -> testosteroneSpriteShifts.AEQUALIS_PILLAR_PROVIDER))
            .simpleItem()
            .register();



    public static final BlockEntry<testosteronePillBox> TESTOSTERONE_PILL_BLOCK = REGISTRATE.block("testosterone_pill_box", testosteronePillBox::new)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .properties(p -> p.strength(1f, 1f)
                    .sound(EstrogenSoundTypes.PILL_BOX))
            .simpleItem()
            .register();


    // ALL AEQUALIS STONE
    public static final BlockEntry<Block> AEQUALIS = REGISTRATE.block("aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
/////////
    public static final BlockEntry<Block> CUT_AEQUALIS = REGISTRATE.block("cut_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> CUT_AEQUALIS_STAIRS = REGISTRATE.block("cut_aequalis_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<SlabBlock> CUT_AEQUALIS_SLAB = REGISTRATE.block("cut_aequalis_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<WallBlock> CUT_AEQUALIS_WALL = REGISTRATE.block("cut_aequalis_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
///
    public static final BlockEntry<Block> POLISHED_CUT_AEQUALIS = REGISTRATE.block("polished_cut_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> POLISHED_CUT_AEQUALIS_STAIRS = REGISTRATE.block("polished_cut_aequalis_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<SlabBlock> POLISHED_CUT_AEQUALIS_SLAB = REGISTRATE.block("polished_cut_aequalis_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<WallBlock> POLISHED_CUT_AEQUALIS_WALL = REGISTRATE.block("polished_cut_aequalis_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
///
    public static final BlockEntry<Block> CUT_AEQUALIS_BRICKS = REGISTRATE.block("cut_aequalis_bricks", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> CUT_AEQUALIS_BRICK_STAIRS = REGISTRATE.block("cut_aequalis_brick_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<SlabBlock> CUT_AEQUALIS_BRICK_SLAB = REGISTRATE.block("cut_aequalis_brick_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register(); 
    public static final BlockEntry<WallBlock> CUT_AEQUALIS_BRICK_WALL = REGISTRATE.block("cut_aequalis_brick_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
///
    public static final BlockEntry<Block> SMALL_AEQUALIS_BRICKS = REGISTRATE.block("small_aequalis_bricks", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> SMALL_AEQUALIS_BRICK_STAIRS = REGISTRATE.block("small_aequalis_brick_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
    public static final BlockEntry<SlabBlock> SMALL_AEQUALIS_BRICK_SLAB = REGISTRATE.block("small_aequalis_brick_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register(); 
    public static final BlockEntry<WallBlock> SMALL_AEQUALIS_BRICK_WALL = REGISTRATE.block("small_aequalis_brick_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();
/////////


    public static final BlockEntry<Block> SMOOTH_AEQUALIS = REGISTRATE.block("smooth_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> SMOOTH_DIAMOND_AEQUALIS = REGISTRATE.block("smooth_diamond_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> SMOOTH_DARK_DIAMOND_AEQUALIS = REGISTRATE.block("smooth_dark_diamond_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<bigBricks> BIG_AEQUALIS_BRICKS = REGISTRATE.block("big_aequalis_bricks", bigBricks::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();








    // PIZZA TOWER
    public static final BlockEntry<johnRock> JOHN_ROCK = REGISTRATE.block("john_rock", johnRock::new)
            .initialProperties(() -> Blocks.OBSIDIAN)
            .properties(p -> p.sound(SoundType.STONE).isSuffocating((pState, pLevel, pPos) -> !pState.getValue(johnRock.TOGGLED)).lightLevel(s -> 12).isViewBlocking((pState, pLevel, pPos) -> !pState.getValue(johnRock.TOGGLED)))
            .item((block, properties) -> new BlockItem(block, properties.rarity(Rarity.EPIC)))
            .build()
            .register();

    public static final BlockEntry<Block> CHEESE_BLOCK = REGISTRATE.block("cheese_block", Block::new)
            .initialProperties(() -> Blocks.HONEYCOMB_BLOCK)
            .item((block, properties) -> new BlockItem(block, properties.food(testosteroneModFoods.CHEESE_BLOCK)))
            .build()
            .register();

    public static final BlockEntry<Block> FRAGILE_COPYCAT_BLOCK = REGISTRATE.block("fragile_copycat_block", Block::new)
            .properties(p -> p.sound(SoundType.METAL))
            .simpleItem()
            .register();


    public static void register() {}
}
