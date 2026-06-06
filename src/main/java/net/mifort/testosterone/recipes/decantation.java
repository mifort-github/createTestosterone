package net.mifort.testosterone.recipes;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeParams;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

public class decantation extends StandardProcessingRecipe<SingleRecipeInput> {
    public decantation(ProcessingRecipeParams params) {
        super(testosteroneModRecipes.DECANTATION, params);
    }

    @Override
    public boolean matches(SingleRecipeInput inv, Level worldIn) {
        return true;
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

        BlockEntity inputBlockEntity = level.getBlockEntity(inputPos);
        BlockEntity outputBlockEntity = level.getBlockEntity(outputPos);

        if (inputBlockEntity == null || outputBlockEntity == null) return false;

        IFluidHandler inputHandler = level.getCapability(
                Capabilities.FluidHandler.BLOCK,
                inputPos,
                inputBlockEntity.getBlockState(),
                inputBlockEntity,
                null
        );

        IFluidHandler outputHandler = level.getCapability(
                Capabilities.FluidHandler.BLOCK,
                outputPos,
                outputBlockEntity.getBlockState(),
                outputBlockEntity,
                null
        );

        if (inputHandler == null || outputHandler == null) return false;
        if (inputHandler.getTanks() == 0 || outputHandler.getTanks() == 0) return false;

        int belowIdx = 0;
        int aboveIdx = 0;

        FluidStack tankBelow = inputHandler.getFluidInTank(belowIdx);
        FluidStack tankAbove = outputHandler.getFluidInTank(aboveIdx);

        var ingredient = getFluidIngredients().get(0);
        FluidStack result = getFluidResults().get(0).copy();

        int required = ingredient.amount();
        if (!ingredient.test(tankBelow)) return false;
        if (tankBelow.getAmount() < required) return false;

        if (!tankAbove.isEmpty() && !tankAbove.isFluidEqual(result)) return false;

        int capacity = outputHandler.getTankCapacity(aboveIdx);
        int freeSpace = capacity - tankAbove.getAmount();
        if (freeSpace < result.getAmount()) return false;

        FluidStack simDrain = inputHandler.drain(new FluidStack(tankBelow.getFluidHolder(), required), IFluidHandler.FluidAction.SIMULATE);
        if (simDrain.getAmount() != required) return false;

        int simFill = outputHandler.fill(result, IFluidHandler.FluidAction.SIMULATE);
        if (simFill != result.getAmount()) return false;

        inputHandler.drain(required, IFluidHandler.FluidAction.EXECUTE);
        outputHandler.fill(result, IFluidHandler.FluidAction.EXECUTE);

        return true;
    }
}