
package net.mifort.testosterone.items.advanced;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;


public class resetSlotsItem extends Item {
    public resetSlotsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide) {
            CuriosApi.getCuriosInventory(pLivingEntity).ifPresent(inventory -> {
                inventory.clearSlotModifiers();
            });
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
