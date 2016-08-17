package com.githubsample;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public class GitHubSampleApp extends Application {

    private ApplicationComponent component;

    public static GitHubSampleApp get(Context context) {
        return (GitHubSampleApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
