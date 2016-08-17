package com.githubsample;


import com.githubsample.ui.users.UserListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(UserListActivity activity);
}
