package net.mifort.testosterone.recipes;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.mifort.testosterone.blocks.centrifuge.CentrifugeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class centrifugingRecipe extends ProcessingRecipe<Inventory> {
    public centrifugingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(testosteroneModRecipes.CENTRIFUGING, params);
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

    public boolean match(@NotNull CentrifugeBlockEntity centrifugeBlockEntity) {
        if (fluidIngredients.isEmpty() || fluidResults.isEmpty()) return false;

        BlockPos below = centrifugeBlockEntity.getBlockPos().below();
        BlockPos above = centrifugeBlockEntity.getBlockPos().above();

        BlockEntity belowBlockEntity;
        BlockEntity aboveBlockEntity;

        if (centrifugeBlockEntity.getLevel() != null) {
            belowBlockEntity = centrifugeBlockEntity.getLevel().getBlockEntity(below);
            aboveBlockEntity = centrifugeBlockEntity.getLevel().getBlockEntity(above);
        } else {
            belowBlockEntity = null;
            aboveBlockEntity = null;
        }


        if (belowBlockEntity == null || aboveBlockEntity == null) return false;

        AtomicBoolean success = new AtomicBoolean(false);

        belowBlockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(belowHandler -> {
            aboveBlockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(aboveHandler -> {
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