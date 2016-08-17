package com.githubsample.ui.users;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.githubsample.ApplicationComponent;
import com.githubsample.R;
import com.githubsample.ui.base.BaseActivity;
import com.githubsample.viewmodel.UserViewModel;

import java.util.List;

import butterknife.Bind;

public class UserListActivity extends BaseActivity<UserListPresenter> implements UserListPresenter.View {

    private UserListAdapter adapter;

    @Bind(R.id.list_message) TextView message;
    @Bind(R.id.users_rv) RecyclerView recyclerView;

    @Override
    protected void injectDependencies(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new UserListAdapter(this, getApplicationContext());
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ListScrollListener scrollListener = new ListScrollListener(this, llm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().makeGetUsersRequest();
    }

    @Override
    public void renderUserList(@NonNull List<UserViewModel> userList) {
        message.setVisibility(View.GONE);
        adapter.addItems(userList);
    }

    @Override
    public void getNextPage() {
        getPresenter().makeGetNextPageRequest();
    }

    @Override
    public void goToUserDetails(String url) {
        if (url == null || url.isEmpty()) {
            displayError(R.string.generic_error_msg);
            return;
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void displayError(@StringRes int messageId) {
        message.setVisibility(View.VISIBLE);
        message.setText(messageId);
    }

}
