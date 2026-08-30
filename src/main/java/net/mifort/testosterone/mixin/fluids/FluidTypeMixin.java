package net.mifort.testosterone.mixin.fluids;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.mifort.testosterone.fluids.testosteroneFluids;
import net.minecraft.world.level.material.Fluid;

import io.github.fabricators_of_create.porting_lib.fluids.FluidType;

@Mixin(Fluid.class)
public class FluidTypeMixin {
	@Dynamic("getFluidType is injected by Porting Lib")
	@Inject(method = "getFluidType", at = @At("HEAD"), cancellable = true)
	private void testosterone$handleCustomFluids(CallbackInfoReturnable<FluidType> cir) {
		FluidType type = testosteroneFluids.getTypeOf((Fluid) (Object) this);
		if (type != null) {
			cir.setReturnValue(type);
		}
	}
}
