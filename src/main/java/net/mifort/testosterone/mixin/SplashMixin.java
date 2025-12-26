package net.mifort.testosterone.mixin;

import net.minecraft.client.resources.SplashManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SplashManager.class)
public class SplashMixin {
    @Unique
    private static final List<String> createTestosterone$testosteroneSplashes = List.of(
        "Now with testosterone!",
        "Also try TREN",
        "Pumping iron since 2025!",
        "Let's go Jim",
        ">:)",
        "Vazkii is a mod by Neat",
        "To the Bone!",
        "Literally me",
        "-SUCCESS-",
        "Under Your Spell",
        "You look like a good Joe",
        "greg.",
        "May your vows be many...",
        "...and your days few.",
        "Nah, I'd win.",
        "It's Time To Kick Gum And Chew Ass!"
    );

    @Shadow @Final private List<String> splashes;

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At(value = "TAIL"))
    protected void apply(List<String> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler, CallbackInfo ci) {
        splashes.addAll(createTestosterone$testosteroneSplashes);
    }
}
