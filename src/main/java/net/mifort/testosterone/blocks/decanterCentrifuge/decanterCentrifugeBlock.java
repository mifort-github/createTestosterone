package net.mifort.testosterone.blocks.decanterCentrifuge;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.createmod.catnip.data.Iterate;
import net.mifort.testosterone.blocks.testosteroneBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class decanterCentrifugeBlock extends HorizontalKineticBlock implements IBE<decanterCentrifugeBlockEntity>, IWrenchable {

    public decanterCentrifugeBlock(Properties settings) {
        super(settings);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldView, BlockPos blockPos) {
        return true;
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public SpeedLevel getMinimumRequiredSpeedLevel() {
        return SpeedLevel.MEDIUM;
    }

    @Override
    public Class<decanterCentrifugeBlockEntity> getBlockEntityClass() {
        return decanterCentrifugeBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends decanterCentrifugeBlockEntity> getBlockEntityType() {
        return testosteroneBlockEntities.DECANTER_CENTRIFUGE.get();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == Direction.UP || face == Direction.DOWN;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() != null && context.getPlayer().isCrouching()) {
            return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection());
        } else {
            if (getPreferredHorizontalFacing(context) != null) {
                return defaultBlockState().setValue(HORIZONTAL_FACING, getPreferredHorizontalFacing(context));
            } else {
                return super.getStateForPlacement(context);
            }
        }
    }

    @Override
    public Direction getPreferredHorizontalFacing(BlockPlaceContext context) {
        Direction preferredSide = null;
        for (Direction side : Iterate.horizontalDirections) {
            BlockEntity be = context.getLevel().getBlockEntity(context.getClickedPos().relative(side));


            if (be != null) {
                @NotNull LazyOptional<IFluidHandler> cap = be.getCapability(ForgeCapabilities.FLUID_HANDLER);

                if (cap.isPresent()) {
                    preferredSide = side.getOpposite();
                }
            }
        }

        return  preferredSide;
    }
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return new decanterCentrifugeBlockEntity(testosteroneBlockEntities.DECANTER_CENTRIFUGE.get(), pPos, pState);
    }
}