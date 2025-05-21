package net.mifort.testosterone.blocks;

import net.mifort.testosterone.config.ConfigRegistry;
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

public class johnRock extends Block {
    public static final BooleanProperty TOGGLED = BooleanProperty.create("toggled");
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    private static long lastUpdateTick = -1;
    private static final Set<BlockPos> updatedClusterPositions = new HashSet<>();

    public johnRock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TOGGLED, false)
                .setValue(PRESSED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOGGLED, PRESSED);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide()) {
            long currentTick = level.getGameTime();
            if (currentTick != lastUpdateTick) {
                updatedClusterPositions.clear();
                lastUpdateTick = currentTick;
            }
            if (updatedClusterPositions.contains(pos)) {
                return;
            }
            boolean isPowered = level.hasNeighborSignal(pos);
            if (isPowered && !state.getValue(PRESSED)) {
                boolean newToggled = !state.getValue(TOGGLED);
                BlockState newState = state.setValue(PRESSED, true).setValue(TOGGLED, newToggled);
                level.setBlockAndUpdate(pos, newState);
                updatedClusterPositions.add(pos);
                propagateConnectedToggling(level, pos, newToggled);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide()) {
            long currentTick = level.getGameTime();
            if (currentTick != lastUpdateTick) {
                updatedClusterPositions.clear();
                lastUpdateTick = currentTick;
            }
            if (updatedClusterPositions.contains(pos)) {
                super.neighborChanged(state, level, pos, neighborBlock, fromPos, isMoving);
                return;
            }
            boolean isPowered = level.hasNeighborSignal(pos);
            boolean wasPressed = state.getValue(PRESSED);
            if (isPowered && !wasPressed) {
                boolean newToggled = !state.getValue(TOGGLED);
                BlockState newState = state.setValue(PRESSED, true).setValue(TOGGLED, newToggled);
                level.setBlockAndUpdate(pos, newState);
                updatedClusterPositions.add(pos);
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

        int limit = ConfigRegistry.JOHN_ROCK_LIMIT.get();
        int toggledCount = 0;

        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = origin.relative(direction);
            BlockState adjacentState = level.getBlockState(adjacentPos);
            if (adjacentState.getBlock() instanceof johnRock) {
                if (visited.add(adjacentPos)) {
                    queue.add(adjacentPos);
                }
            }
        }

        while (!queue.isEmpty() && toggledCount < limit) {
            BlockPos currentPos = queue.poll();
            updatedClusterPositions.add(currentPos);

            BlockState currentState = level.getBlockState(currentPos);
            if (currentState.getValue(johnRock.TOGGLED) != newToggled) {
                level.setBlockAndUpdate(currentPos, currentState.setValue(johnRock.TOGGLED, newToggled));
            }
            toggledCount++;

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
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if (pState.getValue(TOGGLED)) {
            return Shapes.empty();
        }

        return super.getOcclusionShape(pState, pLevel, pPos);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        if (adjacentBlockState.getBlock() == this) {
            return state.getValue(TOGGLED);
        }
        return super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(TOGGLED) || super.propagatesSkylightDown(state, level, pos);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(TOGGLED) ? 0 : super.getLightBlock(state, level, pos);
    }
}
