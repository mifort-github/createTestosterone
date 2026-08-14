package net.mifort.testosterone.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;

import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@Mixin(ConnectedPillarBlock.class)
public abstract class ConnectedPillarBlockMixin {
    public BlockState rotate(BlockState state, Rotation rotation) {
        if (rotation == Rotation.NONE)
            return state;

        Axis axis = state.getValue(BlockStateProperties.AXIS);

        boolean north = state.getValue(ConnectedPillarBlock.NORTH);
        boolean south = state.getValue(ConnectedPillarBlock.SOUTH);
        boolean east = state.getValue(ConnectedPillarBlock.EAST);
        boolean west = state.getValue(ConnectedPillarBlock.WEST);

        return switch (rotation) {
            case CLOCKWISE_180 -> applyRotation180(state, axis, north, south, east, west);
            case CLOCKWISE_90, COUNTERCLOCKWISE_90 -> applyRotation90(state, axis, rotation, north, south, east, west);
            default -> state;
        };
    }

    @Unique
    private static BlockState applyRotation180(BlockState state, Axis axis,
            boolean north, boolean south, boolean east, boolean west) {
        state = state.setValue(ConnectedPillarBlock.NORTH, south)
            .setValue(ConnectedPillarBlock.SOUTH, north);

        // EAST/WEST are literal compass directions only when AXIS == Y.
        // For X/Z axis pillars they encode up/down and must be left alone.
        if (axis == Axis.Y) {
            state = state.setValue(ConnectedPillarBlock.EAST, west)
                .setValue(ConnectedPillarBlock.WEST, east);
        }

        return state;
    }

    @Unique
    private static BlockState applyRotation90(BlockState state, Axis axis, Rotation rotation,
            boolean north, boolean south, boolean east, boolean west) {
        if (axis == Axis.Y) {
            if (rotation == Rotation.CLOCKWISE_90) {
                state = state.setValue(ConnectedPillarBlock.NORTH, west)
                    .setValue(ConnectedPillarBlock.EAST, north)
                    .setValue(ConnectedPillarBlock.SOUTH, east)
                    .setValue(ConnectedPillarBlock.WEST, south);
            } else {
                state = state.setValue(ConnectedPillarBlock.NORTH, east)
                    .setValue(ConnectedPillarBlock.EAST, south)
                    .setValue(ConnectedPillarBlock.SOUTH, west)
                    .setValue(ConnectedPillarBlock.WEST, north);
            }
        }

        return state;
    }
}