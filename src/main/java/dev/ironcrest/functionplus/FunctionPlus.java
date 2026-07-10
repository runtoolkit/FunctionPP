package dev.ironcrest.functionplus;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ironcrest.functionplus.trigger.AdvancedTriggerSystem;
import dev.ironcrest.functionplus.execute.ExecuteConditionRegistry;

public class FunctionPlus implements ModInitializer {
	public static final String MOD_ID = "functionplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Function++ - Enhanced mcfunction/datapack system for 1.20.2");
		
		// Initialize Advanced Trigger System
		AdvancedTriggerSystem.init();
		LOGGER.info("Advanced Trigger System loaded");
		
		// Initialize Execute Conditions
		ExecuteConditionRegistry.register();
		LOGGER.info("Execute Conditions registered");
		
		LOGGER.info("Function++ initialization complete!");
	}
}
