package net.mifort.testosterone.effects;

import net.mifort.testosterone.testosterone;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class testosteroneModAttributes {
	public static final Attribute STEP_HEIGHT_ADDITION = Registry.register(
			BuiltInRegistries.ATTRIBUTE,
			new ResourceLocation(testosterone.MOD_ID, "step_height_addition"),
			new RangedAttribute("attribute.testosterone.step_height_addition", 0.0D, 0.0D, 10.0D).setSyncable(true)
	);

	public static void registerAttributes() {

	}
}
