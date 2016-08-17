package com.githubsample.api;

import com.githubsample.entity.UserEntity;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public interface GithubApi {

    @GET("/users")
    Observable<Response<List<UserEntity>>> getUsers();

    @GET
    Observable<Response<List<UserEntity>>> getNextPage(@Url String url);

}
