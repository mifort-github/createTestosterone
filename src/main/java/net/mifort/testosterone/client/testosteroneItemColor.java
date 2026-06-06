package net.mifort.testosterone.client;

import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class testosteroneItemColor implements ItemColor {

    @Override
    public int getColor(ItemStack pStack, int pTintIndex) {
        CustomData customData = pStack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            String nbtColor = customData.copyTag().getString("color");

            for (int pId = 0; pId < 16; pId++) {
                if (DyeColor.byId(pId).name().toLowerCase().equals(nbtColor)) {
                    return DyeColor.byId(pId).getTextureDiffuseColor();
                }
            }
        }

        long currentTick = 0;

        if (Minecraft.getInstance().level != null) {
            currentTick = Minecraft.getInstance().level.getGameTime();
        }

        short pId = (short) ((currentTick / 12) % 16);

        return DyeColor.byId(pId).getTextureDiffuseColor();
    }

    @EventBusSubscriber(modid = testosterone.MOD_ID, value = Dist.CLIENT)
    public class sub {

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            event.register(new testosteroneItemColor(), testosteroneModItems.TIE.get());
        }
    }
}