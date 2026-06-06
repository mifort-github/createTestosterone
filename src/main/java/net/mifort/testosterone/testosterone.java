package net.mifort.testosterone;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.providers.ProviderType;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.mifort.testosterone.advancements.testosteroneModTriggers;
import net.mifort.testosterone.blocks.testosteroneBlockEntities;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.chestLoot.testosteroneModLootModifiers;
import net.mifort.testosterone.compat.CreateJeiCompat;
import net.mifort.testosterone.config.testosteroneConfigs;
import net.mifort.testosterone.datagen.dataGen;
import net.mifort.testosterone.entities.rat.ratEntity;
import net.mifort.testosterone.entities.rat.ratModel;
import net.mifort.testosterone.entities.rat.ratRenderer;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.entities.testosteroneModelLayers;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.curios.curioTieRenderer;
import net.mifort.testosterone.items.testosteroneModCreativeModTabs;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.packages.TestosteronePackageStyles;
import net.mifort.testosterone.particles.testosteroneModParticles;
import net.mifort.testosterone.ponder.testosteronePonder;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.mifort.testosterone.sounds.testosteroneModSounds;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;


@Mod(testosterone.MOD_ID)
public class testosterone {

    public static final String MOD_ID = "testosterone";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                        .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
        );
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public testosterone(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::loadComplete);

        REGISTRATE.registerEventListeners(modEventBus);

        REGISTRATE.defaultCreativeTab(testosteroneModCreativeModTabs.TESTOSTERONE_TAB.getKey());
        // Register items
        testosteroneModItems.register(modEventBus);

        // Register the creative tab
        testosteroneModCreativeModTabs.register(modEventBus);

        // Register potion effects
        testosteroneModEffects.registerEffects(modEventBus);

        // Register blocks
        testosteroneModBlocks.register();
        testosteroneBlockEntities.register();

        // Register fluids
        testosteroneFluids.register();

        // potions
        testosteroneModPotions.register(modEventBus);

        // entities
        testosteroneEntities.register();

        // config
        testosteroneConfigs.register(modContainer);

        // ADVANCEMENT TRIGGERS
        testosteroneModTriggers.TRIGGERS.register(modEventBus);

        testosteroneModLootModifiers.register(modEventBus);

        testosteroneModSounds.register(modEventBus);

        testosteroneModParticles.PARTICLES.register(modEventBus);

        testosteroneModRecipes.register(modEventBus);


        REGISTRATE.addDataGenerator(ProviderType.LANG, dataGen::registerLang);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(testosteroneFluids::registerFluidInteractions);
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TESTOSTERONE_FLUID.getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TRENBOLONE_FLUID.getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL_FLUID.getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC_FLUID.getSource(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.BEER_FLUID.getSource(), RenderType.translucent());

            CuriosRendererRegistry.register(testosteroneModItems.TIE.get(), curioTieRenderer::new);

            PonderIndex.addPlugin(new testosteronePonder());

            if (ModList.get().isLoaded("jei")) {
                CreateJeiCompat.register();
            }

            TestosteronePackageStyles.TESTOSTERONE_PILL_STYLES.forEach(style -> {
                AllPartialModels.PACKAGES.put(
                        style.getItemId(),
                        PartialModel.of(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + style.getItemId().getPath()))
                );

                AllPartialModels.PACKAGE_RIGGING.put(
                        style.getItemId(),
                        PartialModel.of(style.getRiggingModel())
                );
            });

            TestosteronePackageStyles.TRENBOLONE_VIAL_STYLES.forEach(style -> {
                AllPartialModels.PACKAGES.put(
                        style.getItemId(),
                        PartialModel.of(ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/" + style.getItemId().getPath()))
                );

                AllPartialModels.PACKAGE_RIGGING.put(
                        style.getItemId(),
                        PartialModel.of(style.getRiggingModel())
                );
            });
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(
                    testosteroneEntities.RAT.get(),
                    ratRenderer::new
            );
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(
                    testosteroneModelLayers.RAT_MODEL_LAYER,
                    ratModel::createBodyLayer
            );
        }

        @SubscribeEvent
        public static void onAddPackFinders(AddPackFindersEvent event) throws IOException {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                Path resourcePath = ModList.get()
                        .getModFileById(testosterone.MOD_ID)
                        .getFile()
                        .findResource("programmer_art");

                PackLocationInfo locationInfo = new PackLocationInfo(
                        "testosterone_programmer_art",
                        Component.translatable("pack.testosterone.programmer_art.title"),
                        PackSource.BUILT_IN,
                        Optional.empty()
                );

                event.addRepositorySource(consumer -> {
                    Pack pack = Pack.readMetaAndCreate(
                            locationInfo,
                            new PathPackResources.PathResourcesSupplier(resourcePath),
                            PackType.CLIENT_RESOURCES,
                            new PackSelectionConfig(
                                    false,
                                    Pack.Position.TOP,
                                    false
                            )
                    );

                    if (pack != null) {
                        consumer.accept(pack);
                    }
                });
            }
        }
    }

    @EventBusSubscriber(modid = testosterone.MOD_ID)
    public static class ModEventBusEvents  {

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(
                    testosteroneEntities.RAT.get(),
                    ratEntity.createAttributes().build()
            );
        }
    }
}