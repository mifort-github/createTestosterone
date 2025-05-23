package net.mifort.testosterone;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.curios.curioTieRenderer;
import net.mifort.testosterone.items.testosteroneModCreativeModTabs;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.ponder.index;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(testosterone.MOD_ID)
public class testosterone {
    public static final String MOD_ID = "testosterone";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE));
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

        // Register fluids
        testosteroneFluids.register();

        // packets
        testosteroneModMessages.register();

        // potions
        testosteroneModPotions.register(modEventBus);

        // config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigRegistry.CLIENT_SPEC, "testosterone-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigRegistry.SERVER_SPEC, "testosterone-server.toml");

        testosteroneAdvancementUtils.register();

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

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL_FLUID.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC_FLUID.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.BEER_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.BEER_FLUID.getSource(), RenderType.translucent());

            CuriosRendererRegistry.register(testosteroneModItems.TIE.get(), () -> new curioTieRenderer());

            index.register();
        }
    }
}