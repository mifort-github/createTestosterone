package net.mifort.testosterone;

import com.simibubi.create.AllPartialModels;

import com.simibubi.create.content.logistics.box.PackageStyles;

import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.ponder.foundation.PonderIndex;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.mifort.testosterone.client.hudOverlay;
import net.mifort.testosterone.client.layerRegister;
import net.mifort.testosterone.client.testosteroneItemColor;
import net.mifort.testosterone.compat.CreateJeiCompat;
import net.mifort.testosterone.config.renderButton;
import net.mifort.testosterone.entities.rat.ratModel;
import net.mifort.testosterone.entities.rat.ratRenderer;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.events.overlayRegistry;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.items.trinkets.trinketsTieRenderer;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.packages.TestosteronePackageStyles;
import net.mifort.testosterone.particles.testosteroneParticlesClientSetup;
import net.mifort.testosterone.ponder.testosteronePonder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class testosteroneClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
		layerRegister.register();

        TrinketRendererRegistry.registerRenderer(
                testosteroneModItems.TIE.get(),
                new trinketsTieRenderer()
        );

		testosteroneItemColor.register();

		if (FabricLoader.getInstance().isModLoaded("jei")) {
			CreateJeiCompat.register();
		}

		renderButton.register();

		hudOverlay.register();

		testosteroneParticlesClientSetup.registerParticleFactories();

		EntityRendererRegistry.register(testosteroneEntities.RAT, ratRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(ratModel.LAYER_LOCATION, ratModel::createBodyLayer);

		registerPackageModels(TestosteronePackageStyles.TESTOSTERONE_PILL_STYLES);
		registerPackageModels(TestosteronePackageStyles.TRENBOLONE_VIAL_STYLES);

		testosteroneModMessages.registerClientReceivers();

		register_transparent_fluids();

		overlayRegistry.register();

		PonderIndex.addPlugin(new testosteronePonder());

		ResourceManagerHelper.registerBuiltinResourcePack(
				new ResourceLocation(testosterone.MOD_ID, "programmer_art"),
				FabricLoader.getInstance().getModContainer(testosterone.MOD_ID).orElseThrow(),
				Component.translatable("pack.testosterone.programmer_art.title"),
				ResourcePackActivationType.NORMAL
		);
    }

	private static void registerPackageModels(List<PackageStyles.PackageStyle> styles) {
		for (PackageStyles.PackageStyle style : styles) {
			ResourceLocation itemId = style.getItemId();
			PartialModel model = PartialModel.of(new ResourceLocation(testosterone.MOD_ID, "item/" + itemId.getPath()));

			AllPartialModels.PACKAGES.put(itemId, model);
			if (!style.rare()) AllPartialModels.PACKAGES_TO_HIDE_AS.add(model);

			AllPartialModels.PACKAGE_RIGGING.put(itemId, PartialModel.of(style.getRiggingModel()));
		}
	}

	private static void register_transparent_fluids() {
		register_transparent_fluids(testosteroneFluids.BEER_FLUID);
		register_transparent_fluids(testosteroneFluids.CHEESE_FLUID);
		register_transparent_fluids(testosteroneFluids.CHOLESTEROL_FLUID);
		register_transparent_fluids(testosteroneFluids.ESTRONE_FLUID);
		register_transparent_fluids(testosteroneFluids.TESTOSTERONE_FLUID);
		register_transparent_fluids(testosteroneFluids.TRENBOLONE_FLUID);
		register_transparent_fluids(testosteroneFluids.WHEY_FLUID);
		register_transparent_fluids(testosteroneFluids.DILUTED_ZINC_FLUID);
	}

	private static void register_transparent_fluids(FluidEntry<? extends SimpleFlowableFluid.Flowing> fluid) {
		BlockRenderLayerMap.INSTANCE.putFluid(fluid.get(), RenderType.translucent());
		BlockRenderLayerMap.INSTANCE.putFluid(fluid.getSource(), RenderType.translucent());
	}
}
