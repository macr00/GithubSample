package com.githubsample.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
@AutoValue
public abstract class UserEntity {

    @SerializedName("avatar_url")
    public abstract String getAvatar();
    @SerializedName("html_url")
    public abstract String getUrl();
    @SerializedName("login")
    public abstract String getName();


    public static TypeAdapter<UserEntity> typeAdapter(Gson gson) {
        return new AutoValue_UserEntity.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder avatar(String avatar);
        public abstract Builder name(String loginName);
        public abstract Builder url(String url);
        public abstract UserEntity build();
    }
}
