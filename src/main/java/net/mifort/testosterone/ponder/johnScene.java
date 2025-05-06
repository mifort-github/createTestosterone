package net.mifort.testosterone.ponder;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.ParrotElement;
import net.mifort.testosterone.blocks.johnRock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import com.simibubi.create.foundation.ponder.element.ParrotElement.FlappyPose;

public class johnScene {

    public static void scene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("john_rock", "John Rock");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(15);

        Selection sbutton = util.select.position(3, 1, 1);
        Selection wire = util.select.position(3, 1, 2);
        Selection redstonepart = util.select.fromTo(3, 1, 1, 3, 1, 2);
        BlockPos bottomJohnPos = util.grid.at(3, 1, 3);
        Selection bottomjohn = util.select.position(3, 1, 3);

        //Satisfactory incoming c = center block, r = right block, l = left block
        Selection lbottomJohn = util.select.position(4, 1, 3);
        Selection rbottomJohn = util.select.position(2, 1, 3);

        Selection lmidJohn = util.select.position(4, 2, 3);
        Selection cmidJohn = util.select.position(3, 2, 3);
        Selection rmidJohn = util.select.position(2, 2, 3);

        Selection ltopJohn = util.select.position(4, 3, 3);
        Selection ctopJohn = util.select.position(3, 3, 3);
        Selection rtopJohn = util.select.position(2, 3, 3);

        Selection allJohns = util.select.fromTo(2, 1, 3, 4, 3, 3);

        scene.world.showSection(bottomjohn, Direction.DOWN);

        scene.idle(5);

        scene.world.showSection(redstonepart, Direction.SOUTH);

        scene.idle(15);

        scene.world.toggleRedstonePower(redstonepart);
        scene.effects.indicateRedstone(util.grid.at(3, 1, 1));
        scene.world.cycleBlockProperty(bottomJohnPos, johnRock.TOGGLED);

        scene.idle(15);

        scene.overlay.showText(70)
                .pointAt(util.vector.blockSurface(bottomJohnPos, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("John Rocks change state when given a redstone signal");
        
		scene.idle(5);

        scene.world.toggleRedstonePower(redstonepart);

        scene.idle(55);

        scene.world.toggleRedstonePower(redstonepart);
		scene.effects.indicateRedstone(util.grid.at(3, 1, 1));
		scene.world.cycleBlockProperty(bottomJohnPos, johnRock.TOGGLED);
        
		scene.idle(20);
        
		scene.world.toggleRedstonePower(redstonepart);
        
		// John Wall Show
		scene.idle(5);
		scene.world.showSection(lbottomJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(rbottomJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(lmidJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(cmidJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(rmidJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(ltopJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(ctopJohn, Direction.SOUTH);
		scene.idle(5);
		scene.world.showSection(rtopJohn, Direction.SOUTH);
        //

		scene.idle(25);

		scene.world.toggleRedstonePower(redstonepart);
        scene.effects.indicateRedstone(util.grid.at(3, 1, 1));
		allJohns.forEach(w -> scene.world.cycleBlockProperty(w, johnRock.TOGGLED));
        
		scene.idle(15);
        
		scene.overlay.showText(60)
                .pointAt(util.vector.blockSurface(bottomJohnPos, Direction.WEST))
                .placeNearTarget()
                .attachKeyFrame()
                .text("Multiple John Rocks can be placed next to each other");
        
		scene.idle(5);
        
		scene.world.toggleRedstonePower(redstonepart);
        
		scene.idle(75);
        
		scene.overlay.showText(85)
                .pointAt(util.vector.centerOf(util.grid.at(2, 2, 3)))
                .placeNearTarget()
                .attachKeyFrame()
                .text("While in the inactive state, any entity can go through the block");
        
		scene.idle(30);

        ElementLink<ParrotElement> birb = scene.special.createBirb(util.vector.of(3, 2, 8), FlappyPose::new);
        scene.special.moveParrot(birb, util.vector.of(0, 0, -300), 1000);
        
		scene.idle(70);
    }
}
