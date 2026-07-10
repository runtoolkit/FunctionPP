package com.runtoolkit.datalib.features;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Feature 2: Conditional Datapack Loader
 * Allows datapacks to be loaded conditionally based on server properties or world data
 */
public class ConditionalDatapackLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger("ConditionalDatapackLoader");
    private static final DatapackConditionChecker conditionChecker = new DatapackConditionChecker();

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(ConditionalDatapackLoader::onServerStart);
    }

    private static void onServerStart(MinecraftServer server) {
        LOGGER.info("Checking conditional datapack requirements...");
        conditionChecker.checkWorldBorder(server);
        conditionChecker.checkDifficulty(server);
        conditionChecker.checkExperimentalFeatures(server);
        LOGGER.info("Conditional datapack loading completed");
    }

    public static DatapackConditionChecker getConditionChecker() {
        return conditionChecker;
    }
}
