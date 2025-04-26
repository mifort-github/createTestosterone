package net.mifort.testosterone.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class johnRock extends HorizontalDirectionalBlock {
    public static final BooleanProperty TOGGLED = BooleanProperty.create("toggled");
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    private static final int MAX_PROPAGATION_BLOCKS = 4096;

    public johnRock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TOGGLED, false)
                .setValue(PRESSED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOGGLED, PRESSED, FACING);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide()) {
            boolean isPowered = level.hasNeighborSignal(pos);
            if (isPowered && !state.getValue(PRESSED)) {
                boolean newToggled = !state.getValue(TOGGLED);
                BlockState newState = state.setValue(PRESSED, true).setValue(TOGGLED, newToggled);
                level.setBlockAndUpdate(pos, newState);
                propagateConnectedToggling(level, pos, newToggled);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide()) {
            boolean isPowered = level.hasNeighborSignal(pos);
            boolean wasPressed = state.getValue(PRESSED);

            if (isPowered && !wasPressed) {
                boolean newToggled = !state.getValue(TOGGLED);
                BlockState newState = state.setValue(PRESSED, true).setValue(TOGGLED, newToggled);
                level.setBlockAndUpdate(pos, newState);
                propagateConnectedToggling(level, pos, newToggled);
            } else if (!isPowered && wasPressed) {
                level.setBlockAndUpdate(pos, state.setValue(PRESSED, false));
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, fromPos, isMoving);
    }

    private void propagateConnectedToggling(Level level, BlockPos origin, boolean newToggled) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = origin.relative(direction);
            BlockState adjacentState = level.getBlockState(adjacentPos);
            if (adjacentState.getBlock() instanceof johnRock) {
                queue.add(adjacentPos);
                visited.add(adjacentPos);
            }
        }

        while (!queue.isEmpty() && visited.size() < MAX_PROPAGATION_BLOCKS) {
            BlockPos currentPos = queue.poll();
            BlockState currentState = level.getBlockState(currentPos);
            if (currentState.getValue(johnRock.TOGGLED) != newToggled) {
                level.setBlockAndUpdate(currentPos, currentState.setValue(johnRock.TOGGLED, newToggled));
            }
            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = currentPos.relative(direction);
                if (!visited.contains(neighborPos)) {
                    BlockState neighborState = level.getBlockState(neighborPos);
                    if (neighborState.getBlock() instanceof johnRock) {
                        visited.add(neighborPos);
                        queue.add(neighborPos);
                    }
                }
            }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(TOGGLED)) {
            return Shapes.empty();
        }
        return super.getCollisionShape(state, level, pos, context);
    }


    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        if (adjacentBlockState.getBlock() == this) {
            return state.getValue(TOGGLED);
        }
        return super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }
}
