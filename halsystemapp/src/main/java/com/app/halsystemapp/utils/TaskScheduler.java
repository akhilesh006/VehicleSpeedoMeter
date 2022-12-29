package com.app.halsystemapp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A custom schedule class to schedule any task.
 */
public class TaskScheduler {

    private ScheduledExecutorService executerService;
    private Thread backgroundThread;

    /**
     * schedule the job in every given time(in milliseconds).
     *
     * @param task             Job to schedule.
     * @param timeInMillSecond repeat time.
     */
    public void scheduleEvery(Runnable task, long timeInMillSecond) {
        if (executerService == null)
            executerService = Executors.newSingleThreadScheduledExecutor();
        if (backgroundThread == null || !backgroundThread.isAlive()) {
            backgroundThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    task.run();
                    if (executerService != null) {
                        executerService.schedule(this, timeInMillSecond, TimeUnit.MILLISECONDS);
                    }
                }
            });
            backgroundThread.start();
        }
    }

    /**
     * Cancel the scheduled job
     */
    public void cancel() {
        if (executerService != null) {
            executerService.shutdownNow();
            executerService = null;
        }
        if (backgroundThread != null && backgroundThread.isAlive()) {
            backgroundThread.interrupt();
            backgroundThread = null;
        }
    }
}