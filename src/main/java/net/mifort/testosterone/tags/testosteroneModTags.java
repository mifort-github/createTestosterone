package net.mifort.testosterone.tags;

import net.mifort.testosterone.testosterone;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class testosteroneModTags {
    public static class Items {

        public static final TagKey<Item> HAS_CHOLESTEROL = tag("has_cholesterol");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(testosterone.MOD_ID, name));
        }
    }

    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(testosterone.MOD_ID, name));
        }
    }
}
