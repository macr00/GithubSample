package com.githubsample;

import com.githubsample.thread.SubscribeOnThread;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public class IOThread implements SubscribeOnThread {

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
