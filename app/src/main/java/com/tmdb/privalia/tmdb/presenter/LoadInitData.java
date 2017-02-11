package com.tmdb.privalia.tmdb.presenter;

import android.os.AsyncTask;

import com.tmdb.privalia.tmdb.ApplicationTMDB;
import com.tmdb.privalia.tmdb.interactor.ConfigurationInteractor;
import com.tmdb.privalia.tmdb.interactor.PageMovieInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;

/**
 * Created by fernando on 2/11/17.
 */

public class LoadInitData extends AsyncTask<Void, Void, PageMovies> {
    private PageMovieInteractor pageMoviesInteractor;
    private ConfigurationInteractor configurationInteractor;
    private PageMoviesPresenter pageMoviesPresenter;


    public LoadInitData(PageMovieInteractor _pageMoviesInteractor, PageMoviesPresenter _pageMoviesPresenter) {
        this.pageMoviesInteractor = _pageMoviesInteractor;
        this.configurationInteractor = new ConfigurationInteractor();
        this.pageMoviesPresenter = _pageMoviesPresenter;
    }

    @Override
    protected PageMovies doInBackground(Void... params) {
        ApplicationTMDB.getInstance().setTMDBConfig(configurationInteractor.getConfiguration());
        PageMovies page = pageMoviesInteractor.getMovies(1);

        return page;
    }

    @Override
    protected void onPostExecute(PageMovies result) {
        pageMoviesPresenter.updateMoviesList(result);
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
}

