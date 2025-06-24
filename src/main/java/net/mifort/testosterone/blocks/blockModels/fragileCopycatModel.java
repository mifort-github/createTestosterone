package net.mifort.testosterone.blocks.blockModels;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.copycat.CopycatModel;
import net.mifort.testosterone.blocks.fragileCopycatBase;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

import java.util.List;

public class fragileCopycatModel extends CopycatModel {
    public fragileCopycatModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    protected List<BakedQuad> getCroppedQuads(BlockState blockState, Direction direction, RandomSource randomSource, BlockState material, ModelData modelData, RenderType renderType) {
        BakedModel originalModel = getModelOf(material);

        if (material == AllBlocks.COPYCAT_BASE.getDefaultState()) {
            material = testosteroneModBlocks.FRAGILE_COPYCAT_BASE.getDefaultState().setValue(fragileCopycatBase.STATE, randomSource.nextInt(5));
            originalModel = getModelOf(material);
        }
        return originalModel.getQuads(material, direction, randomSource, modelData, renderType);
    }
}
