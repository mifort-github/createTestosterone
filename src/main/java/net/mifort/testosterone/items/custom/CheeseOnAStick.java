package net.mifort.testosterone.items.custom;

import net.mifort.testosterone.entities.rat.ratEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CheeseOnAStick extends Item {
    public CheeseOnAStick(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!world.isClientSide() && player.getVehicle() instanceof ratEntity) {
            CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
            boolean current = customData.copyTag().getBoolean("Boost");

            stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, data -> {
                var tag = data.copyTag();
                tag.putBoolean("Boost", !current);
                return CustomData.of(tag);
            });
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }
}