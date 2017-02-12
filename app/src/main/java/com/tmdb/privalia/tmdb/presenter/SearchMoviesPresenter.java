package com.tmdb.privalia.tmdb.presenter;

import com.tmdb.privalia.tmdb.interactor.SearchMovieInteractor;
import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;


/**
 * Created by fernando on 2/11/17.
 */

public class SearchMoviesPresenter {
    private IListPopularMovies iListPopularMovies;
    private SearchMovieInteractor searchMoviesInteractor;

    private AInteractor.InteractorResponse iResponse;
    private AInteractor.InteractorResponse iResponseNewSearch;
    private int total_page = 1;

    public SearchMoviesPresenter(IListPopularMovies _iListPopularMovies) {
        this.iListPopularMovies = _iListPopularMovies;
        this.searchMoviesInteractor = new SearchMovieInteractor();


        this.iResponse = new AInteractor.InteractorResponse<PageMovies>() {
            @Override
            public void success(PageMovies _pageMovies) {
                total_page = _pageMovies.total_pages;
                updateMoviesList(_pageMovies);
            }

            @Override
            public void failure(String errMsg) {

            }
        };

        this.iResponseNewSearch = new AInteractor.InteractorResponse<PageMovies>() {
            @Override
            public void success(PageMovies _pageMovies) {
                total_page = _pageMovies.total_pages;
                resetMoviesList(_pageMovies);
            }

            @Override
            public void failure(String errMsg) {

            }
        };
    }

    public void updateAdapter(int _page, String _query) {
        if (_page <= total_page) {
            this.searchMoviesInteractor.cancelSearch();
            this.searchMoviesInteractor.getSearch(_page, _query, iResponse);
        }
    }

    public void resetAdapter(int _page, String _query) {
        total_page = 1;
        this.searchMoviesInteractor.cancelSearch();
        this.searchMoviesInteractor.getSearch(_page, _query, iResponseNewSearch);

    }

    private void updateMoviesList(PageMovies _pageMovies) {
        ((AdapterPopularMovies) iListPopularMovies.getListAdapter()).updateListMovies(_pageMovies.getListMovies());
    }

    private void resetMoviesList(PageMovies _pageMovies) {
        ((AdapterPopularMovies) iListPopularMovies.getListAdapter()).resetListMovies(_pageMovies.getListMovies());
    }


}
