package net.mifort.testosterone.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.mifort.testosterone.items.trinkets.tieModel;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;

public class layerRegister {

	public static void register() {
		EntityModelLayerRegistry.registerModelLayer(beardModel.LAYER_LOCATION, beardModel::createBodyLayer);
		EntityModelLayerRegistry.registerModelLayer(tieModel.LAYER_LOCATION, tieModel::createBodyLayer);
		EntityModelLayerRegistry.registerModelLayer(mustacheModel.LAYER_LOCATION, mustacheModel::createBodyLayer);

		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, registrationHelper, context) -> {
			if (renderer instanceof PlayerRenderer playerRenderer) {
				registrationHelper.register(new Layer(playerRenderer));
			}
		});
	}
}
