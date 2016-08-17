package com.githubsample.entity;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
@GsonTypeAdapterFactory
public abstract class GithubTypeAdapterFactory implements TypeAdapterFactory {

    public static GithubTypeAdapterFactory create() {
        return new AutoValueGson_GithubTypeAdapterFactory();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (rawType.equals(UserEntity.class)) {
            return (TypeAdapter<T>) UserEntity.typeAdapter(gson);
        }
        return null;
    }
}
