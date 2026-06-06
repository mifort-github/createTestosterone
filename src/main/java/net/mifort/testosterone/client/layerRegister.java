package net.mifort.testosterone.client;

import net.mifort.testosterone.items.curios.tieModel;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT)
public class layerRegister {
    @SubscribeEvent
    public static void addLayerEvent(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            addLayerToSkin(event, String.valueOf(skin));
        }
    }

    private static void addLayerToSkin(EntityRenderersEvent.AddLayers event, String skin) {
        PlayerRenderer renderer = event.getSkin(PlayerSkin.Model.valueOf(skin));
        if (renderer == null) return;
        renderer.addLayer(new Layer(renderer));
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(beardModel.LAYER_LOCATION, beardModel::createBodyLayer);
        event.registerLayerDefinition(tieModel.LAYER_LOCATION, tieModel::createBodyLayer);
        event.registerLayerDefinition(mustacheModel.LAYER_LOCATION, mustacheModel::createBodyLayer);
    }
}