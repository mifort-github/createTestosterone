package net.mifort.testosterone.fluids;

import com.tterrag.registrate.util.entry.FluidEntry;
import dev.mayaqq.estrogen.registry.EstrogenFluids;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.mifort.testosterone.testosterone;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static net.mifort.testosterone.testosterone.REGISTRATE;

@EventBusSubscriber(modid = testosterone.MOD_ID, bus = Bus.MOD)
public class testosteroneFluids {
    public static final FluidEntry<ForgeFlowingFluid.Flowing> CHOLESTEROL_FLUID =
            REGISTRATE.fluid("cholesterol_fluid", testosterone.rl("block/cholesterol_fluid_still"), testosterone.rl("block/cholesterol_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canExtinguish(true)
                            .canHydrate(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(20)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> DILUTED_ZINC_FLUID =
            REGISTRATE.fluid("diluted_zinc_fluid", testosterone.rl("block/diluted_zinc_fluid_still"), testosterone.rl("block/diluted_zinc_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canExtinguish(true)
                            .canHydrate(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> TESTOSTERONE_FLUID =
            REGISTRATE.fluid("testosterone_fluid", testosterone.rl("block/testosterone_fluid_still"), testosterone.rl("block/testosterone_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canHydrate(true)
                            .canExtinguish(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> BEER_FLUID =
            REGISTRATE.fluid("beer_fluid", testosterone.rl("block/beer_fluid_still"), testosterone.rl("block/beer_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canExtinguish(true)
                            .canHydrate(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();



    public static void register() {

    }

    public static void registerFluidInteractions() {
        final BlockState BLOCK = testosteroneModBlocks.AEQUALIS.get().defaultBlockState();

        FluidInteractionRegistry.addInteraction(EstrogenFluids.LIQUID_ESTROGEN.get().getFluidType(), new FluidInteractionRegistry.InteractionInformation(
                TESTOSTERONE_FLUID.get().getFluidType(),
                fluidState -> BLOCK
        ));

        FluidInteractionRegistry.addInteraction(TESTOSTERONE_FLUID.get().getFluidType(), new FluidInteractionRegistry.InteractionInformation(
                EstrogenFluids.LIQUID_ESTROGEN.get().getFluidType(),
                fluidState -> BLOCK
        ));
    }

}