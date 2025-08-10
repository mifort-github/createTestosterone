package net.mifort.testosterone.blocks;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlockEntity;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeRenderer;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneBlockEntities {
    public static final BlockEntityEntry<decanterCentrifugeBlockEntity> TEST = REGISTRATE.blockEntity("test", decanterCentrifugeBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(AllPartialModels.SHAFT), false)
            .validBlock(testosteroneModBlocks.TEST)
            .renderer(() -> decanterCentrifugeRenderer::new)
            .register();

    public static void register() {

    }
}
