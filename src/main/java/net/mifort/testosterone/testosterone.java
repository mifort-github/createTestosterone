package net.mifort.testosterone;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.testosteroneModCreativeModTabs;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
//import top.theillusivec4.curios.api.SlotTypeMessage;
//
//import static top.theillusivec4.curios.api.SlotTypeMessage.REGISTER_TYPE;

// The value here should match an entry in the META-INF/mods.toml file
@SuppressWarnings("UnstableApiUsage")
@Mod(testosterone.MOD_ID)
public class testosterone {
    public static final String MOD_ID = "testosterone";

    // add create mod tooltips (hopefully)
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE));
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }


    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public testosterone() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRATE.registerEventListeners(modEventBus);


        // Register items
        testosteroneModItems.register(modEventBus);

        // Register the creative tab
        testosteroneModCreativeModTabs.register(modEventBus);

        // Register potion effects
        testosteroneModEffects.registerEffects();

        // Register blocks
        testosteroneModBlocks.register(modEventBus);

        // Register fluids
        testosteroneFluids.register();

        // packets
        testosteroneModMessages.register();

        // potions
        testosteroneModPotions.register(modEventBus);



//        InterModComms.sendTo("curios", REGISTER_TYPE, () -> new SlotTypeMessage.Builder("necklace").build());


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TESTOSTERONE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.TESTOSTERONE.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.CHOLESTEROL.getSource(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(testosteroneFluids.DILUTED_ZINC.getSource(), RenderType.translucent());
        }
    }
}