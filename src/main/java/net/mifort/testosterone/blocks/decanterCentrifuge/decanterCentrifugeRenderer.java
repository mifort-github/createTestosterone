package net.mifort.testosterone.blocks.decanterCentrifuge;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class decanterCentrifugeRenderer extends KineticBlockEntityRenderer<decanterCentrifugeBlockEntity> {

	public decanterCentrifugeRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(decanterCentrifugeBlockEntity be, BlockState state) {
		return CachedBuffers.partial(AllPartialModels.SHAFT, state);
	}

}
