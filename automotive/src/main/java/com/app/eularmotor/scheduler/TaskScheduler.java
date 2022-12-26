package com.app.eularmotor.scheduler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A custom schedule class to schedule any task.
 */
public class TaskScheduler {

    private TimerTask timerTask;
    private Timer timer;

    /**
     * schedule the job in every given time(in milliseconds).
     * @param task Job to schedule.
     * @param time repeat time.
     */
    public void scheduleEvery(Runnable task, long time){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, time);
    }

    /**
     * Cancel the scheduled job
     */
    public void cancel(){
        timer.cancel();
    }
}
