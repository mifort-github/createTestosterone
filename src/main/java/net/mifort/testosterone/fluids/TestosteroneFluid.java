package net.mifort.testosterone.fluids;

import com.tterrag.registrate.fabric.SimpleFlowableFluid;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public class TestosteroneFluid {

	public static class Flowing extends SimpleFlowableFluid.Flowing {
		public Flowing(Properties properties) {
			super(properties);
		}

		@Override
		public boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
			if (fluid.isSame(testosteroneFluids.ESTRONE_FLUID.get())) {
				return true;
			}
			return super.canBeReplacedWith(state, level, pos, fluid, direction);
		}

		@Override
		protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState blockState,
								Direction direction, FluidState fluidState) {
			FluidState neighbor = level.getFluidState(pos);
			if (neighbor.getType().isSame(testosteroneFluids.ESTRONE_FLUID.get())) {
				level.setBlock(pos, testosteroneModBlocks.AEQUALIS.defaultBlockState(), 3);
				level.levelEvent(1501, pos, 0);
				return;
			}
			super.spreadTo(level, pos, blockState, direction, fluidState);
		}
	}

	public static class Source extends SimpleFlowableFluid.Source {
		public Source(Properties properties) {
			super(properties);
		}

		@Override
		public boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
			if (fluid.isSame(testosteroneFluids.ESTRONE_FLUID.get())) {
				return true;
			}
			return super.canBeReplacedWith(state, level, pos, fluid, direction);
		}

		@Override
		protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState blockState,
								Direction direction, FluidState fluidState) {
			FluidState neighbor = level.getFluidState(pos);
			if (neighbor.getType().isSame(testosteroneFluids.ESTRONE_FLUID.get())) {
				level.setBlock(pos, testosteroneModBlocks.AEQUALIS.defaultBlockState(), 3);
				level.levelEvent(1501, pos, 0);
				return;
			}
			super.spreadTo(level, pos, blockState, direction, fluidState);
		}
	}
}
