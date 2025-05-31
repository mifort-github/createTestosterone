package net.mifort.testosterone.chestLoot;

import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = testosterone.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableModifier {
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation tableName = event.getName();

        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("color", "red");

        if (tableName.equals(new ResourceLocation("minecraft", "chests/ancient_city"))) {
            LootPool pool = LootPool.lootPool()
                    .name("tie_pool")
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(testosteroneModItems.TIE)
                            .when(LootItemRandomChanceCondition.randomChance(0.1f)))
//                            .apply(SetNbtFunction.setTag(nbtData)))
                    .build();

            event.getTable().addPool(pool);
        }
    }
}

