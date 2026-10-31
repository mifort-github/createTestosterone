package net.mifort.testosterone.entities;

import net.mifort.testosterone.entities.rat.ratModel;
import net.mifort.testosterone.entities.rat.ratRenderer;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.rebar.concrete_lib.lib.mobs.CLMobClient;

public class testosteroneModelLayers {
	public static void registerLayers() {
		CLMobClient.layer(ratModel.LAYER_LOCATION, ratModel::createBodyLayer);
	}

	public static void registerRenderers() {
		CLMobClient.renderer(testosteroneEntities.RAT, ratRenderer::new);
	}
}
