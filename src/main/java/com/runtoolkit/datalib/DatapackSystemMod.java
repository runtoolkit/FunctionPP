package com.runtoolkit.datalib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatapackSystemMod implements ModInitializer {
    public static final String MOD_ID = "datalib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Datalib Mod initialized for Minecraft 1.20.1");
        
        // Register datapack system event listeners
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            LOGGER.info("Server started - Datapack System Features activated");
        });
    }
}
