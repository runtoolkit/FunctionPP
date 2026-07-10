package com.runtoolkit.datalib.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides debugging utilities for mcfunction execution
 */
public class FunctionDebugger {
    private static final Logger LOGGER = LoggerFactory.getLogger("FunctionDebugger");
    private final List<String> executionLog = new ArrayList<>();
    private boolean debugMode = false;

    public void setDebugMode(boolean enabled) {
        this.debugMode = enabled;
        LOGGER.info("Function debugger {} ", enabled ? "enabled" : "disabled");
    }

    public void logExecution(String functionName, String command, long executionTime) {
        if (debugMode) {
            String logEntry = String.format(
                "[%s] Function: %s | Command: %s | Time: %dms",
                System.currentTimeMillis(), functionName, command, executionTime
            );
            executionLog.add(logEntry);
            LOGGER.debug(logEntry);
        }
    }

    public void logError(String functionName, Exception error) {
        LOGGER.error("Error executing function {}: {}", functionName, error.getMessage());
        if (debugMode) {
            executionLog.add(String.format("[ERROR] %s: %s", functionName, error.getMessage()));
        }
    }

    public List<String> getExecutionLog() {
        return new ArrayList<>(executionLog);
    }

    public void clearExecutionLog() {
        executionLog.clear();
    }
}
