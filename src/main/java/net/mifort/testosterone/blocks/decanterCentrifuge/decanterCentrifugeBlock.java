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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"deprecation"})
public class decanterCentrifugeBlock extends HorizontalKineticBlock implements IBE<decanterCentrifugeBlockEntity>, IWrenchable {

    public decanterCentrifugeBlock(Properties settings) {
        super(settings);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return true;
    }

    // 1.21 replaced isPathfindable with getBlockPathType or similar logic via BlockState
    @Override
    public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable net.minecraft.world.entity.Mob mob) {
        return PathType.BLOCKED;
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
        }

        Direction preferred = getPreferredHorizontalFacing(context);
        if (preferred != null) {
            return defaultBlockState().setValue(HORIZONTAL_FACING, preferred);
        }
        return super.getStateForPlacement(context);
    }

    @Override
    public Direction getPreferredHorizontalFacing(BlockPlaceContext context) {
        Direction preferredSide = null;
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        for (Direction side : Iterate.horizontalDirections) {
            // New NeoForge Capability query: no more LazyOptional!
            IFluidHandler handler = level.getCapability(Capabilities.FluidHandler.BLOCK, pos.relative(side), side.getOpposite());

            if (handler != null) {
                preferredSide = side.getOpposite();
                break; // Found a valid side, stop looking
            }
        }
        return preferredSide;
    }
}