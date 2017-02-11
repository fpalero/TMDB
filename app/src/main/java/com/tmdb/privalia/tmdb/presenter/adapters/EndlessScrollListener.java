package com.tmdb.privalia.tmdb.presenter.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by fernando on 2/10/17.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private final int VISIBLE_THRESHOLD = 3;
    private RecyclerView.LayoutManager mLayoutManager;
    private int currentPage = 0;
    private int firstVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;
    private boolean loading = true;

    public EndlessScrollListener() {

    }

    public void setLayoutManager(RecyclerView.LayoutManager _mLayoutManager){
        this.mLayoutManager = _mLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
        totalItemCount = mLayoutManager.getItemCount();
        visibleItemCount = mLayoutManager.getChildCount();
        firstVisibleItem = ((LinearLayoutManager)mLayoutManager).findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            // End has been reac


            loading = true;
            onLoadMore(currentPage, totalItemCount);
        }
    }

    // Defines the process for actually loading more data based on page
    // Returns true if more data is being loaded; returns false if there is no more data to load.
    public abstract void onLoadMore(int page, int totalItemsCount);


}
