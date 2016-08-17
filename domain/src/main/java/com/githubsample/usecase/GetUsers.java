package com.githubsample.usecase;

import com.githubsample.model.GetListResponse;
import com.githubsample.model.User;
import com.githubsample.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public class GetUsers extends UseCase {

    public interface CallBack extends BaseCallBack {

        void onUsersRetrieved(List<User> users);

        void setNextPageUrl(String nextPageUrl);
    }

    private final Repository repository;

    @Inject
    public GetUsers(Repository repository) {
        this.repository = repository;
    }

    public void execute(final CallBack callBack) {
        Observable<GetListResponse> observable = repository.getUsers();

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
