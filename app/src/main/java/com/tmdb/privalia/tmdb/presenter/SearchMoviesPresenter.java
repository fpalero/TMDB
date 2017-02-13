package com.tmdb.privalia.tmdb.presenter;

import com.tmdb.privalia.tmdb.interactor.SearchMovieInteractor;
import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.view.interfaces.InterfaceMovies;


/**
 * Created by fernando on 2/11/17.
 */

public class SearchMoviesPresenter {
    private InterfaceMovies iSearchovies;
    private SearchMovieInteractor searchMoviesInteractor;
    private String query;
    private int id_keyword;
    private AInteractor.InteractorResponse iResponse;
    private AInteractor.InteractorResponse iResponseNewSearch;
    private int total_page = 1;
    boolean useKeywords = false;

    public SearchMoviesPresenter(InterfaceMovies _iListPopularMovies) {
        this.iSearchovies = _iListPopularMovies;
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

    public void updateAdapter(int _page, boolean _useKeywords) {
        this.useKeywords = _useKeywords;
        if (!_useKeywords)
            updateAdapter(_page);
        else
            updateAdapterKeywords(_page);
    }

    public void resetAdapter(int _page, String _query, boolean _useKeywords) {
        this.useKeywords = _useKeywords;
        if (!_useKeywords)
            resetAdapter(_page, _query);
        else
            resetAdapterKeywords(_page, _query);

    }

    public void updateAdapter(int _page) {
        if (_page <= total_page) {
            this.searchMoviesInteractor.getSearch(_page, this.query, iResponse);
        }
    }

    public void resetAdapter(int _page, String _query) {
        total_page = 1;
        this.query = _query;
        this.searchMoviesInteractor.cancelSearch();
        this.searchMoviesInteractor.getSearch(_page, query, iResponseNewSearch);

    }

    public void updateAdapterKeywords(int _page) {
        if (_page <= total_page) {
            this.searchMoviesInteractor.getSearchKeywords(_page, id_keyword, iResponse);
        }
    }

    public void resetAdapterKeywords(int _page, String _query) {
        total_page = 1;
        this.id_keyword = Integer.parseInt(_query);
        this.searchMoviesInteractor.cancelSearch();
        this.searchMoviesInteractor.getSearchKeywords(_page, id_keyword, iResponseNewSearch);

    }

    private void updateMoviesList(PageMovies _pageMovies) {
        ((AdapterPopularMovies) iSearchovies.getListAdapter()).updateListMovies(_pageMovies.getListMovies());
    }

    private void resetMoviesList(PageMovies _pageMovies) {
        ((AdapterPopularMovies) iSearchovies.getListAdapter()).resetListMovies(_pageMovies.getListMovies());
        iSearchovies.getRecyclerView().smoothScrollToPosition(0);
    }


}
