package net.mifort.testosterone.network.packet;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static net.mifort.testosterone.client.hudOverlay.*;

public class hudS2CPacket {
    private final long[] data;

    public hudS2CPacket(long[] Data) {
        this.data = Data;
    }

    public hudS2CPacket(FriendlyByteBuf buf) {
        this.data = buf.readLongArray();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeLongArray(data);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            LocalPlayer player = Minecraft.getInstance().player;
            player.getPersistentData().putLong(END_OF_COOLDOWN_TICK_KEY, data[0]);
            player.getPersistentData().putLong(ACTUAL_BEGIN_TICK_KEY, data[1]);
            player.getPersistentData().putLong(BEGIN_TICK_KEY, data[0] - data[1]);
            player.getPersistentData().putLong(DURATION_KEY, data[2]);
        });
        return true;
    }


}