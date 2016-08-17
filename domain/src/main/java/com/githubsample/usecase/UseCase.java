package com.githubsample.usecase;

import com.githubsample.thread.PostExecutionThread;
import com.githubsample.thread.SubscribeOnThread;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public abstract class UseCase {

    public interface BaseCallBack {

        void onServerError();

        void onGenericError();
    }

    private PostExecutionThread postExecutionThread;
    private SubscribeOnThread subscribeOnThread;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public void setPostExecutionThread(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
    }

    @Inject
    public void setSubscribeOnThread(SubscribeOnThread subscribeOnThread) {
        this.subscribeOnThread = subscribeOnThread;
    }

    protected <T> void subscribe(Observable<T> observable, DefaultSubscriber<T> subscriber) {
        subscriptions.add(observable
                .subscribeOn(subscribeOnThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber));
    }

    public void unsubscribeAll() {
        subscriptions.unsubscribe();
    }

}
