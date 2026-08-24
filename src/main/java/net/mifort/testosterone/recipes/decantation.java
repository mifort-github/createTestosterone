package net.mifort.testosterone.recipes;

import org.jetbrains.annotations.NotNull;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class decantation extends ProcessingRecipe<Inventory> {
	public decantation(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
		super(testosteroneModRecipes.DECANTATION, params);
	}

	@Override
	protected int getMaxInputCount() {
		return 0;
	}

	@Override
	protected int getMaxOutputCount() {
		return 0;
	}

	@Override
	protected int getMaxFluidInputCount() {
		return 1;
	}

	@Override
	protected int getMaxFluidOutputCount() {
		return 1;
	}

	public boolean match(@NotNull decanterCentrifugeBlockEntity decanterCentrifugeBlockEntity) {
		if (fluidIngredients.isEmpty() || fluidResults.isEmpty()) return false;

		Level level = decanterCentrifugeBlockEntity.getLevel();
		if (level == null) return false;

		Direction facing = decanterCentrifugeBlockEntity.getBlockState().getValue(HorizontalKineticBlock.HORIZONTAL_FACING);

		BlockPos inputPos = decanterCentrifugeBlockEntity.getBlockPos().relative(facing.getOpposite());
		BlockPos outputPos = decanterCentrifugeBlockEntity.getBlockPos().relative(facing);

		Storage<FluidVariant> inputStorage = FluidStorage.SIDED.find(level, inputPos, facing);
		Storage<FluidVariant> outputStorage = FluidStorage.SIDED.find(level, outputPos, facing.getOpposite());
		if (inputStorage == null || outputStorage == null) return false;

		var ingredient = getFluidIngredients().get(0);
		FluidStack result = getFluidResults().get(0).copy();

		long dropletsPerMb = FluidConstants.BUCKET / 1000L;
		long required = ingredient.getRequiredAmount() * dropletsPerMb;
		long resultAmount = result.getAmount() * dropletsPerMb;

		FluidVariant inputVariant = FluidVariant.blank();
		long inputAmount = 0;
		for (StorageView<FluidVariant> view : inputStorage) {
			if (!view.isResourceBlank()) {
				inputVariant = view.getResource();
				inputAmount = view.getAmount();
				break;
			}
		}
		if (inputVariant.isBlank()) return false;

		FluidStack tankBelow = new FluidStack(inputVariant, (int) inputAmount);
		if (!ingredient.test(tankBelow)) return false;
		if (inputAmount < required) return false;

		FluidVariant outputVariant = FluidVariant.blank();
		long outputAmount = 0;
		for (StorageView<FluidVariant> view : outputStorage) {
			if (!view.isResourceBlank()) {
				outputVariant = view.getResource();
				outputAmount = view.getAmount();
				break;
			}
		}
		FluidVariant resultVariant = FluidVariant.of(result.getFluid());
		if (outputAmount > 0 && !outputVariant.equals(resultVariant)) return false;

		try (Transaction simulation = Transaction.openOuter()) {
			long extracted = inputStorage.extract(inputVariant, required, simulation);
			long inserted = outputStorage.insert(resultVariant, resultAmount, simulation);
			if (extracted != required || inserted != resultAmount) return false;
		}

		try (Transaction transaction = Transaction.openOuter()) {
			inputStorage.extract(inputVariant, required, transaction);
			outputStorage.insert(resultVariant, resultAmount, transaction);
			transaction.commit();
		}

		return true;
	}

	@Override
	public boolean matches(@NotNull Inventory pContainer, @NotNull Level pLevel) {
		return false;
	}
}
