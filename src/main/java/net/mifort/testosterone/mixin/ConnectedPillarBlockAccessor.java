package net.mifort.testosterone.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(ConnectedPillarBlock.class)
public interface ConnectedPillarBlockAccessor {

    @Invoker("updateColumn")
    BlockState callUpdateColumn(Level level, BlockPos pos, BlockState state, boolean present);
}