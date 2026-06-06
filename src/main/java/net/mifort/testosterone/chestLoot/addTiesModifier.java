package net.mifort.testosterone.chestLoot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.mifort.testosterone.items.curios.tie;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class addTiesModifier extends LootModifier {

    public static final MapCodec<addTiesModifier> CODEC =
            RecordCodecBuilder.mapCodec(inst ->
                    LootModifier.codecStart(inst)
                            .and(com.mojang.serialization.Codec.FLOAT
                                    .fieldOf("chance")
                                    .forGetter(m -> m.chance))
                            .apply(inst, addTiesModifier::new)
            );

    private final float chance;

    public addTiesModifier(LootItemCondition[] conditions, float chance) {
        super(conditions);
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        float roll = context.getRandom().nextFloat();

        if (roll > chance) {
            return generatedLoot;
        }

        int pId = context.getRandom().nextInt(16);
        DyeColor color = DyeColor.byId(pId);
        String colorName = color.getName().toLowerCase();


        ItemStack tieStack = tie.getTieByColor(colorName);
        if (tieStack.isEmpty()) {
        } else {
            generatedLoot.add(tieStack);
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
