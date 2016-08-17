package com.githubsample.repository;

import com.githubsample.api.GithubApi;
import com.githubsample.api.LinkHeaderParser;
import com.githubsample.entity.UserEntity;
import com.githubsample.entity.transformer.UserEntityTransformer;
import com.githubsample.model.GetListResponse;
import com.githubsample.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

public class DataRepository implements Repository {

    private final GithubApi api;
    private final UserEntityTransformer transformer;

    private Func1<Response<List<UserEntity>>, GetListResponse> mapResponse =
           new Func1<Response<List<UserEntity>>, GetListResponse>() {
               @Override
               public GetListResponse call(Response<List<UserEntity>> listResponse) {
                   String linkHeaderString = LinkHeaderParser.getNexPage(listResponse);
                   List<UserEntity> userEntities = listResponse.body();
                   List<User> users = transformer.transform(userEntities);
                   return GetListResponse.create(linkHeaderString, users);
               }
           };

    @Inject
    public DataRepository(Retrofit retrofit, UserEntityTransformer transformer) {
        this.api = retrofit.create(GithubApi.class);
        this.transformer = transformer;
    }

    @Override
    public Observable<GetListResponse> getUsers() {
        return api.getUsers()
                .map(mapResponse);
    }

    @Override
    public Observable<GetListResponse> getNextPage(String nextPageUrl) {
        return api.getNextPage(nextPageUrl)
                .map(mapResponse);
    }
}
