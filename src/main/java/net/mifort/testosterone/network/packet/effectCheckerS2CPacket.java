package net.mifort.testosterone.network.packet;

import net.minecraft.network.FriendlyByteBuf;

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

	public void handle() {
		ClientEffectData.setEffect(data[0], data[1]);
	}
}
