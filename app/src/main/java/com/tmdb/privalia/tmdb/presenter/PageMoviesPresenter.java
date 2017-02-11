package com.tmdb.privalia.tmdb.presenter;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;

import com.tmdb.privalia.tmdb.ApplicationTMDB;
import com.tmdb.privalia.tmdb.interactor.ConfigurationInteractor;
import com.tmdb.privalia.tmdb.interactor.PageMovieInteractor;
import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;


/**
 * Created by fernando on 2/11/17.
 */

public class PageMoviesPresenter {
    private IListPopularMovies iListPopularMovies;
    private PageMovieInteractor pageMoviesInteractor;

    private LoadInitData initData;
    private AInteractor.InteractorResponse iResponse;

    public PageMoviesPresenter(IListPopularMovies _iListPopularMovies) {
        this.iListPopularMovies = _iListPopularMovies;
        this.pageMoviesInteractor = new PageMovieInteractor();


        this.iResponse = new AInteractor.InteractorResponse<PageMovies>() {
            @Override
            public void success(PageMovies _pageMovies) {
                updateMoviesList(_pageMovies);
            }

            @Override
            public void failure(String errMsg) {

            }
        };
    }

    public void loadData(){
        initData = new LoadInitData(pageMoviesInteractor, this);
        initData.execute();
    }

    public void updateAdapter(int _page){
        this.pageMoviesInteractor.getMovies(_page, iResponse);
    }

    public void updateMoviesList(PageMovies _pageMovies){
        ((AdapterPopularMovies)iListPopularMovies.getListAdapter()).updateListMovies(_pageMovies.getListMovies());
    }

}
