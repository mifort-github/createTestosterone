package net.mifort.testosterone.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.logistics.box.PackageStyles;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.regex.Pattern;

@Mixin(PackageStyles.PackageStyle.class)
class PackageStyleMixin {
    @Unique
    private final Pattern testosterone$IdRegex = Pattern.compile("[A-Za-z]+:.*");
    @Shadow
    @Final
    private String type;

    /**
     * @author mifort
     * @reason create doesn't normally support package textures from other namespaces
     */
    @Inject(method = "getItemId()Lnet/minecraft/resources/ResourceLocation;", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true, remap = false)
    void getItemId(CallbackInfoReturnable<ResourceLocation> cir, @Local(name = "id") String id) {
        // if it has a namespace then there is no reason to convert it
        if (testosterone$IdRegex.asMatchPredicate().test(id)) cir.setReturnValue(new ResourceLocation(id));
    }

    /**
     * @author mifort
     * @reason create doesn't normally support package textures from other namespaces
     */
    @Inject(method = "getRiggingModel()Lnet/minecraft/resources/ResourceLocation;", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true, remap = false)
    void getRiggingModel(CallbackInfoReturnable<ResourceLocation> cir, @Local(name = "size") String size) {
        if (testosterone$IdRegex.asMatchPredicate().test(type)) {
            var namespace = new ResourceLocation(type).getNamespace();
            cir.setReturnValue(new ResourceLocation(namespace, "item/package/rigging_" + size));
        }
    }
}