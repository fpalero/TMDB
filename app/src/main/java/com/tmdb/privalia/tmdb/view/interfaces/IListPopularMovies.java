package com.tmdb.privalia.tmdb.view.interfaces;

import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

/**
 * Created by fernando on 2/10/17.
 */

public interface IListPopularMovies {

    void setListAdapter(RecyclerView.Adapter adapter);
    RecyclerView.Adapter getListAdapter();
}
