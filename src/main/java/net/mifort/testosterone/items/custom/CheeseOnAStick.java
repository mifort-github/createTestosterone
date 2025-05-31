package net.mifort.testosterone.items.custom;

import net.mifort.testosterone.entities.rat.ratEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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
