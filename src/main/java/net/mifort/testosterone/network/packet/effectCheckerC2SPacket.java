package net.mifort.testosterone.network.packet;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class effectCheckerC2SPacket {
    int livingEntityID;

    public effectCheckerC2SPacket(int livingEntityID) {
        this.livingEntityID = livingEntityID;
    }

    public effectCheckerC2SPacket(FriendlyByteBuf buf) {
        this.livingEntityID = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(livingEntityID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();

            if (player != null) {
                LivingEntity livingEntity = (LivingEntity) player.level().getEntity(livingEntityID);
                boolean hasEffect = livingEntity.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get());

                int hasEffectInt;

                if (hasEffect) {
                    hasEffectInt = 1;
                } else {
                    hasEffectInt = 0;
                }

                int[] nums = {livingEntityID, hasEffectInt};

                testosteroneModMessages.sendToPlayer(new effectCheckerS2CPacket(nums), player);
//                player.sendSystemMessage(Component.literal(livingEntity + " HAS EFFECT " + hasEffect));
            }
        });
        return true;
    }

}
