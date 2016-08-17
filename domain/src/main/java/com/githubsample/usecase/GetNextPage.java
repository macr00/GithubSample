package com.githubsample.usecase;

import com.githubsample.model.GetListResponse;
import com.githubsample.model.User;
import com.githubsample.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Rory_McCormack on 17/08/2016.
 */
public class GetNextPage extends UseCase {

    private final Repository repository;

    @Inject
    public GetNextPage(Repository repository) {
        this.repository = repository;
    }

    public void execute(final GetUsers.CallBack callBack, String pageUrl) {
        Observable<GetListResponse> observable = repository.getNextPage(pageUrl);

        subscribe(observable, new DefaultSubscriber<GetListResponse>(callBack) {
            @Override
            public void onNext(GetListResponse getListResponse) {
                String nextPage = getListResponse.getNextUrl();
                List<User> users = getListResponse.getUsers();
                callBack.setNextPageUrl(nextPage);
                callBack.onUsersRetrieved(users);


            }
        });
    }
}
