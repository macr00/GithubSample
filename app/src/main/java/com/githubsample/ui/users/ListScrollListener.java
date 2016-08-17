package com.githubsample.ui.users;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
public class ListScrollListener extends RecyclerView.OnScrollListener {

    private static final int RELOAD_COUNT = 5;
    private UserListPresenter.View view;
    private LinearLayoutManager layoutManager;

    public ListScrollListener(UserListPresenter.View view, LinearLayoutManager layoutManager) {
        super();
        this.view = view;
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalItems = layoutManager.getItemCount();
        int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
        if ((totalItems - lastVisible) < RELOAD_COUNT) {
            view.getNextPage();
        }
    }
}
