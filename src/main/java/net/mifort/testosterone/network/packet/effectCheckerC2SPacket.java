package net.mifort.testosterone.network.packet;

import net.mifort.testosterone.effects.testosteroneModEffects;
import net.mifort.testosterone.network.testosteroneModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

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
                int effectInt;

                boolean hasTestosterone = livingEntity.hasEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get());
                boolean hasRoidRage = livingEntity.hasEffect(testosteroneModEffects.ROID_RAGE_EFFECT.get());

                if (hasTestosterone && hasRoidRage) {
                    int testosteroneScore = (livingEntity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get()).getAmplifier() + 1) * livingEntity.getEffect(testosteroneModEffects.TESTOSTERONE_EFFECT.get()).getDuration();
                    int roidRageScore = (livingEntity.getEffect(testosteroneModEffects.ROID_RAGE_EFFECT.get()).getAmplifier() + 1) * livingEntity.getEffect(testosteroneModEffects.ROID_RAGE_EFFECT.get()).getDuration();

                    if (testosteroneScore > roidRageScore) {
                        effectInt = 1;
                    } else {
                        effectInt = 2;
                    }

                } else if (hasTestosterone) {
                    effectInt = 1;
                } else if (hasRoidRage) {
                    effectInt = 2;
                } else {
                    effectInt = 0;
                }

                int[] nums = {livingEntityID, effectInt};

                testosteroneModMessages.sendToPlayer(new effectCheckerS2CPacket(nums), player);
            }
        });
        return true;
    }

}
