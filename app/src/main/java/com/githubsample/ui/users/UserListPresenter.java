package com.githubsample.ui.users;

import android.support.annotation.NonNull;

import com.githubsample.model.User;
import com.githubsample.ui.base.BaseView;
import com.githubsample.ui.base.Presenter;
import com.githubsample.usecase.GetNextPage;
import com.githubsample.usecase.GetUsers;
import com.githubsample.viewmodel.UserViewModel;
import com.githubsample.viewmodel.transformer.UserViewModelTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
public class UserListPresenter extends Presenter<UserListPresenter.View> implements GetUsers.CallBack{

    public interface View extends BaseView {

        void renderUserList(@NonNull List<UserViewModel> userList);

        void goToUserDetails(String url);

        void getNextPage();
    }

    private final GetUsers getUsers;
    private final GetNextPage getNextPage;
    private String nextPageUrl;
    private boolean isFetching;

    @Inject UserViewModelTransformer transformer;

    @Inject
    public UserListPresenter(GetUsers getUsers, GetNextPage getNextPage) {
        super(getUsers, getNextPage);
        this.getUsers = getUsers;
        this.getNextPage = getNextPage;
    }

    public void makeGetUsersRequest() {
        if (!isFetching) {
            getUsers.execute(this);
            isFetching = true;
        }
    }

    public void makeGetNextPageRequest() {
        if (!isFetching && nextPageUrl != null && !nextPageUrl.isEmpty()) {
            getNextPage.execute(this, nextPageUrl);
            isFetching = true;
        }
    }

    @Override
    public void onUsersRetrieved(List<User> users) {
        if (getView() != null && users != null) {
            List<UserViewModel> userList = transformer.transform(users);
            getView().renderUserList(userList);
        }

        isFetching = false;
    }

    @Override
    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    @Override
    public void onServerError() {
        super.onServerError();
        isFetching = false;
    }

    @Override
    public void onGenericError() {
        super.onGenericError();
        isFetching = false;
    }
}
