package net.mifort.testosterone.fluids;

import static net.mifort.testosterone.testosterone.LOGGER;
import static net.mifort.testosterone.testosterone.REGISTRATE;

import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

import io.github.fabricators_of_create.porting_lib.event.common.FluidPlaceBlockCallback;
import io.github.fabricators_of_create.porting_lib.fluids.FluidInteractionRegistry;
import io.github.fabricators_of_create.porting_lib.fluids.FluidType;
import io.github.fabricators_of_create.porting_lib.fluids.PortingLibFluids;
import net.createmod.catnip.data.Iterate;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

public class testosteroneFluids {

	public static final FluidEntry<SimpleFlowableFluid.Flowing> CHOLESTEROL_FLUID =
			REGISTRATE.fluid(
							"cholesterol_fluid",
							testosterone.rl("block/cholesterol_fluid_still"),
							testosterone.rl("block/cholesterol_fluid_flow")
					)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(2)
							.tickRate(20)
							.blastResistance(100f))
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> DILUTED_ZINC_FLUID =
			REGISTRATE.fluid(
							"diluted_zinc_fluid",
							testosterone.rl("block/diluted_zinc_fluid_still"),
							testosterone.rl("block/diluted_zinc_fluid_flow")
					)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> TESTOSTERONE_FLUID =
			REGISTRATE.fluid(
							"testosterone_fluid",
							testosterone.rl("block/testosterone_fluid_still"),
							testosterone.rl("block/testosterone_fluid_flow"))
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.source(SimpleFlowableFluid.Source::new)
					.block()
					.build()
					.bucket()
					.properties(properties -> properties.rarity(Rarity.RARE))
					.build()
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> ESTRONE_FLUID =
			REGISTRATE.fluid(
							"estrone_fluid",
							testosterone.rl("block/estrone_fluid_still"),
							testosterone.rl("block/estrone_fluid_flow"))
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.source(SimpleFlowableFluid.Source::new)
					.block()
					.build()
					.bucket()
					.properties(properties -> properties.rarity(Rarity.RARE))
					.build()
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> TRENBOLONE_FLUID =
			REGISTRATE.fluid(
							"trenbolone_fluid",
							testosterone.rl("block/trenbolone_fluid_still"),
							testosterone.rl("block/trenbolone_fluid_flow")
					)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.source(SimpleFlowableFluid.Source::new)
					.bucket()
					.properties(properties -> properties.rarity(Rarity.RARE))
					.build()
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> BEER_FLUID =
			REGISTRATE.fluid(
							"beer_fluid",
							testosterone.rl("block/beer_fluid_still"),
							testosterone.rl("block/beer_fluid_flow")
					)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> WHEY_FLUID =
			REGISTRATE.fluid(
							"whey_fluid",
							testosterone.rl("block/whey_fluid_still"),
							testosterone.rl("block/whey_fluid_flow")
					)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.register();

	public static final FluidEntry<SimpleFlowableFluid.Flowing> CHEESE_FLUID =
			REGISTRATE.fluid(
							"cheese_fluid",
							testosterone.rl("block/cheese_fluid_still"),
							testosterone.rl("block/cheese_fluid_flow")
					)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(2)
							.tickRate(20)
							.blastResistance(100f))
					.register();

	public static final FluidType TESTOSTERONE_FLUID_TYPE = new FluidType(FluidType.Properties.create());
	public static final FluidType ESTRONE_FLUID_TYPE = new FluidType(FluidType.Properties.create());
	public static final FluidType ESTROGEN_FLUID_TYPE = new FluidType(FluidType.Properties.create());

	public static void register() {
		Registry.register(PortingLibFluids.FLUID_TYPES, testosterone.rl("testosterone_fluid"), TESTOSTERONE_FLUID_TYPE);
		Registry.register(PortingLibFluids.FLUID_TYPES, testosterone.rl("estrone_fluid"), ESTRONE_FLUID_TYPE);
		Registry.register(PortingLibFluids.FLUID_TYPES, new ResourceLocation("estrogen", "liquid_estrogen"), ESTROGEN_FLUID_TYPE);

		FluidInteractionRegistry.addInteraction(TESTOSTERONE_FLUID_TYPE,
				new FluidInteractionRegistry.InteractionInformation(
						ESTROGEN_FLUID_TYPE,
						testosteroneModBlocks.AEQUALIS.defaultBlockState()
				)
		);

		FluidInteractionRegistry.addInteraction(ESTROGEN_FLUID_TYPE,
				new FluidInteractionRegistry.InteractionInformation(
						TESTOSTERONE_FLUID_TYPE,
						testosteroneModBlocks.AEQUALIS.defaultBlockState()
				)
		);
	}

	public static void registerFluidInteractions() {
		FluidInteractionRegistry.addInteraction(TESTOSTERONE_FLUID_TYPE,
				new FluidInteractionRegistry.InteractionInformation(
						ESTRONE_FLUID_TYPE,
						testosteroneModBlocks.AEQUALIS.defaultBlockState()
				)
		);

		FluidInteractionRegistry.addInteraction(ESTRONE_FLUID_TYPE,
				new FluidInteractionRegistry.InteractionInformation(
						TESTOSTERONE_FLUID_TYPE,
						testosteroneModBlocks.AEQUALIS.defaultBlockState()
				)
		);
	}

	// I dont even know bro
	@Nullable
	public static FluidType getTypeOf(Fluid fluid) {
		ResourceLocation id = BuiltInRegistries.FLUID.getKey(fluid);

		if (testosterone.rl("testosterone_fluid").equals(id) || testosterone.rl("flowing_testosterone_fluid").equals(id)) {
			return TESTOSTERONE_FLUID_TYPE;
		}

		if (testosterone.rl("estrone_fluid").equals(id) || testosterone.rl("flowing_estrone_fluid").equals(id)) {
			return ESTRONE_FLUID_TYPE;
		}

		if (new ResourceLocation("estrogen", "liquid_estrogen").equals(id) || new ResourceLocation("estrogen", "flowing_liquid_estrogen").equals(id)) {
			return ESTRONE_FLUID_TYPE;
		}

		return null;
	}
}
