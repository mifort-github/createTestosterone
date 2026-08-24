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
import net.mifort.testosterone.testosterone;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

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

	public static final BlockEntry<testosteronePillBox> TESTOSTERONE_PILL_BLOCK =
			REGISTRATE.block("testosterone_pill_box", testosteronePillBox::new)
					.initialProperties(() -> Blocks.OAK_PLANKS)
					.properties(p -> p.strength(1f, 1f)
							.sound(SoundType.BONE_BLOCK))
					.tag(BlockTags.MINEABLE_WITH_PICKAXE)
//					.blockstate((ctx, prov) -> {
//						ModelFile model = prov.models().cube(
//								ctx.getName(),
//								prov.modLoc("block/testosterone_pill_box/bottom"),
//								prov.modLoc("block/testosterone_pill_box/top"),
//								prov.modLoc("block/testosterone_pill_box/north"),
//								prov.modLoc("block/testosterone_pill_box/south"),
//								prov.modLoc("block/testosterone_pill_box/west"),
//								prov.modLoc("block/testosterone_pill_box/east")
//						).texture("particle", prov.modLoc("block/testosterone_pill_box/top"));
//
//						prov.horizontalBlock(ctx.get(), model);
//					})
					.simpleItem()
					.register();

	public static final Block AEQUALIS = registerBlock(
			"aequalis",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final Block CUT_AEQUALIS = registerBlock(
			"cut_aequalis",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final StairBlock CUT_AEQUALIS_STAIRS = registerBlock(
			"cut_aequalis_stairs",
			new StairBlock(
					CUT_AEQUALIS.defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final SlabBlock CUT_AEQUALIS_SLAB = registerBlock(
			"cut_aequalis_slab",
			new SlabBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final WallBlock CUT_AEQUALIS_WALL = registerBlock(
			"cut_aequalis_wall",
			new WallBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);


	public static final Block POLISHED_CUT_AEQUALIS = registerBlock(
			"polished_cut_aequalis",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final StairBlock POLISHED_CUT_AEQUALIS_STAIRS = registerBlock(
			"polished_cut_aequalis_stairs",
			new StairBlock(
					POLISHED_CUT_AEQUALIS.defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final SlabBlock POLISHED_CUT_AEQUALIS_SLAB = registerBlock(
			"polished_cut_aequalis_slab",
			new SlabBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final WallBlock POLISHED_CUT_AEQUALIS_WALL = registerBlock(
			"polished_cut_aequalis_wall",
			new WallBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);


	public static final Block CUT_AEQUALIS_BRICKS = registerBlock(
			"cut_aequalis_bricks",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final StairBlock CUT_AEQUALIS_BRICK_STAIRS = registerBlock(
			"cut_aequalis_brick_stairs",
			new StairBlock(
					CUT_AEQUALIS_BRICKS.defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final SlabBlock CUT_AEQUALIS_BRICK_SLAB = registerBlock(
			"cut_aequalis_brick_slab",
			new SlabBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final WallBlock CUT_AEQUALIS_BRICK_WALL = registerBlock(
			"cut_aequalis_brick_wall",
			new WallBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);


	public static final Block SMALL_AEQUALIS_BRICKS = registerBlock(
			"small_aequalis_bricks",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final StairBlock SMALL_AEQUALIS_BRICK_STAIRS = registerBlock(
			"small_aequalis_brick_stairs",
			new StairBlock(
					SMALL_AEQUALIS_BRICKS.defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final SlabBlock SMALL_AEQUALIS_BRICK_SLAB = registerBlock(
			"small_aequalis_brick_slab",
			new SlabBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final WallBlock SMALL_AEQUALIS_BRICK_WALL = registerBlock(
			"small_aequalis_brick_wall",
			new WallBlock(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);


	public static final Block SMOOTH_AEQUALIS = registerBlock(
			"smooth_aequalis",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.STONE)
			)
	);

	public static final Block SMOOTH_DIAMOND_AEQUALIS = registerBlock(
			"smooth_diamond_aequalis",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.STONE)
			)
	);

	public static final Block SMOOTH_DARK_DIAMOND_AEQUALIS = registerBlock(
			"smooth_dark_diamond_aequalis",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.STONE)
			)
	);

	public static final bigBricks BIG_AEQUALIS_BRICKS = registerBlock(
			"big_aequalis_bricks",
			new bigBricks(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);


	public static final Block CRACKED_PILLAR = registerBlock(
			"cracked_pillar",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.STONE)
							.sound(SoundType.DEEPSLATE)
			)
	);

	public static final johnRock JOHN_ROCK = registerBlock(
			"john_rock",
			new johnRock(
					BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
							.sound(SoundType.STONE)
							.isSuffocating((state, level, pos) ->
									!state.getValue(johnRock.TOGGLED))
							.lightLevel(state -> 12)
							.isViewBlocking((state, level, pos) ->
									!state.getValue(johnRock.TOGGLED))
			),
			new Item.Properties()
					.rarity(Rarity.EPIC)
	);

	public static final Block CHEESE_BLOCK = registerBlock(
			"cheese_block",
			new Block(
					BlockBehaviour.Properties.copy(Blocks.HONEYCOMB_BLOCK)
			),
			new Item.Properties()
					.food(testosteroneModFoods.CHEESE_BLOCK)
	);


	public static final fragileCopycatBase FRAGILE_COPYCAT_BASE = registerBlock(
			"fragile_copycat_base",
			new fragileCopycatBase(
					BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
							.sound(SoundType.METAL)
			),
			false
	);

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
//			.model((ctx, prov) -> rotatedBlock(prov.withExistingParent(ctx.getName(), prov.modLoc("block/decanter_centrifuge/item")), 0, 90, 0))
			.build()
			.register();

	public static final trenboloneVial TRENBOLONE_VIAL =
			registerBlock(
					"trenbolone_vial",
					new trenboloneVial(
							BlockBehaviour.Properties.copy(Blocks.GLASS)
									.sound(SoundType.GLASS)
									.noOcclusion()
					)
			);


	private static <T extends Block> T registerBlock(
			String name,
			T block
	) {
		return registerBlock(
				name,
				block,
				new Item.Properties()
		);
	}

	private static <T extends Block> T registerBlock(
			String name,
			T block,
			boolean registerItem
	) {
		if (!registerItem) {
			ResourceLocation id =
					new ResourceLocation(testosterone.MOD_ID, name);

			return Registry.register(
					BuiltInRegistries.BLOCK,
					id,
					block
			);
		}

		return registerBlock(
				name,
				block,
				new Item.Properties()
		);
	}

	private static <T extends Block> T registerBlock(
			String name,
			T block,
			Item.Properties itemProperties
	) {
		ResourceLocation id =
				new ResourceLocation(testosterone.MOD_ID, name);

		T registeredBlock = Registry.register(
				BuiltInRegistries.BLOCK,
				id,
				block
		);

		Registry.register(
				BuiltInRegistries.ITEM,
				id,
				new BlockItem(
						registeredBlock,
						itemProperties
				)
		);

		return registeredBlock;
	}

	public static void register() {

	}
}
