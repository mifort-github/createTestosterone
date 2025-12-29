package net.mifort.testosterone;

import com.mojang.logging.LogUtils;
//import com.simibubi.create.compat.jei.ConversionRecipe;
//import com.simibubi.create.compat.jei.category.MysteriousItemConversionCategory;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.blocks.testosteroneBlockEntities;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.chestLoot.testosteroneModLootModifiers;
import net.mifort.testosterone.compat.CreateJeiCompat;
import net.mifort.testosterone.config.ConfigRegistry;
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
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.packages.TestosteronePackageStyles;
import net.mifort.testosterone.particles.testosteroneModParticles;
import net.mifort.testosterone.ponder.testosteronePonder;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.mifort.testosterone.sounds.testosteroneModSounds;
import net.minecraft.SharedConstants;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import java.nio.file.Path;

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
        return new ResourceLocation(MOD_ID, path);
    }


    public testosterone() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::loadComplete);

        REGISTRATE.registerEventListeners(modEventBus);

        // Register items
        testosteroneModItems.register(modEventBus);

        // Register the creative tab
        testosteroneModCreativeModTabs.register(modEventBus);

        // Register potion effects
        testosteroneModEffects.registerEffects();

        // Register blocks
        testosteroneModBlocks.register();
        testosteroneBlockEntities.register();

        // Register fluids
        testosteroneFluids.register();

        // packets
        testosteroneModMessages.register();

        // potions
        testosteroneModPotions.register(modEventBus);

        // entities
        testosteroneEntities.register(modEventBus);

        // config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigRegistry.CLIENT_SPEC, "testosterone-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigRegistry.SERVER_SPEC, "testosterone-server.toml");

        testosteroneAdvancementUtils.register();

        testosteroneModLootModifiers.register(modEventBus);

        testosteroneModSounds.register(modEventBus);

        testosteroneModParticles.PARTICLES.register(modEventBus);

        testosteroneModRecipes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
         event.enqueueWork(testosteroneFluids::registerFluidInteractions);
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TESTOSTERONE_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TESTOSTERONE_FLUID.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TRENBOLONE_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TRENBOLONE_FLUID.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL_FLUID.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC_FLUID.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.BEER_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.BEER_FLUID.getSource(), RenderType.translucent());


            CuriosRendererRegistry.register(testosteroneModItems.TIE.get(), curioTieRenderer::new);

            PonderIndex.addPlugin(new testosteronePonder());

            EntityRenderers.register(testosteroneEntities.RAT.get(), ratRenderer::new);

            if (ModList.get().isLoaded("jei")) {
                CreateJeiCompat.register();
            }

            TestosteronePackageStyles.TESTOSTERONE_PILL_STYLES.forEach(style -> {
                AllPartialModels.PACKAGES.put(
                        style.getItemId(),
                        PartialModel.of(new ResourceLocation(MOD_ID, "item/" + style.getItemId().getPath()))
                );

                AllPartialModels.PACKAGE_RIGGING.put(
                        style.getItemId(),
                        PartialModel.of(style.getRiggingModel())
                );
            });

            TestosteronePackageStyles.TRENBOLONE_VIAL_STYLES.forEach(style -> {
                AllPartialModels.PACKAGES.put(
                        style.getItemId(),
                        PartialModel.of(new ResourceLocation(MOD_ID, "item/" + style.getItemId().getPath()))
                );

                AllPartialModels.PACKAGE_RIGGING.put(
                        style.getItemId(),
                        PartialModel.of(style.getRiggingModel())
                );
            });

        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(
                    testosteroneModelLayers.RAT_MODEL_LAYER,
                    ratModel::createBodyLayer
            );
        }

        @SubscribeEvent
        public static void onAddPackFinders(AddPackFindersEvent event) {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                Path resourcePath = ModList.get().getModFileById(testosterone.MOD_ID).getFile().findResource("programmer_art");
                PathPackResources pack = new PathPackResources(ModList.get().getModFileById(testosterone.MOD_ID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
                PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.testosterone.programmer_art.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
                event.addRepositorySource((source) ->
                        source.accept(Pack.create(
                                "testosterone_programmer_art",
                                Component.translatable("pack.testosterone.programmer_art.title"),
                                false,
                                (string) -> pack,
                                new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                                PackType.CLIENT_RESOURCES,
                                Pack.Position.TOP,
                                false,
                                PackSource.BUILT_IN)
                        )
                );
            }
        }
    }

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents  {
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(testosteroneEntities.RAT.get(), ratEntity.createAttributes().build());
        }
    }
}