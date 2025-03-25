package net.mifort.testosterone.client;

import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

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
        long currentTick = ServerLifecycleHooks.getCurrentServer().overworld().getGameTime();

        short pId = (short) ((currentTick / 12) % 16);

        float[] color = DyeColor.byId(pId).getTextureDiffuseColors();
        return ((int)(color[0] * 255) << 16) | ((int)(color[1] * 255) << 8) | ((int)(color[2] * 255));
    }

    @Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class sub {

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            event.register(new testosteroneItemColor(), testosteroneModItems.TIE.get());
        }
    }
}
