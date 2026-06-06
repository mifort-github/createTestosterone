package net.mifort.testosterone.config;

import net.createmod.catnip.config.ConfigBase;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class testosteroneConfigs {

	private static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);

	private static testosteroneClientConfig client;
	private static testosteroneServerConfig server;

	public static testosteroneClientConfig client() {
		return client;
	}

	public static testosteroneServerConfig server() {
		return server;
	}

	public static void register(ModContainer container) {
		client = register(testosteroneClientConfig::new, ModConfig.Type.CLIENT);
		server = register(testosteroneServerConfig::new, ModConfig.Type.SERVER);

		for (var entry : CONFIGS.entrySet()) {
			container.registerConfig(entry.getKey(), entry.getValue().specification);
		}
	}

	private static <T extends ConfigBase> T register(Supplier<T> factory, ModConfig.Type side) {
		Pair<T, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(builder -> {
			T config = factory.get();
			config.registerAll(builder);
			return config;
		});

		T config = specPair.getLeft();
		config.specification = specPair.getRight();
		CONFIGS.put(side, config);
		return config;
	}
}