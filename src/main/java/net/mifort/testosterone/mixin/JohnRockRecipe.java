package net.mifort.testosterone.mixin;

import com.simibubi.create.content.equipment.bell.PeculiarBellBlock;
import dev.mayaqq.estrogen.registry.EstrogenBlocks;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.config.ConfigRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(PeculiarBellBlock.class)
public abstract class JohnRockRecipe {

    @Inject(method = "playSound", at = @At(value = "HEAD", target = "Lnet/minecraft/client/player/LocalPlayer;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
    private void injectedTouch(Level world, BlockPos pos, CallbackInfo ci) {
        int range = ConfigRegistry.JOHN_ROCK_RANGE.get();
        int vertical = ConfigRegistry.JOHN_ROCK_VERTICAL.get() ? range : 0;
        Random random = new Random();

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -vertical; k <= vertical; k++) {
                    BlockPos blockPos = new BlockPos(pos.getX() + i, pos.getY() + k, pos.getZ() + j);

                    if (world.getBlockState(blockPos).getBlock() == EstrogenBlocks.DORMANT_DREAM_BLOCK.get()) {
                        if (random.nextInt(16) == 0) {
                            world.setBlock(blockPos, testosteroneModBlocks.JOHN_ROCK.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }
}