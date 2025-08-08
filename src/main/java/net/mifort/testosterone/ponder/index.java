package net.mifort.testosterone.ponder;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.resources.ResourceLocation;

public class index {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

        HELPER.forComponents(testosteroneModBlocks.JOHN_ROCK)
                .addStoryBoard("john_bell", johnScene::john_bell);
        HELPER.forComponents(testosteroneModBlocks.JOHN_ROCK)
                .addStoryBoard("john_active_inactive", johnScene::john_active_inactive);
    }
}
