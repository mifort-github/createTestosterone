package net.mifort.testosterone.mixin;

import com.simibubi.create.content.equipment.bell.PeculiarBellBlock;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.config.ConfigRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.logging.Logger;

import static net.mifort.testosterone.testosterone.LOGGER;

@Mixin(PeculiarBellBlock.class)
public abstract class JohnRockRecipe {

    @Inject(method = "playSound", at = @At(value = "HEAD", target = "Lnet/minecraft/client/player/LocalPlayer;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
    private void onPlaySound(Level world, BlockPos pos, CallbackInfo ci) {
        final ResourceLocation dreamBlockId = new ResourceLocation("estrogen", "dream_block");
        final Block dormantDreamBlock = ForgeRegistries.BLOCKS.getValue(dreamBlockId);


        int range = ConfigRegistry.JOHN_ROCK_RANGE.get();
        int vertical = ConfigRegistry.JOHN_ROCK_VERTICAL.get() ? range : 0;
        Random random = new Random();

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -vertical; k <= vertical; k++) {
                    BlockPos blockPos = new BlockPos(pos.getX() + i, pos.getY() + k, pos.getZ() + j);

                    if ((world.getBlockState(blockPos).getBlock() == dormantDreamBlock && !dormantDreamBlock.defaultBlockState().isAir()) || world.getBlockState(blockPos).is(testosteroneModBlocks.CRACKED_PILLAR.get())) {
                        int actualRange = 2 * range + 1;
                        int chance;

                        if (vertical == 0) {
                            chance = (actualRange * actualRange - 1) / 3;
                        } else {
                            chance = (actualRange * actualRange * actualRange - 1) / 3;
                        }

                        if (random.nextInt(chance) == 0) {
                            world.setBlock(blockPos, testosteroneModBlocks.JOHN_ROCK.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }
}