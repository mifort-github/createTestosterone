package net.mifort.testosterone.blocks;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.mifort.testosterone.blocks.CT.testosteroneSpriteShifts;
import net.mifort.testosterone.blocks.blockModels.fragileCopycatModel;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlock;
import net.mifort.testosterone.items.testosteroneModFoods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneModBlocks {
    public static final BlockEntry<Block> LAYERED_AEQUALIS = REGISTRATE.block("layered_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().cubeColumn(ctx.getName(), prov.modLoc("block/aequalis_cut_layered"), prov.modLoc("block/aequalis_cut_cap"))))
            .onRegister(CreateRegistrate.blockModel(() -> testosteroneSpriteShifts.LAYERED_AEQUALIS_PROVIDER))
            .simpleItem()
            .register();

    public static final BlockEntry<ConnectedPillarBlock> AEQUALIS_PILLAR = REGISTRATE.block("aequalis_pillar", ConnectedPillarBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.axisBlock(ctx.get(),
                    prov.modLoc("block/aequalis_cut_pillar"), prov.modLoc("block/aequalis_cut_cap")))
            .onRegister(CreateRegistrate.blockModel(() -> testosteroneSpriteShifts.AEQUALIS_PILLAR_PROVIDER))
            .simpleItem()
            .register();

    private static SoundType resolvePillSound() {
        Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("estrogen", "estrogen_pill_block"));
        return b != null ? b.defaultBlockState().getSoundType() : SoundType.BONE_BLOCK;
    }

    public static final BlockEntry<testosteronePillBox> TESTOSTERONE_PILL_BLOCK =
            REGISTRATE.block("testosterone_pill_box", testosteronePillBox::new)
                    .initialProperties(() -> Blocks.OAK_PLANKS)
                    .properties(p -> p.strength(1f, 1f)
                            .sound(resolvePillSound()))
                    .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .blockstate((ctx, prov) -> {
                        ModelFile model = prov.models().cube(
                                ctx.getName(),
                                prov.modLoc("block/testosterone_pill_box/bottom"),
                                prov.modLoc("block/testosterone_pill_box/top"),
                                prov.modLoc("block/testosterone_pill_box/north"),
                                prov.modLoc("block/testosterone_pill_box/south"),
                                prov.modLoc("block/testosterone_pill_box/west"),
                                prov.modLoc("block/testosterone_pill_box/east")
                        ).texture("particle", prov.modLoc("block/testosterone_pill_box/top"));

                        prov.horizontalBlock(ctx.get(), model);
                    })
                    .simpleItem()
                    .register();



    // ALL AEQUALIS STONE
    public static final BlockEntry<Block> AEQUALIS = REGISTRATE.block("aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();
/////////
    public static final BlockEntry<Block> CUT_AEQUALIS = REGISTRATE.block("cut_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> CUT_AEQUALIS_STAIRS = REGISTRATE.block("cut_aequalis_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(), prov.modLoc("block/cut_aequalis")))
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> CUT_AEQUALIS_SLAB = REGISTRATE.block("cut_aequalis_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(), prov.modLoc("block/cut_aequalis"), prov.modLoc("block/cut_aequalis")))
            .simpleItem()
            .register();

    public static final BlockEntry<WallBlock> CUT_AEQUALIS_WALL = REGISTRATE.block("cut_aequalis_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.wallBlock(ctx.get(), prov.modLoc("block/cut_aequalis")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, prov) -> prov.wallInventory(ctx.getName(), prov.modLoc("block/cut_aequalis")))
            .build()
            .register();
///
    public static final BlockEntry<Block> POLISHED_CUT_AEQUALIS = REGISTRATE.block("polished_cut_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> POLISHED_CUT_AEQUALIS_STAIRS = REGISTRATE.block("polished_cut_aequalis_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(), prov.modLoc("block/polished_cut_aequalis")))
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> POLISHED_CUT_AEQUALIS_SLAB = REGISTRATE.block("polished_cut_aequalis_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(), prov.modLoc("block/polished_cut_aequalis"), prov.modLoc("block/polished_cut_aequalis")))
            .simpleItem()
            .register();

    public static final BlockEntry<WallBlock> POLISHED_CUT_AEQUALIS_WALL = REGISTRATE.block("polished_cut_aequalis_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.wallBlock(ctx.get(), prov.modLoc("block/polished_cut_aequalis")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, prov) -> prov.wallInventory(ctx.getName(), prov.modLoc("block/cut_aequalis")))
            .build()
            .register();
///
    public static final BlockEntry<Block> CUT_AEQUALIS_BRICKS = REGISTRATE.block("cut_aequalis_bricks", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> CUT_AEQUALIS_BRICK_STAIRS = REGISTRATE.block("cut_aequalis_brick_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(), prov.modLoc("block/cut_aequalis_bricks")))
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> CUT_AEQUALIS_BRICK_SLAB = REGISTRATE.block("cut_aequalis_brick_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(), prov.modLoc("block/cut_aequalis_bricks"), prov.modLoc("block/cut_aequalis")))
            .simpleItem()
            .register();

    public static final BlockEntry<WallBlock> CUT_AEQUALIS_BRICK_WALL = REGISTRATE.block("cut_aequalis_brick_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.wallBlock(ctx.get(), prov.modLoc("block/cut_aequalis_bricks")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, prov) -> prov.wallInventory(ctx.getName(), prov.modLoc("block/cut_aequalis")))
            .build()
            .register();
///
    public static final BlockEntry<Block> SMALL_AEQUALIS_BRICKS = REGISTRATE.block("small_aequalis_bricks", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<StairBlock> SMALL_AEQUALIS_BRICK_STAIRS = REGISTRATE.block("small_aequalis_brick_stairs", p -> new StairBlock(Blocks.STONE_STAIRS::defaultBlockState, p))
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(), prov.modLoc("block/small_aequalis_bricks")))
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> SMALL_AEQUALIS_BRICK_SLAB = REGISTRATE.block("small_aequalis_brick_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(), prov.modLoc("block/small_aequalis_bricks"), prov.modLoc("block/small_aequalis_bricks")))
            .simpleItem()
            .register();

    public static final BlockEntry<WallBlock> SMALL_AEQUALIS_BRICK_WALL = REGISTRATE.block("small_aequalis_brick_wall", WallBlock::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.wallBlock(ctx.get(), prov.modLoc("block/small_aequalis_bricks")))
            .item()
            .tag(ItemTags.WALLS)
            .model((ctx, prov) -> prov.wallInventory(ctx.getName(), prov.modLoc("block/cut_aequalis")))
            .build()
            .register();
/////////


    public static final BlockEntry<Block> SMOOTH_AEQUALIS = REGISTRATE.block("smooth_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<Block> SMOOTH_DIAMOND_AEQUALIS = REGISTRATE.block("smooth_diamond_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<Block> SMOOTH_DARK_DIAMOND_AEQUALIS = REGISTRATE.block("smooth_dark_diamond_aequalis", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.STONE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .simpleItem()
            .register();

    public static final BlockEntry<bigBricks> BIG_AEQUALIS_BRICKS = REGISTRATE.block("big_aequalis_bricks", bigBricks::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> {})
            .simpleItem()
            .register();








    // PIZZA TOWER
    public static final BlockEntry<Block> CRACKED_PILLAR = REGISTRATE.block("cracked_pillar", Block::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.sound(SoundType.DEEPSLATE))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
                    prov.models().cubeColumn(ctx.getName(), prov.modLoc("block/cracked_pillar"), prov.modLoc("block/cracked_cap"))))
            .onRegister(CreateRegistrate.blockModel(() -> testosteroneSpriteShifts.LAYERED_AEQUALIS_PROVIDER))
            .simpleItem()
            .register();

    public static final BlockEntry<johnRock> JOHN_ROCK = REGISTRATE.block("john_rock", johnRock::new)
            .initialProperties(() -> Blocks.OBSIDIAN)
            .properties(p -> p.sound(SoundType.STONE).isSuffocating((pState, pLevel, pPos) -> !pState.getValue(johnRock.TOGGLED)).lightLevel(s -> 12).isViewBlocking((pState, pLevel, pPos) -> !pState.getValue(johnRock.TOGGLED)))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> {})
            .item((block, properties) -> new BlockItem(block, properties.rarity(Rarity.EPIC)))
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/john_rock_active")))
            .build()
            .register();

    public static final BlockEntry<Block> CHEESE_BLOCK = REGISTRATE.block("cheese_block", Block::new)
            .initialProperties(() -> Blocks.HONEYCOMB_BLOCK)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item((block, properties) -> new BlockItem(block, properties.food(testosteroneModFoods.CHEESE_BLOCK)))
            .build()
            .register();

    public static final BlockEntry<fragileCopycatBase> FRAGILE_COPYCAT_BASE = REGISTRATE.block("fragile_copycat_base", fragileCopycatBase::new)
            .properties(p -> p.sound(SoundType.METAL))
            .blockstate((ctx, prov) -> {})
            .register();

    public static final BlockEntry<fragileCopycatBlock> FRAGILE_COPYCAT_BLOCK = REGISTRATE.block("fragile_copycat_block", fragileCopycatBlock::new)
            .transform(BuilderTransformers.copycat())
            .properties(p -> p.sound(SoundType.METAL))
            .onRegister(CreateRegistrate.blockModel(() -> fragileCopycatModel::new))
            .blockstate((ctx, prov) -> {})
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/fragile_copycat/0")))
            .build()
            .register();



    public static final BlockEntry<decanterCentrifugeBlock> DECANTER_CENTRIFUGE = REGISTRATE.block("decanter_centrifuge", decanterCentrifugeBlock::new)
            .initialProperties(AllBlocks.MECHANICAL_PUMP)
            .properties(p -> p.sound(SoundType.METAL).noOcclusion())
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 4))
            .blockstate((ctx, prov) -> {})
            .item()
            .model((ctx, prov) -> rotatedBlock(prov.withExistingParent(ctx.getName(), prov.modLoc("block/decanter_centrifuge/item")), 0, 90, 0))
            .build()
            .register();

    public static final BlockEntry<trenboloneVial> TRENBOLONE_VIAL = REGISTRATE.block("trenbolone_vial", trenboloneVial::new)
            .properties(p -> p.sound(SoundType.GLASS).noOcclusion())
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate((ctx, prov) -> {})
            .item()
            .model((ctx, prov) -> blockItemWithStandardTransforms(prov.withExistingParent(ctx.getName(), prov.modLoc("block/trenbolone_vial"))))
            .build()
            .register();


    public static void register() {

    }

    public static ItemModelBuilder blockItemWithStandardTransforms(ItemModelBuilder b) {
        b.transforms()
                .transform(ItemDisplayContext.GUI).rotation(30, 225, 0).scale(0.625F).end()
                .transform(ItemDisplayContext.GROUND).translation(0, 3, 0).scale(0.25F).end()
                .transform(ItemDisplayContext.FIXED).rotation(0, 180, 0).scale(0.5F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, 45, 0).scale(0.4F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0, -45, 0).scale(0.4F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(75, 45, 0).scale(0.4F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(75, -45, 0).scale(0.4F).end()
                .end();

        return b;
    }

    public static void rotatedBlock(ItemModelBuilder b, int xRot, int yRot, int zRot) {
        b.transforms()
                .transform(ItemDisplayContext.GUI).rotation(30 + xRot, 225 + yRot, zRot).scale(0.625F).end()
                .transform(ItemDisplayContext.GROUND).translation(xRot, 3 + yRot, zRot).scale(0.25F).end()
                .transform(ItemDisplayContext.FIXED).rotation(xRot, 180 + yRot, zRot).scale(0.5F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(xRot, 45 + yRot, zRot).scale(0.4F).end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(xRot, -45 + yRot, zRot).scale(0.4F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(75 + xRot, 45 + yRot, zRot).scale(0.4F).end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(75 + xRot, -45 + yRot, zRot).scale(0.4F).end()
                .end();

    }

} // tags, ponder