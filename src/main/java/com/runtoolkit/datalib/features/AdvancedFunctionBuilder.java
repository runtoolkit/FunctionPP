package com.runtoolkit.datalib.features;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Feature 3: Advanced Function Builder
 * Provides utilities for building complex mcfunction sequences with debugging
 */
public class AdvancedFunctionBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger("AdvancedFunctionBuilder");
    private static final FunctionDebugger debugger = new FunctionDebugger();
    private static final FunctionExecutor executor = new FunctionExecutor();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(AdvancedFunctionBuilder::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        // Execute queued functions
        executor.executeQueuedFunctions(server);
    }

    public static FunctionDebugger getDebugger() {
        return debugger;
    }

    public static FunctionExecutor getExecutor() {
        return executor;
    }
}
