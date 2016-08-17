package com.githubsample.ui.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubsample.R;
import com.githubsample.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final List<UserViewModel> userList;
    private final UserListPresenter.View userListView;
    private final Context context;

    public UserListAdapter(UserListPresenter.View userListView, Context context) {
        this.userListView = userListView;
        this.userList = new ArrayList<>();
        this.context = context;
    }

    public void addItems(@NonNull List<UserViewModel> users) {
        int start = userList.size();
        userList.addAll(users);
        notifyItemRangeInserted(start, users.size());
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view, userListView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserViewModel userViewModel = userList.get(position);
        holder.bind(userViewModel);
        holder.loginName.setText(userViewModel.getLogin());
        Picasso.with(context)
                .load(userViewModel.getAvatar())
                .placeholder(R.drawable.github_icon)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private UserViewModel userViewModel;
        private UserListPresenter.View userListView;

        @Bind(R.id.user_avatar) ImageView avatar;
        @Bind(R.id.user_login) TextView loginName;

        public UserViewHolder(View itemView, UserListPresenter.View userListView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.userListView = userListView;
        }

        public void bind(UserViewModel userViewModel) {
            this.userViewModel = userViewModel;
        }

        @Override
        public void onClick(View view) {
            userListView.goToUserDetails(userViewModel.getUrl());
        }
    }

}
