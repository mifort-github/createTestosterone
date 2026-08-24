package net.mifort.testosterone.packages;

import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface TestosteroneItemHandler {
    int getSlots();

    @NotNull ItemStack getStackInSlot(int slot);

    CompoundTag serializeNBT();
}
