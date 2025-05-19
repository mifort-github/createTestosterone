package net.mifort.testosterone.blocks.CT;

import com.simibubi.create.foundation.block.connected.*;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.mifort.testosterone.testosterone;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;

public class testosteroneSpriteShifts {
    public static final CTSpriteShiftEntry AEQUALIS_CUT_CAP = omni("aequalis_cut_cap");

    public static final CTSpriteShiftEntry AEQUALIS_CUT_LAYERED = horizontal("aequalis_cut_layered");
    public static final CTModelProvider LAYERED_AEQUALIS_PROVIDER = new CTModelProvider(new HorizontalCTBehaviour(AEQUALIS_CUT_LAYERED, AEQUALIS_CUT_CAP));

    public static final CTSpriteShiftEntry AEQUALIS_PILLAR_LAYERED = rect("aequalis_cut_pillar");
    public static final CTModelProvider AEQUALIS_PILLAR_PROVIDER = new CTModelProvider(new RotatedPillarCTBehaviour(AEQUALIS_PILLAR_LAYERED, AEQUALIS_CUT_CAP));


    private static CTSpriteShiftEntry omni(String name) {
        return CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL, new ResourceLocation(testosterone.MOD_ID, "block/" + name), new ResourceLocation(testosterone.MOD_ID, "block/" + name + "_connected"));
    }

    private static CTSpriteShiftEntry horizontal(String name) {
        return CTSpriteShifter.getCT(AllCTTypes.HORIZONTAL_KRYPPERS, new ResourceLocation(testosterone.MOD_ID, "block/" + name), new ResourceLocation(testosterone.MOD_ID, "block/" + name + "_connected"));
    }

    private static CTSpriteShiftEntry rect(String name) {
        return CTSpriteShifter.getCT(AllCTTypes.RECTANGLE, new ResourceLocation(testosterone.MOD_ID, "block/" + name), new ResourceLocation(testosterone.MOD_ID, "block/" + name + "_connected"));
    }


    public record CTModelProvider(ConnectedTextureBehaviour behavior) implements NonNullFunction<BakedModel, BakedModel> {
        @Override
        public BakedModel apply(BakedModel bakedModel) {
            return new CTModel(bakedModel, behavior);
        }
    }
}
