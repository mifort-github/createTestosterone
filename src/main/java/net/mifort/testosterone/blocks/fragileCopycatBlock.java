package net.mifort.testosterone.blocks;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.copycat.CopycatBlock;
import net.mifort.testosterone.config.testosteroneConfigs;
import net.mifort.testosterone.effects.roidRageEffect;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class fragileCopycatBlock extends CopycatBlock {
    public fragileCopycatBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canConnectTexturesToward(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos, BlockPos blockPos1, BlockState blockState) {
        return true;
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        if (pContext instanceof EntityCollisionContext entityCollisionContext) {
            Entity entity = entityCollisionContext.getEntity();
            if (entity instanceof Player player && player.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT) && roidRageEffect.getSpeed(player) > testosteroneConfigs.server().abilitySpeed.get()) {
                if (pLevel instanceof Level level && !level.isClientSide()) {
                    level.destroyBlock(pPos, true, player);
                }

                return Shapes.empty();
            }
        }

        return Shapes.block();
    }

    @Override
    public boolean hidesNeighborFace(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull BlockState neighborState, @NotNull Direction dir) {
        BlockState material = getMaterial(level, pos);

        if (neighborState == AllBlocks.COPYCAT_BASE.getDefaultState()) {
            return false;
        }

        return material == neighborState;
    }

    @Override
    public boolean canFaceBeOccluded(BlockState state, Direction face) {
        return true;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return testosteroneBlockEntities.FRAGILE_COPYCAT.create(p_153215_, p_153216_);
    }
}
