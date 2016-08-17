package com.githubsample.ui.base;

import android.support.annotation.Nullable;

import com.githubsample.R;
import com.githubsample.usecase.UseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public abstract class Presenter<V extends BaseView> implements UseCase.BaseCallBack {

    @Nullable private V view;

    private List<UseCase> useCaseList;

    public Presenter(UseCase... useCases) {
        createUseCaseList(useCases);
    }

    private void createUseCaseList(UseCase... useCases) {
        if (useCases == null) {
            throw new NullPointerException("UseCases must not be null");
        }

        useCaseList = new ArrayList<>(useCases.length);
        Collections.addAll(useCaseList, useCases);
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
        for (UseCase useCase : useCaseList) {
            useCase.unsubscribeAll();
        }
    }

    @Nullable
    public V getView() {
        return view;
    }

    @Override
    public void onServerError() {
        if (view != null) {
            view.displayError(R.string.server_error_msg);
        }
    }

    @Override
    public void onGenericError() {
        if (view != null) {
            view.displayError(R.string.generic_error_msg);
        }
    }
}
