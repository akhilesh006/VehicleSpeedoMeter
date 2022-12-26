package com.app.eularmotor.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor class for background activity on constant pool.
 */
public class DefaultThreadExecutorSupplier {

    private static final DefaultThreadExecutorSupplier INSTANCE = new DefaultThreadExecutorSupplier();
    private final ExecutorService executorService;

    private DefaultThreadExecutorSupplier() {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(NUMBER_OF_CORES * 2);
    }

    public static ExecutorService getExecutor() {
        return INSTANCE.executorService;
    }
}
