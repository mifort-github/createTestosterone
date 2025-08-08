package net.mifort.testosterone.fluids;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.mifort.testosterone.testosterone;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import static net.mifort.testosterone.testosterone.LOGGER;
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
                    .source(ForgeFlowingFluid.Source::new)
                    .block(LiquidBlock::new)
                    .build()
                    .bucket()
                    .properties(properties -> properties.rarity(Rarity.RARE))
                    .build()
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> ESTRONE_FLUID =
            REGISTRATE.fluid("estrone_fluid", testosterone.rl("block/estrone_fluid_still"), testosterone.rl("block/estrone_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canHydrate(true)
                            .canExtinguish(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .block(LiquidBlock::new)
                    .build()
                    .bucket()
                    .properties(properties -> properties.rarity(Rarity.RARE))
                    .build()
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> TRENBOLONE_FLUID =
            REGISTRATE.fluid("trenbolone_fluid", testosterone.rl("block/trenbolone_fluid_still"), testosterone.rl("block/trenbolone_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canHydrate(true)
                            .canExtinguish(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .block(LiquidBlock::new)
                    .build()
                    .bucket()
                    .properties(properties -> properties.rarity(Rarity.RARE))
                    .build()
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

    public static final FluidEntry<ForgeFlowingFluid.Flowing> WHEY_FLUID =
            REGISTRATE.fluid("whey_fluid", testosterone.rl("block/whey_fluid_still"), testosterone.rl("block/whey_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canExtinguish(true)
                            .canHydrate(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> CHEESE_FLUID =
            REGISTRATE.fluid("cheese_fluid", testosterone.rl("block/cheese_fluid_still"), testosterone.rl("block/cheese_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .canExtinguish(true)
                            .canHydrate(true)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(20)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static void register() {

    }

    public static void registerFluidInteractions() {
        final BlockState BLOCK = testosteroneModBlocks.AEQUALIS.get().defaultBlockState();

        final ResourceLocation estrogenFluidId = new ResourceLocation("estrogen", "liquid_estrogen");

        final @Nullable Fluid estrogenFluid = ForgeRegistries.FLUIDS.getValue(estrogenFluidId);

        if (estrogenFluid != Fluids.EMPTY) {
            FluidInteractionRegistry.addInteraction(estrogenFluid.getFluidType(), new FluidInteractionRegistry.InteractionInformation(
                    TESTOSTERONE_FLUID.get().getFluidType(),
                    fluidState -> BLOCK
            ));

            FluidInteractionRegistry.addInteraction(TESTOSTERONE_FLUID.get().getFluidType(), new FluidInteractionRegistry.InteractionInformation(
                    estrogenFluid.getFluidType(),
                    fluidState -> BLOCK
            ));
        }


        FluidInteractionRegistry.addInteraction(testosteroneFluids.ESTRONE_FLUID.getType(), new FluidInteractionRegistry.InteractionInformation(
                TESTOSTERONE_FLUID.get().getFluidType(),
                fluidState -> BLOCK
        ));

        FluidInteractionRegistry.addInteraction(TESTOSTERONE_FLUID.get().getFluidType(), new FluidInteractionRegistry.InteractionInformation(
                testosteroneFluids.ESTRONE_FLUID.getType(),
                fluidState -> BLOCK
        ));

    }

}