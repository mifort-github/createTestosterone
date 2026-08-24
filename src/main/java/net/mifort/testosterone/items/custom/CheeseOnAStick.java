package net.mifort.testosterone.items.custom;

import org.jetbrains.annotations.NotNull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CheeseOnAStick extends Item {
    public CheeseOnAStick(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!world.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTag();
            boolean current = tag.getBoolean("Boost");

            tag.putBoolean("Boost", !current);
        }


        return InteractionResultHolder.success(stack);
    }
}
