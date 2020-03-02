package com.example.codingwithmitchmvvmretrofit2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors INSTANCE;

    /**
     * Singleton, insures that this object is only created once.
     * @return returns the INSTANCE.
     */
    public static AppExecutors getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AppExecutors();
        }
        return INSTANCE;
    }

    // An object that  executes submitted Runnable tasks. This interface provides a way of decoupling
    // task submission from the mechanics of how each task will be run, including details of thread use,
    // scheduling.
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
        return networkIO();
    }
}
