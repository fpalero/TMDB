package com.tmdb.privalia.tmdb.presenter;

import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;

/**
 * Created by fernando on 2/11/17.
 */

public class PageMoviesPresenter {
    private IListPopularMovies iListPopularMovies;

    public PageMoviesPresenter(IListPopularMovies _iListPopularMovies) {
        this.iListPopularMovies = _iListPopularMovies;
    }

    public void updateAdapter(){

        ((AdapterPopularMovies)this.iListPopularMovies.getListAdapter()).updateListMovies(null);

    }

}
