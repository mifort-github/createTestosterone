package net.mifort.testosterone.fluids;

import com.simibubi.create.AllFluids;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.mifort.testosterone.testosterone;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.joml.Vector3f;
import com.simibubi.create.foundation.utility.Color;

import java.util.function.Supplier;

import static net.mifort.testosterone.testosterone.REGISTRATE;

@EventBusSubscriber(modid = testosterone.MOD_ID, bus = Bus.MOD)
public class testosteroneFluids {
    public static final FluidEntry<ForgeFlowingFluid.Flowing> CHOLESTEROL =
            REGISTRATE.standardFluid("cholesterol_fluid",
                            SolidRenderedPlaceableFluidType.create(0x0, () -> 1f))
                    .properties(b -> b.viscosity(1500)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(20)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> DILUTED_ZINC =
            REGISTRATE.standardFluid("diluted_zinc_fluid",
                            SolidRenderedPlaceableFluidType.create(0xFFFFFF, () -> 1f))
                    .properties(b -> b.viscosity(1500)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> TESTOSTERONE =
            REGISTRATE.standardFluid("testosterone_fluid",
                            SolidRenderedPlaceableFluidType.create(0x0, () -> 1f))
                    .properties(b -> b.viscosity(1500)
                            .density(1500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(5)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .register();


    private static class SolidRenderedPlaceableFluidType extends AllFluids.TintedFluidType {

        private Vector3f fogColor;
        private Supplier<Float> fogDistance;

        public static FluidBuilder.FluidTypeFactory create(int fogColor, Supplier<Float> fogDistance) {
            return (p, s, f) -> {
                SolidRenderedPlaceableFluidType fluidType = new SolidRenderedPlaceableFluidType(p, s, f);
                fluidType.fogColor = new Color(fogColor, false).asVectorF();
                fluidType.fogDistance = fogDistance;
                return fluidType;
            };
        }

        private SolidRenderedPlaceableFluidType(Properties properties, ResourceLocation stillTexture, ResourceLocation flowingTexture) {
            super(properties, stillTexture, flowingTexture);
        }

        @Override
        protected int getTintColor(FluidStack stack) {
            return NO_TINT;
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return 0x00ffffff;
        }

        @Override
        protected Vector3f getCustomFogColor() {
            return fogColor;
        }

        @Override
        protected float getFogDistanceModifier() {
            return fogDistance.get();
        }
    }

    public static void register() {
        // This method can be used to call the registration of fluids if needed
    }
}