package com.githubsample.thread;

import rx.Scheduler;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public interface PostExecutionThread {

    Scheduler getScheduler();
}
