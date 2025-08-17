package net.mifort.testosterone.ponder;

import com.simibubi.create.AllFluids;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.ParrotElement;
import net.createmod.ponder.api.element.ParrotPose;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.mifort.testosterone.blocks.johnRock;
import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.fluids.testosteroneFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Random;

// TODO: make independent from estrogen (translatable)

public class decanterScene {
    public static void decanter_main(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("decanter_main", "Converting Fluids in The Decanter Centrifuge");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        Random rand = new Random();

        BlockPos decanter1 = util.grid().at(3, 2, 3);
        Selection decanter1sele = util.select().fromTo(3, 2, 3,3,2,3);
        BlockPos decanter2 = util.grid().at(3, 3, 3);
        Selection decanter2sele = util.select().fromTo(3, 3, 3,3,3,3);
        Selection tank1 = util.select().fromTo(3, 1, 2, 3, 3, 2);
        Selection tank2 = util.select().fromTo(3, 1, 4, 3, 3, 4);
        Selection extended = util.select().fromTo(7, 1, 3, 3, 1, 3);
        Selection gear = util.select().fromTo(7, 0, 2, 7, 0, 2);
        BlockPos outlineStart1 = util.grid().at(3, 1, 3);
        BlockPos outlineStart2 = util.grid().at(3, 2, 2);

        AABB bbRed1 = new AABB(Vec3.ZERO, Vec3.ZERO).inflate(0.5, 0, 0.5).move(0, 0, 0); //First outline prep
        AABB bbRed2 = new AABB(Vec3.ZERO, Vec3.ZERO).inflate(0.5, 1.5, 0.5).move(0, 0, 0);
        Object shaft = new Object();

        BlockPos tankPos1 = util.grid().at(3, 1, 2);
        BlockPos tankPos2 = util.grid().at(3, 1, 4);
        FluidStack cholesterol = new FluidStack(testosteroneFluids.CHOLESTEROL_FLUID.get()
                .getSource(), 16000);
        FluidStack testosterone = new FluidStack(testosteroneFluids.TESTOSTERONE_FLUID.get()
                .getSource(), 400);


        scene.world().setKineticSpeed(decanter1sele, 0); //sets speed to 0
        scene.world().setKineticSpeed(decanter2sele, 0);


        scene.world().showSection(decanter1sele, Direction.DOWN); //summons centrifuge in air
        scene.idle(15);


        scene.overlay().showText(60)
                .pointAt(util.vector().blockSurface(decanter1, Direction.WEST))
                .placeNearTarget()
                .text("The Decanter Centrifuge converts fluids into other fluids between Tanks");
        scene.idle(70);


        ElementLink<WorldSectionElement> outputElement = scene.world().showIndependentSection(gear, Direction.SOUTH);
        scene.world().moveSection(outputElement, util.vector().of(0, 0, 0), 0);
        ElementLink<WorldSectionElement> outputElement2 = scene.world().showIndependentSection(extended, Direction.SOUTH);
        scene.world().moveSection(outputElement2, util.vector().of(0, 0, 0), 0);
        scene.world().setKineticSpeed(gear, -128);
        scene.world().setKineticSpeed(extended, 256);
        scene.idle(10);
        scene.world().setKineticSpeed(decanter1sele, 256);
        scene.idle(15);

        scene.overlay().showText(75)
                .pointAt(util.vector().blockSurface(decanter1, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("The centrifuge requires at least 100 RPM to work, connected at either the top or the bottom...");

        Selection connection1 = util.select().fromTo(3, 1, 3,3,3,3);
        scene.idle(10);
        scene.overlay().chaseBoundingBoxOutline(PonderPalette.RED, shaft, bbRed1.move(util.vector().centerOf(3, 2, 3)), 3);
        scene.idle(2);
        scene.overlay().chaseBoundingBoxOutline(PonderPalette.RED, shaft, bbRed2.move(util.vector().centerOf(3, 2, 3)), 50);
        scene.idle(75);

        AABB bb1 = new AABB(Vec3.ZERO, Vec3.ZERO).inflate(0.5, 0.5, 0).move(0, 0, 0); //Second outline prep
        AABB bb2 = new AABB(Vec3.ZERO, Vec3.ZERO).inflate(0.5, 0.5, 1.5).move(0, 0, 0);
        Object fluids = new Object();




        scene.world().showSection(tank1, Direction.WEST); //summons tank1
        scene.world().modifyBlockEntity(tankPos1, FluidTankBlockEntity.class, be -> be.getTankInventory()
                .fill(cholesterol, IFluidHandler.FluidAction.EXECUTE));
        scene.idle(5);
        scene.world().showSection(tank2, Direction.WEST); //summons tank2

        scene.idle(10);

        scene.overlay().showText(65)
                .pointAt(util.vector().blockSurface(decanter1, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("...with the sides used for fluid conversion.");

        Selection connection2 = util.select().fromTo(3, 2, 2,3,2,4);
        scene.idle(10);
        scene.overlay().chaseBoundingBoxOutline(PonderPalette.INPUT, fluids, bb1.move(util.vector().centerOf(3, 2, 3)), 3);
        scene.idle(2);
        scene.overlay().chaseBoundingBoxOutline(PonderPalette.INPUT, fluids, bb2.move(util.vector().centerOf(3, 2, 3)), 50);
        scene.idle(10);


        for (int i = 0; i < 50; i++) {
            final int drainAmount = (i < 36) ? 400
                    : (i == 36 ? 300
                    : (i == 37 ? 200
                    : (i > 37 ? 100
                    : 100)));
            scene.world().modifyBlockEntity(tankPos1, FluidTankBlockEntity.class,
                    be -> be.getTankInventory().drain(drainAmount, IFluidHandler.FluidAction.EXECUTE));
            scene.world().modifyBlockEntity(tankPos2, FluidTankBlockEntity.class,
                    be -> be.getTankInventory().fill(testosterone, IFluidHandler.FluidAction.EXECUTE));
            scene.idle(1);
        }
        scene.idle(25);
        scene.rotateCameraY(-90);
        scene.idle(40);

        scene.world().showSection(decanter2sele, Direction.DOWN); //summons second centrifuge
        scene.idle(10);
        scene.world().setKineticSpeed(decanter2sele, 256);
        scene.idle(5);

        scene.overlay().showText(65)
                .pointAt(util.vector().blockSurface(decanter2, Direction.SOUTH))
                .placeNearTarget()
                .attachKeyFrame()
                .text("Centrifuges can be stacked on top of each other.");
        scene.overlay().showOutline(PonderPalette.RED, decanter2, decanter2sele, 65);
        scene.idle(80);


    }
}