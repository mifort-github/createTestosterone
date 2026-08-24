package net.mifort.testosterone.blocks.blockModels;

import java.util.function.Supplier;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.copycat.CopycatModel;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.mifort.testosterone.blocks.fragileCopycatBase;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class fragileCopycatModel extends CopycatModel {
	public fragileCopycatModel(BakedModel originalModel) {
		super(originalModel);
	}

	@Override
	protected void emitBlockQuadsInner(BlockAndTintGetter blockAndTintGetter, BlockState blockState, BlockPos blockPos,
									   Supplier<RandomSource> supplier, RenderContext renderContext,
									   BlockState material, CullFaceRemovalData cullFaceRemovalData,
									   OcclusionData occlusionData) {

		BakedModel originalModel = getModelOf(material);

		if (material == AllBlocks.COPYCAT_BASE.getDefaultState()) {
			material = testosteroneModBlocks.FRAGILE_COPYCAT_BASE.defaultBlockState().setValue(fragileCopycatBase.STATE, supplier.get().nextInt(5));
			originalModel = getModelOf(material);
		}

		((FabricBakedModel) originalModel).emitBlockQuads(blockAndTintGetter, material, blockPos, supplier, renderContext);
	}
}
