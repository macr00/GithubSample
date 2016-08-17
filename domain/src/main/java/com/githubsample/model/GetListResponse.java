package com.githubsample.model;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
@AutoValue
public abstract class GetListResponse {

    public abstract String getNextUrl();
    public abstract List<User> getUsers();

    public static GetListResponse create(String url, List<User> users) {
        if (users == null) {
            throw new NullPointerException();
        }

        return new AutoValue_GetListResponse(url, users);
    }
}
