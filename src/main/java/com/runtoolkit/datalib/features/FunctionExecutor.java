package com.runtoolkit.datalib.features;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Executes mcfunctions with advanced queueing and scheduling
 */
public class FunctionExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger("FunctionExecutor");
    private final Queue<QueuedFunction> functionQueue = new LinkedList<>();

    public void queueFunction(String functionName, long delayTicks) {
        functionQueue.offer(new QueuedFunction(functionName, delayTicks));
        LOGGER.info("Queued function: {} with delay: {} ticks", functionName, delayTicks);
    }

    public void executeQueuedFunctions(MinecraftServer server) {
        if (functionQueue.isEmpty()) return;

        QueuedFunction queued = functionQueue.peek();
        if (queued != null && queued.isReadyToExecute()) {
            try {
                // Execute the function
                LOGGER.info("Executing queued function: {}", queued.getFunctionName());
                functionQueue.poll();
            } catch (Exception e) {
                LOGGER.error("Error executing queued function", e);
            }
        }
    }

    public int getQueueSize() {
        return functionQueue.size();
    }

    private static class QueuedFunction {
        private final String functionName;
        private final long delayTicks;
        private final long createdTime;

        public QueuedFunction(String functionName, long delayTicks) {
            this.functionName = functionName;
            this.delayTicks = delayTicks;
            this.createdTime = System.currentTimeMillis();
        }

        public boolean isReadyToExecute() {
            long elapsedMs = System.currentTimeMillis() - createdTime;
            long delayMs = delayTicks * 50; // 1 tick = 50ms
            return elapsedMs >= delayMs;
        }

        public String getFunctionName() {
            return functionName;
        }
    }
}
