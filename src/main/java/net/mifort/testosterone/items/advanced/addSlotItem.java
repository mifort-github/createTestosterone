
package net.mifort.testosterone.items.advanced;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;


public class addSlotItem extends Item {
    public addSlotItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            // CuriosApi.getCuriosInventory(pLivingEntity).ifPresent(inventory -> inventory.addTransientSlotModifier("necklace", UUID.randomUUID(), "necklace", 1, AttributeModifier.Operation.ADDITION));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
