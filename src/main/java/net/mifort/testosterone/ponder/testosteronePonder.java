package net.mifort.testosterone.ponder;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.mifort.testosterone.testosterone;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class testosteronePonder implements PonderPlugin {
    @Override
    public @NotNull String getModId() {
        return testosterone.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        index.register(helper);
    }
}
