package net.mifort.testosterone;

import com.simibubi.create.foundation.data.CreateRegistrate;

import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.createmod.catnip.lang.FontHelper;
import net.fabricmc.api.ModInitializer;

import net.mifort.testosterone.advancements.testosteroneAdvancementUtils;
import net.mifort.testosterone.blocks.testosteroneBlockEntities;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.chestLoot.addTiesModifier;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.effects.testosteroneModAttributes;
import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.entities.testosteroneSpawns;
import net.mifort.testosterone.events.fluidEffectHandler;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.mifort.testosterone.items.addItemModifier;
import net.mifort.testosterone.items.custom.beerMug;
import net.mifort.testosterone.items.testosteroneModCreativeModTabs;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.items.trinkets.trinketsTieRenderer;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.mifort.testosterone.particles.testosteroneModParticles;
import net.mifort.testosterone.potions.testosteroneModPotions;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.mifort.testosterone.sounds.testosteroneModSounds;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testosterone implements ModInitializer {

	public static final String MOD_ID = "testosterone";

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

	static {
		REGISTRATE.setTooltipModifierFactory(item ->
				new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
						.andThen(TooltipModifier.mapNull(KineticStats.create(item)))
		);
	}

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {

		testosteroneModSounds.registerSounds();

		testosteroneModEffects.registerEffects();

		testosteroneModItems.register();

		testosteroneModBlocks.register();

		testosteroneBlockEntities.register();

		testosteroneModPotions.registerPotions();

		testosteroneEntities.registerEntities();

		testosteroneFluids.register();

		testosteroneAdvancementUtils.register();

		testosteroneModRecipes.register();

		beerMug.registerBeerEvents();

		addTiesModifier.register();

		ConfigRegistry.register();

		testosteroneModCreativeModTabs.register();

		fluidEffectHandler.register();

		testosteroneModMessages.registerServerReceivers();

		testosteroneModAttributes.registerAttributes();

		testosteroneSpawns.register();

		addItemModifier.register();

		testosteroneModParticles.registerParticles();



		REGISTRATE.register();
	}


	public static ResourceLocation rl(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
