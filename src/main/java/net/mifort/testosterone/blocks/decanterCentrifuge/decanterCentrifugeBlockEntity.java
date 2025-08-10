package net.mifort.testosterone.blocks.decanterCentrifuge;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.mifort.testosterone.recipes.decantation;
import net.mifort.testosterone.recipes.testosteroneModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class decanterCentrifugeBlockEntity extends KineticBlockEntity {

    float tickCount = 0;

    public decanterCentrifugeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        if (level != null && level.isClientSide) return;

        if (this.getBlockState().getBlock() instanceof decanterCentrifugeBlock decanterCentrifugeBlock) {
            float minSpeed = decanterCentrifugeBlock.getMinimumRequiredSpeedLevel().getSpeedValue();


            if (Math.abs(getSpeed()) >= minSpeed) {
                tickCount += Math.abs(getSpeed());

                if (tickCount >= 256) {
                    tickCount -= 256;

                    List<decantation> allRecipes = findRecipe();


                    allRecipes.forEach(decantation -> {

                    });

                }
                return;
            }
        }
        tickCount = 0;
    }


    public List<decantation> findRecipe() {
        Level level = getLevel();

        if (level != null) {
            return level.getRecipeManager()
                    .getAllRecipesFor(testosteroneModRecipes.DECANTATION.getType())
                    .stream()
                    .filter(decantation.class::isInstance)
                    .map(decantation.class::cast)
                    .filter(r -> r.match(this))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }
}