package com.githubsample.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public interface BaseView {

    void displayError(@StringRes int messageId);
}
