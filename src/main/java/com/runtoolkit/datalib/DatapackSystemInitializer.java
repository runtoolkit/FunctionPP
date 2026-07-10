package com.runtoolkit.datalib;

import com.runtoolkit.datalib.features.AdvancedFunctionBuilder;
import com.runtoolkit.datalib.features.CommandAliasSystem;
import com.runtoolkit.datalib.features.ConditionalDatapackLoader;

/**
 * Initializes all three datapack system features
 */
public class DatapackSystemInitializer {
    public static void initializeAllFeatures() {
        DatapackSystemMod.LOGGER.info("Initializing all datapack system features...");
        
        // Initialize Feature 1: Command Alias System
        CommandAliasSystem.register();
        DatapackSystemMod.LOGGER.info("✓ Command Alias System initialized");
        
        // Initialize Feature 2: Conditional Datapack Loader
        ConditionalDatapackLoader.register();
        DatapackSystemMod.LOGGER.info("✓ Conditional Datapack Loader initialized");
        
        // Initialize Feature 3: Advanced Function Builder
        AdvancedFunctionBuilder.register();
        DatapackSystemMod.LOGGER.info("✓ Advanced Function Builder initialized");
        
        DatapackSystemMod.LOGGER.info("All datapack system features are ready!");
    }
}
