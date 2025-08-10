package net.mifort.testosterone.recipes;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

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


        Direction facing = decanterCentrifugeBlockEntity.getBlockState().getValue(HorizontalKineticBlock.HORIZONTAL_FACING);

        BlockPos input = decanterCentrifugeBlockEntity.getBlockPos().relative(facing);
        BlockPos output = decanterCentrifugeBlockEntity.getBlockPos().relative(facing.getOpposite());

        BlockEntity inputBlockEntity;
        BlockEntity outputBlockEntity;

        if (decanterCentrifugeBlockEntity.getLevel() != null) {
            inputBlockEntity = decanterCentrifugeBlockEntity.getLevel().getBlockEntity(input);
            outputBlockEntity = decanterCentrifugeBlockEntity.getLevel().getBlockEntity(output);
        } else {
            inputBlockEntity = null;
            outputBlockEntity = null;
        }


        if (inputBlockEntity == null || outputBlockEntity == null) return false;

        AtomicBoolean success = new AtomicBoolean(false);

        inputBlockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(belowHandler -> {
            outputBlockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(aboveHandler -> {
                if (belowHandler.getTanks() == 0 || aboveHandler.getTanks() == 0) return;

                int belowIdx = 0;
                int aboveIdx = 0;

                FluidStack tankBelow = belowHandler.getFluidInTank(belowIdx);
                FluidStack tankAbove = aboveHandler.getFluidInTank(aboveIdx);

                var ingredient = getFluidIngredients().get(0);
                FluidStack result = getFluidResults().get(0).copy();

                int required = ingredient.getRequiredAmount();
                if (!ingredient.test(tankBelow)) return;
                if (tankBelow.getAmount() < required) return;

                if (!tankAbove.isEmpty() && !tankAbove.isFluidEqual(result)) return;

                int capacity = aboveHandler.getTankCapacity(aboveIdx);
                int freeSpace = capacity - tankAbove.getAmount();
                if (freeSpace < result.getAmount()) return;

                FluidStack simDrain = belowHandler.drain(new FluidStack(tankBelow, required), IFluidHandler.FluidAction.SIMULATE);
                if (simDrain.getAmount() != required) return;

                int simFill = aboveHandler.fill(result, IFluidHandler.FluidAction.SIMULATE);
                if (simFill != result.getAmount()) return;

                belowHandler.drain(required, IFluidHandler.FluidAction.EXECUTE);
                aboveHandler.fill(result, IFluidHandler.FluidAction.EXECUTE);

                success.set(true);
            });
        });

        return success.get();
    }

    @Override
    public boolean matches(@NotNull Inventory pContainer, @NotNull Level pLevel) {
        return false;
    }
}