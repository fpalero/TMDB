package com.tmdb.privalia.tmdb.interactor;

import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.interactor.services.ServiceTMDB;


/**
 * Created by fernando on 2/11/17.
 */

public class PageMovieInteractor extends AInteractor<PageMovies> {

    public void getMovies(int _page, InteractorResponse _mListener) {
        this.mListener = _mListener;
        callPage = ServiceTMDB.getInstace().getPopularMovies(_page);
        callPage.enqueue(this);
    }
}
