package com.app.eularmotor.scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler {

    private TimerTask timerTask;
    private Timer timer;

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

    public void cancel(){
        timer.cancel();
    }
}
