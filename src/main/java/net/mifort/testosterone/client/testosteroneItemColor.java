package net.mifort.testosterone.client;

import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class testosteroneItemColor implements ItemColor {

    @Override
    public int getColor(ItemStack pStack, int pTintIndex) {
        if (pStack.getTag() != null) {
            String nbtColor = pStack.getTag().getString("color");

            for (int pId = 0; pId < 16; pId++) {
                if (DyeColor.byId(pId).name().toLowerCase().equals(nbtColor)) {
                    float[] color = DyeColor.byId(pId).getTextureDiffuseColors();
                    return ((int)(color[0] * 255) << 16) | ((int)(color[1] * 255) << 8) | ((int)(color[2] * 255));
                }
            }
        }
        return 0xFFFFFF;
    }

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class sub {

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            event.register(new testosteroneItemColor(), testosteroneModItems.TIE.get());
        }
    }
}
