package com.mezsiah.nuclearmachines;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;

import com.mezsiah.nuclearmachines.config.registerBlock;
import com.mezsiah.nuclearmachines.config.registerBlockEntity;
import com.mezsiah.nuclearmachines.config.registerFluid;
import com.mezsiah.nuclearmachines.config.registerItem;
import com.mezsiah.nuclearmachines.config.registerScreenHandler;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NuclearMachines implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MODID = "nuclearmachines";

	public static final Logger LOGGER = LogManager.getLogger();
	public static Registry registry;
	
	

	@Override
	public void onInitialize() {
		 new registerItem(registry);
		 new registerBlock(registry);
		 new registerFluid(registry);
		 new registerBlockEntity();
		 new registerScreenHandler();
	}


	public static void log(String item){
		 LOGGER.info(item);
	}
	
}
