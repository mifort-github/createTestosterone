package net.mifort.testosterone.blocks.centrifuge;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.mifort.testosterone.recipes.centrifugingRecipe;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class CentrifugeBlockEntity extends KineticBlockEntity {

    public CentrifugeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        if (level != null && level.isClientSide) return;

        List<centrifugingRecipe> allRecipes = findRecipe();

        allRecipes.forEach(centrifugingRecipe -> {

        });

    }


    public List<centrifugingRecipe> findRecipe() {
        Level level = getLevel();

        if (level != null) {
            return level.getRecipeManager()
                    .getAllRecipesFor(testosteroneModRecipes.CENTRIFUGING.getType())
                    .stream()
                    .filter(centrifugingRecipe.class::isInstance)
                    .map(centrifugingRecipe.class::cast)
                    .filter(r -> r.match(this))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }
}