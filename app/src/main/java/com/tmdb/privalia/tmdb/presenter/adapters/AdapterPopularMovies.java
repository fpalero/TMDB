package com.tmdb.privalia.tmdb.presenter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.interactor.model.Movie;
import com.tmdb.privalia.tmdb.presenter.adapters.view_holder.MovieHolder;
import com.tmdb.privalia.tmdb.view.fragment.FragmentPopularMovies.OnListFragmentInteractionListener;
import com.tmdb.privalia.tmdb.view.fragment.dummy.DummyContent.DummyItem;

import java.util.List;

public class AdapterPopularMovies extends RecyclerView.Adapter<MovieHolder> {

    private final List<Movie> mValues;
    private final OnListFragmentInteractionListener mListener;

    public AdapterPopularMovies(List<Movie> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        holder.fillHolder(mValues.get(position));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public void updateListMovies(List<Movie> _mValues) {
        int curSize = this.getItemCount();

        mValues.addAll(_mValues);
        notifyItemRangeInserted(curSize, mValues.size() - 1);
    }
}
