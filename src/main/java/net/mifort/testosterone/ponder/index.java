package net.mifort.testosterone.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.testosterone;

public class index {
    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(testosterone.MOD_ID);

    public static void register() {
        HELPER.forComponents(testosteroneModBlocks.JOHN_ROCK)
                .addStoryBoard("scenes/john_rock", johnScene::scene);
    }
}
