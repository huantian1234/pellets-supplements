package com.fantasky.ps;

import com.fantasky.ps.datagen.ModModelsProvider;
import com.fantasky.ps.datagen.ModZHCNLanProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PelletsSupplementsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack=fabricDataGenerator.createPack();
		pack.addProvider(ModModelsProvider::new);
		pack.addProvider(ModZHCNLanProvider::new);
	}
}
