package net.mifort.testosterone.blocks;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.mifort.testosterone.blocks.centrifuge.CentrifugeBlockEntity;

import static net.mifort.testosterone.testosterone.REGISTRATE;

public class testosteroneBlockEntities {
    public static final BlockEntityEntry<CentrifugeBlockEntity> TEST = REGISTRATE.blockEntity("test", CentrifugeBlockEntity::new)
            .validBlock(testosteroneModBlocks.TEST)
            .register();

    public static void register() {

    }
}
