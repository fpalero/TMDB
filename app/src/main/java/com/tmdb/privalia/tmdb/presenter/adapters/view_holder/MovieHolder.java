package com.tmdb.privalia.tmdb.presenter.adapters.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tmdb.privalia.tmdb.ApplicationTMDB;
import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.interactor.model.Images;
import com.tmdb.privalia.tmdb.interactor.model.Movie;
//import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.presenter.interfaces.AViewHolderFillData;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by fernando on 2/11/17.
 */

public class MovieHolder extends AViewHolderFillData<Movie> {

    public final View mView;
    public final TextView title;
    public final TextView year;
    public final TextView overview;
    public final ImageView logo;
    public final AVLoadingIndicatorView av;

    public MovieHolder(View _itemView) {
        super(_itemView);
        this.mView = _itemView;
        this.title = (TextView) mView.findViewById(R.id.title);
        this.year = (TextView) mView.findViewById(R.id.year);
        this.overview = (TextView) mView.findViewById(R.id.overview);
        this.logo = (ImageView) mView.findViewById(R.id.logo);
        this.av = (AVLoadingIndicatorView) mView.findViewById(R.id.load_animation);
    }

    @Override
    public RecyclerView.ViewHolder fillHolder(Movie _movie) {
        av.show();
        this.title.setText(_movie.getTitle());
        this.year.setText(_movie.getRelease_date());
        this.overview.setText(_movie.getOverview());
        Glide.with(ApplicationTMDB.getInstance().getContext())
                .load(getPicPath(_movie.getPoster_path()))
                .thumbnail(0.5f)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        av.show();
                        logo.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        //av.hide();
                        logo.setVisibility(View.VISIBLE );
                        return false;
                    }
                })
                .into(logo);
        return this;
    }

    private String  getPicPath(String _path){
        Images images = ApplicationTMDB.getInstance().getTMDBConfig().getImages();
        return images.getBase_url() + images.getLogo_sizes().get(images.getLogo_sizes().size()-1) + _path;
    }

}
