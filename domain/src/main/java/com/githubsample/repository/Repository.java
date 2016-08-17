package com.githubsample.repository;

import com.githubsample.model.GetListResponse;

import rx.Observable;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public interface Repository {

    Observable<GetListResponse> getUsers();

    Observable<GetListResponse> getNextPage(String nextPageUrl);
}
