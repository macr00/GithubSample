package com.githubsample.usecase;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    private final UseCase.BaseCallBack callBack;

    public DefaultSubscriber(UseCase.BaseCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Throwable cause = e.getCause();

        if (cause != null && cause instanceof HttpException) {
            int code = ((HttpException) cause).code();
            if (code >= 500) {
                callBack.onServerError();
                return;
            }
        }

        callBack.onGenericError();
    }

    @Override
    public void onNext(T t) {

    }
}
