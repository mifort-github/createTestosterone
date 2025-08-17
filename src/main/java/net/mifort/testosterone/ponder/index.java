package net.mifort.testosterone.ponder;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class index {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

        ResourceLocation dreamBlockId = new ResourceLocation("estrogen", "dormant_dream_block");
        Block dormantDreamBlock = ForgeRegistries.BLOCKS.getValue(dreamBlockId);

        String schematic = "john_bell";

        if (dormantDreamBlock != Blocks.AIR) {
            schematic = "john_bell_estrogen";
        }

        HELPER.forComponents(testosteroneModBlocks.JOHN_ROCK)
                .addStoryBoard(schematic, johnScene::john_bell);
        HELPER.forComponents(testosteroneModBlocks.JOHN_ROCK)
                .addStoryBoard("john_active_inactive", johnScene::john_active_inactive);
        HELPER.forComponents(testosteroneModBlocks.DECANTER_CENTRIFUGE)
                .addStoryBoard("decanter_main", decanterScene::decanter_main);
    }
}
