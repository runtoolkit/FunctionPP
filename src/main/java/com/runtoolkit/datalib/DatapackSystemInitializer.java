package com.runtoolkit.datalib;

import com.runtoolkit.datalib.features.AdvancedFunctionBuilder;
import com.runtoolkit.datalib.features.CommandAliasSystem;
import com.runtoolkit.datalib.features.ConditionalDatapackLoader;

/**
 * Initializes all three datapack system features
 */
public class DatapackSystemInitializer {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("DatapackSystemInitializer");

    public static void initializeAllFeatures() {
        LOGGER.info("Initializing all datapack system features...");
        
        try {
            CommandAliasSystem.register();
            LOGGER.info("✓ Command Alias System initialized");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Command Alias System", e);
        }
        
        try {
            ConditionalDatapackLoader.register();
            LOGGER.info("✓ Conditional Datapack Loader initialized");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Conditional Datapack Loader", e);
        }
        
        try {
            AdvancedFunctionBuilder.register();
            LOGGER.info("✓ Advanced Function Builder initialized");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Advanced Function Builder", e);
        }
        
        LOGGER.info("All datapack system features are ready!");
    }
}
