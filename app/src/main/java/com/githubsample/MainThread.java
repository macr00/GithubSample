package com.githubsample;

import com.githubsample.thread.PostExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public class MainThread implements PostExecutionThread {

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
