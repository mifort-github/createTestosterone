package net.mifort.testosterone.network.packet;

import net.minecraft.network.FriendlyByteBuf;

public class hudS2CPacket {
	private final long[] data;

	public hudS2CPacket(long[] data) {
		this.data = data;
	}

	public hudS2CPacket(FriendlyByteBuf buf) {
		this.data = buf.readLongArray(null);
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeLongArray(data);
	}

	public void handle() {
		ClientEffectData.setHudData(data[0], data[1], data[2]);
	}
}
