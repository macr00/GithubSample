package com.githubsample;

import android.content.Context;

import com.githubsample.thread.PostExecutionThread;
import com.githubsample.thread.SubscribeOnThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
@Module
public class ApplicationModule {

    private final GitHubSampleApp application;

    public ApplicationModule(GitHubSampleApp application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return application;
    }

    @Singleton
    @Provides
    SubscribeOnThread provideSubscribeOnThread() {
        return new IOThread();
    }

    @Singleton
    @Provides
    PostExecutionThread providePostExecutionThread() {
        return new MainThread();
    }
}
