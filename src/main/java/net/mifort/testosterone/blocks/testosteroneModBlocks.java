package net.mifort.testosterone.blocks;

import com.tterrag.registrate.util.entry.BlockEntry;
import dev.mayaqq.estrogen.registry.EstrogenSoundTypes;
import net.minecraft.world.level.block.*;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneModBlocks {

    public static final BlockEntry<testosteronePillBox> TESTOSTERONE_PILL_BLOCK = REGISTRATE.block("testosterone_pill_box", testosteronePillBox::new)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .properties(p -> p.strength(1f, 1f)
                    .sound(EstrogenSoundTypes.PILL_BOX))
            .simpleItem()
            .register();


    // ALL AEQUALIS STONE
    public static final BlockEntry<Block> AEQUALIS_STONE = REGISTRATE.block("aequalis_stone", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> AEQUALIS_CUT = REGISTRATE.block("aequalis_cut", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> AEQUALIS_DIAMOND_CUT = REGISTRATE.block("aequalis_diamond_cut", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> AEQUALIS_DARK_DIAMOND_CUT = REGISTRATE.block("aequalis_dark_diamond_cut", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<bigBricks> BIG_BRICKS = REGISTRATE.block("big_bricks", bigBricks::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> AEQUALIS_STONE_STAIRS = REGISTRATE.block("aequalis_stone_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> AEQUALIS_STONE_SLAB = REGISTRATE.block("aequalis_stone_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<WallBlock> AEQUALIS_STONE_WALL = REGISTRATE.block("aequalis_stone_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .simpleItem()
            .register();


    // PIZZA TOWER
    public static final BlockEntry<johnRock> JOHN_ROCK = REGISTRATE.block("john_rock", johnRock::new)
            .initialProperties(() -> Blocks.OBSIDIAN)
            .properties(p -> p.sound(SoundType.STONE).noOcclusion())
            .simpleItem()
            .register();


    public static void register() {}
}
