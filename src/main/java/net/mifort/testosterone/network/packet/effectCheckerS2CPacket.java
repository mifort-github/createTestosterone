package net.mifort.testosterone.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.mifort.testosterone.client.Layer.EFFECT_CHECKER_KEY;

public class effectCheckerS2CPacket {
    private final int[] data;

    public effectCheckerS2CPacket(int[] hasEffect) {
        this.data = hasEffect;
    }

    public effectCheckerS2CPacket(FriendlyByteBuf buf) {
        this.data = buf.readVarIntArray();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeVarIntArray(data);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!

            Minecraft.getInstance().level.getEntity(data[0]).getPersistentData().putInt(EFFECT_CHECKER_KEY, data[1]);
        });
        return true;
    }


}