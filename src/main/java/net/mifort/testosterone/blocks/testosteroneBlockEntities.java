package net.mifort.testosterone.blocks;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.decoration.copycat.CopycatBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeBlockEntity;
import net.mifort.testosterone.blocks.decanterCentrifuge.decanterCentrifugeRenderer;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneBlockEntities {
    public static final BlockEntityEntry<decanterCentrifugeBlockEntity> DECANTER_CENTRIFUGE = REGISTRATE.blockEntity("decanter_centrifuge", decanterCentrifugeBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(AllPartialModels.SHAFT), false)
            .validBlock(testosteroneModBlocks.DECANTER_CENTRIFUGE)
            .renderer(() -> decanterCentrifugeRenderer::new)
            .register();

    public static final BlockEntityEntry<CopycatBlockEntity> FRAGILE_COPYCAT =
            REGISTRATE.blockEntity("fragile_copycat", CopycatBlockEntity::new)
                    .validBlocks(testosteroneModBlocks.FRAGILE_COPYCAT_BLOCK)
                    .register();

    public static void register() {

    }
}
