package net.mifort.testosterone.ponder;

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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;

import java.util.Random;

// TODO: make independent from estrogen (translatable)

public class johnScene {
    public static void john_bell(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("bell_conversion", "Bell Conversion");
        scene.configureBasePlate(0, 0, 9);
        scene.showBasePlate();
        Random rand = new Random();

        Selection bellsel = util.select().position(4, 1, 4);
        BlockPos bellpos = util.grid().at(4, 1, 4);
        Selection dream1 = util.select().fromTo(1, 1, 1, 3, 1, 4);
        Selection dream2 = util.select().fromTo(1, 1, 5, 4, 1, 7);
        Selection dream3 = util.select().fromTo(5, 1, 4, 7, 1, 7);
        Selection dream4 = util.select().fromTo(4, 1, 1, 7, 1, 3);

        Selection sele = util.select().fromTo(1, 1, 1, 7, 1, 7);

        BlockPos obToJohn1 = util.grid().at(rand.nextInt(6-2) + 4, 1, 1);
        BlockPos obToJohn2 = util.grid().at(rand.nextInt(3-1) + 1, 1, 4);
        BlockPos obToJohn3 = util.grid().at(rand.nextInt(7-4) + 3, 1, 6);

        BlockPos obToJohn4 = util.grid().at(rand.nextInt(6-2) + 4, 1, 3);
        BlockPos obToJohn5 = util.grid().at(rand.nextInt(3-1) + 1, 1, 2);
        BlockPos obToJohn6 = util.grid().at(rand.nextInt(3-1) + 1, 1, 7);

        scene.world().showSection(bellsel, Direction.SOUTH);
        scene.idle(15);

        scene.overlay().showText(55)
                .pointAt(util.vector().blockSurface(bellpos, Direction.WEST))
                .placeNearTarget()
                .text("When a Peculiar Bell is rung...");
        scene.idle(30);
        scene.world().showSection(dream1, Direction.SOUTH);
        scene.idle(5);
        scene.world().showSection(dream2, Direction.EAST);
        scene.idle(5);
        scene.world().showSection(dream3, Direction.NORTH);
        scene.idle(5);
        scene.world().showSection(dream4, Direction.WEST);
        scene.idle(20);

        scene.overlay().showText(65)
                .pointAt(util.vector().blockSurface(bellpos, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("...any cracked pillar block in an 7x1x7 area of the bell...");
        scene.overlay().showOutline(PonderPalette.GREEN, bellpos, sele, 40);
        scene.idle(75);


        // Right Click
        BlockPos topBell = util.grid().at(4, 1, 4);
        Vec3 topCenter = util.vector().centerOf(topBell);
        scene.overlay().showControls(topCenter, Pointing.DOWN,  70).rightClick();

        scene.idle(5);

        scene.world().modifyBlock(obToJohn1, oldState -> testosteroneModBlocks.JOHN_ROCK.get().defaultBlockState(), true);
        scene.world().modifyBlock(obToJohn2, oldState -> testosteroneModBlocks.JOHN_ROCK.get().defaultBlockState(), true);
        scene.world().modifyBlock(obToJohn3, oldState -> testosteroneModBlocks.JOHN_ROCK.get().defaultBlockState(), true);
        //scene.world.modifyBlock(util.grid.at(x, 3, z), s -> s.setValue(testosteroneModBlocks.JOHN_ROCK, johnRock.TOGGLED));

        scene.idle(75);
        scene.overlay().showText(70)
                .pointAt(util.vector().blockSurface(bellpos, Direction.WEST))
                .placeNearTarget()
                .text("...has a chance to turn into John Rocks");

        scene.idle(85);

        Selection kinetics = util.select().fromTo(8, 2, 6, 8, 1, 9);
        Selection bottomShaft = util.select().position(8, 0, 9);
        Selection deleteDream = util.select().fromTo(4, 1, 5, 4, 1, 6);
        BlockPos deployerPos = util.grid().at(8, 1, 6);

        scene.overlay().showText(80)
                .pointAt(util.vector().blockSurface(bellpos, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("This process can be automated with redstone/deployers and drills");
        scene.world().hideSection(deleteDream, Direction.DOWN);
        scene.idle(25);

        ElementLink<WorldSectionElement> outputElement = scene.world().showIndependentSection(kinetics, Direction.DOWN);
        scene.world().moveSection(outputElement, util.vector().of(-4, 0, 0), 0);
        ElementLink<WorldSectionElement> outputElement2 = scene.world().showIndependentSection(bottomShaft, Direction.DOWN);
        scene.world().moveSection(outputElement2, util.vector().of(-4, 0, 0), 0);

        scene.world().setKineticSpeed(kinetics, 32);
        scene.world().setKineticSpeed(bottomShaft, 32);
        scene.idle(20);
        scene.world().moveDeployer(deployerPos, 1, 25);
        scene.idle(26);
        scene.world().modifyBlock(obToJohn4, oldState -> testosteroneModBlocks.JOHN_ROCK.get().defaultBlockState(), true);
        scene.world().modifyBlock(obToJohn5, oldState -> testosteroneModBlocks.JOHN_ROCK.get().defaultBlockState(), true);
        scene.world().modifyBlock(obToJohn6, oldState -> testosteroneModBlocks.JOHN_ROCK.get().defaultBlockState(), true);
        scene.world().moveDeployer(deployerPos, -1, 25);

        scene.idle(40);
    }


    public static void john_active_inactive(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("john_rock", "John Rock");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(15);

        Selection sbutton = util.select().position(3, 1, 1);
        Selection wire = util.select().position(3, 1, 2);
        Selection redstonepart = util.select().fromTo(3, 1, 1, 3, 1, 2);
        BlockPos bottomJohnPos = util.grid().at(3, 1, 3);
        Selection bottomjohn = util.select().position(3, 1, 3);

        //Satisfactory incoming c = center block, r = right block, l = left block
        Selection lbottomJohn = util.select().position(4, 1, 3);
        Selection rbottomJohn = util.select().position(2, 1, 3);

        Selection lmidJohn = util.select().position(4, 2, 3);
        Selection cmidJohn = util.select().position(3, 2, 3);
        Selection rmidJohn = util.select().position(2, 2, 3);

        Selection ltopJohn = util.select().position(4, 3, 3);
        Selection ctopJohn = util.select().position(3, 3, 3);
        Selection rtopJohn = util.select().position(2, 3, 3);

        Selection allJohns = util.select().fromTo(2, 1, 3, 4, 3, 3);

        scene.world().showSection(bottomjohn, Direction.DOWN);

        scene.idle(5);

        scene.world().showSection(redstonepart, Direction.SOUTH);

        scene.idle(15);

        scene.world().toggleRedstonePower(redstonepart);
        scene.effects().indicateRedstone(util.grid().at(3, 1, 1));
        scene.world().cycleBlockProperty(bottomJohnPos, johnRock.TOGGLED);

        scene.idle(15);

        scene.overlay().showText(70)
                .pointAt(util.vector().blockSurface(bottomJohnPos, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("John Rocks change state when given a redstone signal");
        
		scene.idle(5);

        scene.world().toggleRedstonePower(redstonepart);

        scene.idle(55);

        scene.world().toggleRedstonePower(redstonepart);
		scene.effects().indicateRedstone(util.grid().at(3, 1, 1));
		scene.world().cycleBlockProperty(bottomJohnPos, johnRock.TOGGLED);
        
		scene.idle(20);
        
		scene.world().toggleRedstonePower(redstonepart);
        
		// John Wall Show
		scene.idle(5);
		scene.world().showSection(lbottomJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(rbottomJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(lmidJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(cmidJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(rmidJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(ltopJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(ctopJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world().showSection(rtopJohn, Direction.SOUTH);
        //

		scene.idle(25);

		scene.world().toggleRedstonePower(redstonepart);
        scene.effects().indicateRedstone(util.grid().at(3, 1, 1));
		allJohns.forEach(w -> scene.world().cycleBlockProperty(w, johnRock.TOGGLED));
        
		scene.idle(15);
        
		scene.overlay().showText(60)
                .pointAt(util.vector().blockSurface(bottomJohnPos, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("Multiple John Rocks can be placed next to each other");
        
		scene.idle(5);
        
		scene.world().toggleRedstonePower(redstonepart);
        
		scene.idle(75);
        
		scene.overlay().showText(85)
                .pointAt(util.vector().centerOf(util.grid().at(2, 2, 3)))
                .placeNearTarget()
                .attachKeyFrame()
                .text("While in the inactive state, any entity can go through the block");
        
		scene.idle(30);

        ElementLink<ParrotElement> birb = scene.special().createBirb(util.vector().of(3, 2, 8), ParrotPose.FlappyPose::new);
        scene.special().moveParrot(birb, util.vector().of(0, 0, -300), 1000);
        
		scene.idle(70);
    }



}