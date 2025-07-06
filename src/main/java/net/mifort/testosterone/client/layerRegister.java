package net.mifort.testosterone.client;

import net.mifort.testosterone.items.curios.tieModel;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class layerRegister {
    @SubscribeEvent
    public static void addLayerEvent(EntityRenderersEvent.AddLayers event) {
        Set<String> skins = event.getSkins();

        for (String skin : skins) {
            RenderLayer layer = new Layer((PlayerRenderer) event.getSkin(skin));
            event.getSkin(skin).addLayer(layer);
        }
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(beardModel.LAYER_LOCATION, beardModel::createBodyLayer);
        event.registerLayerDefinition(tieModel.LAYER_LOCATION, tieModel::createBodyLayer);
        event.registerLayerDefinition(mustacheModel.LAYER_LOCATION, mustacheModel::createBodyLayer);
    }
}
