package net.mifort.testosterone.datagen;


import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.mifort.testosterone.testosterone;

public class testosteroneDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
        ExistingFileHelper helper = ExistingFileHelper.withResources();
        testosterone.REGISTRATE.setupDatagen(pack, helper);
    }
}
