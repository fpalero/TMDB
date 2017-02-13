package com.tmdb.privalia.tmdb.presenter.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;

/**
 * Created by fernando on 2/11/17.
 */

public abstract class AViewHolderFillData<T> extends RecyclerView.ViewHolder{

    public AViewHolderFillData(View itemView) {
        super(itemView);
    }

    public abstract RecyclerView.ViewHolder fillHolder(T _values);

}
