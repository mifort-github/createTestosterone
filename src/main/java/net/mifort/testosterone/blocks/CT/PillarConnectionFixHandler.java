package net.mifort.testosterone.blocks.CT;

import net.minecraft.core.BlockPos;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;

import net.mifort.testosterone.mixin.ConnectedPillarBlockAccessor;

@EventBusSubscriber(modid = "testosterone")
public class PillarConnectionFixHandler {
    private static final int RESCAN_DELAY_TICKS = 2;

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel level))
            return;
        if (!(event.getChunk() instanceof LevelChunk chunk))
            return;
        if (!event.isNewChunk())
            return;

        level.getServer().tell(new TickTask(
            level.getServer().getTickCount() + RESCAN_DELAY_TICKS,
            () -> rescanChunk(level, chunk)
        ));
    }

    private static void rescanChunk(ServerLevel level, LevelChunk chunk) {
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        int minX = chunk.getPos().getMinBlockX();
        int minZ = chunk.getPos().getMinBlockZ();
        int minY = level.getMinBuildHeight();
        int maxY = level.getMaxBuildHeight();

        for (int x = minX; x < minX + 16; x++) {
            for (int z = minZ; z < minZ + 16; z++) {
                for (int y = minY; y < maxY; y++) {
                    cursor.set(x, y, z);

                    BlockState state = chunk.getBlockState(cursor);
                    if (!(state.getBlock() instanceof ConnectedPillarBlock pillar))
                        continue;

                    BlockPos fixedPos = cursor.immutable();

                    BlockState newState = ((ConnectedPillarBlockAccessor) pillar)
                        .callUpdateColumn(level, fixedPos, state, true);

                    if (newState != state) {
                        level.setBlock(fixedPos, newState, Block.UPDATE_ALL);
                    }
                }
            }
        }
    }
}