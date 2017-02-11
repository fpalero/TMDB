package com.tmdb.privalia.tmdb.presenter;

import com.tmdb.privalia.tmdb.interactor.PageMoviesInteractor;
import com.tmdb.privalia.tmdb.interactor.model.Movie;
import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;

import java.util.List;

/**
 * Created by fernando on 2/11/17.
 */

public class PageMoviesPresenter {
    private IListPopularMovies iListPopularMovies;
    private PageMoviesInteractor pageMoviesInteractor;

    private PageMoviesInteractor.InteractorResponse iResponse;

    public PageMoviesPresenter(IListPopularMovies _iListPopularMovies) {
        this.iListPopularMovies = _iListPopularMovies;
        this.pageMoviesInteractor = new PageMoviesInteractor();

        this.iResponse = new PageMoviesInteractor.InteractorResponse() {
            @Override
            public void success(List<Movie> listMovies) {
                ((AdapterPopularMovies)iListPopularMovies.getListAdapter()).updateListMovies(listMovies);
            }

            @Override
            public void failure(String errMsg) {

            }
        };
    }

    public void updateAdapter(int _page){
        this.pageMoviesInteractor.getMovies(_page, iResponse);


    }

}
