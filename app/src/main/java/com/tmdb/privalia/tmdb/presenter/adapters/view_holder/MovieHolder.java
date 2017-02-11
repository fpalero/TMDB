package com.tmdb.privalia.tmdb.presenter.adapters.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tmdb.privalia.tmdb.ApplicationTMDB;
import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.interactor.model.Movie;
//import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.presenter.interfaces.AViewHolderFillData;

/**
 * Created by fernando on 2/11/17.
 */

public class MovieHolder extends AViewHolderFillData<Movie> {

    public final View mView;
    public final TextView title;
    public final TextView year;
    public final TextView overview;
    public final ImageView poster;

    public MovieHolder(View _itemView) {
        super(_itemView);
        this.mView = _itemView;
        this.title = (TextView) mView.findViewById(R.id.title);
        this.year = (TextView) mView.findViewById(R.id.year);
        this.overview = (TextView) mView.findViewById(R.id.overview);
        this.poster = (ImageView) mView.findViewById(R.id.poster);
    }

    @Override
    public RecyclerView.ViewHolder fillHolder(Movie _movie) {

        this.title.setText(_movie.getTitle());
        this.year.setText(_movie.getRelease_date());
        this.overview.setText(_movie.getOverview());
        Glide.with(ApplicationTMDB.getInstance().getContext())
                .load(_movie.getPoster_path())
                .into(poster);
        return this;
    }

}
