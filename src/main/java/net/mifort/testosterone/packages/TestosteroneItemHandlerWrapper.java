package net.mifort.testosterone.packages;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class TestosteroneItemHandlerWrapper implements TestosteroneItemHandler {

    private final IItemHandler handler;

    public TestosteroneItemHandlerWrapper(IItemHandler handler) {
        this.handler = handler;
    }

    @Override
    public int getSlots() {
        return handler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return handler.getStackInSlot(slot);
    }

    @Override
    public CompoundTag serializeNBT() {
        if (handler instanceof INBTSerializable<?> serializable) {
            Object tag = serializable.serializeNBT(Minecraft.getInstance().level.registryAccess());
            if (tag instanceof CompoundTag compoundTag) {
                return compoundTag;
            }
        }
        return new CompoundTag();
    }
}
