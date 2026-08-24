package net.mifort.testosterone.fluids;

import static net.mifort.testosterone.testosterone.REGISTRATE;

import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

import io.github.fabricators_of_create.porting_lib.fluids.FluidType;
import io.github.fabricators_of_create.porting_lib.fluids.PortingLibFluids;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Rarity;

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

	public static final FluidEntry<TestosteroneFluid.Flowing> TESTOSTERONE_FLUID =
			REGISTRATE.fluid(
							"testosterone_fluid",
							testosterone.rl("block/testosterone_fluid_still"),
							testosterone.rl("block/testosterone_fluid_flow"),
							TestosteroneFluid.Flowing::new)
					.source(TestosteroneFluid.Source::new)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
					.block()
					.build()
					.bucket()
					.properties(properties -> properties.rarity(Rarity.RARE))
					.build()
					.register();

	public static final FluidEntry<EstroneFluid.Flowing> ESTRONE_FLUID =
			REGISTRATE.fluid(
							"estrone_fluid",
							testosterone.rl("block/estrone_fluid_still"),
							testosterone.rl("block/estrone_fluid_flow"),
							EstroneFluid.Flowing::new)
					.source(EstroneFluid.Source::new)
					.fluidProperties(p -> p
							.levelDecreasePerBlock(1)
							.tickRate(5)
							.blastResistance(100f))
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

	public static void register() {
		Registry.register(PortingLibFluids.FLUID_TYPES, testosterone.rl("testosterone_fluid"), TESTOSTERONE_FLUID_TYPE);
		Registry.register(PortingLibFluids.FLUID_TYPES, testosterone.rl("estrone_fluid"), ESTRONE_FLUID_TYPE);
	}
}
