package net.mifort.testosterone.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;

public class johnScene {
    // Renamed the method to 'scene' so that it follows common naming conventions and is easily registered.
    public static void scene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("john_rock", "John (pork) Rock");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();

        BlockPos pos = util.grid.at(2, 0, 2);
        scene.world.modifyBlock(pos, oldState -> Blocks.OBSIDIAN.defaultBlockState(), false);

        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 5, 5), Direction.DOWN);

    }
}
