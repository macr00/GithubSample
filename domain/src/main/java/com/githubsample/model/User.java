package com.githubsample.model;

import com.google.auto.value.AutoValue;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
@AutoValue
public abstract class User {

    public abstract String getAvatar();
    public abstract String getLogin();
    public abstract String getUrl();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder avatar(String avatar);
        public abstract Builder login(String login);
        public abstract Builder url(String url);
        public abstract User build();
    }
}
