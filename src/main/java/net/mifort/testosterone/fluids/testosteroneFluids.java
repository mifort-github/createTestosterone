package net.mifort.testosterone.fluids;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.mifort.testosterone.testosterone;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static net.mifort.testosterone.testosterone.REGISTRATE;

@EventBusSubscriber(modid = testosterone.MOD_ID, bus = Bus.MOD)
public class testosteroneFluids {
    public static final FluidEntry<ForgeFlowingFluid.Flowing> CHOLESTEROL_FLUID =
            REGISTRATE.fluid("cholesterol_fluid", testosterone.rl("fluid/cholesterol_fluid_still"), testosterone.rl("fluid/cholesterol_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(20)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> DILUTED_ZINC_FLUID =
            REGISTRATE.fluid("diluted_zinc_fluid", testosterone.rl("fluid/diluted_zinc_fluid_still"), testosterone.rl("fluid/diluted_zinc_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> TESTOSTERONE_FLUID =
            REGISTRATE.fluid("testosterone_fluid", testosterone.rl("fluid/testosterone_fluid_still"), testosterone.rl("fluid/testosterone_fluid_flow"))
                    .properties(b -> b.viscosity(1500)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static void register() {
        // This method can be used to call the registration of fluids if needed
    }
}