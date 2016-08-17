package com.githubsample;

import com.githubsample.entity.GithubTypeAdapterFactory;
import com.githubsample.entity.transformer.UserEntityTransformer;
import com.githubsample.repository.DataRepository;
import com.githubsample.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
@Module
public class DataModule {

    private static final String ENDPOINT = "https://api.github.com/";

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(GithubTypeAdapterFactory.create())
                .create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    Repository provideRepository(Retrofit retrofit) {
        return new DataRepository(retrofit, new UserEntityTransformer());
    }
}
