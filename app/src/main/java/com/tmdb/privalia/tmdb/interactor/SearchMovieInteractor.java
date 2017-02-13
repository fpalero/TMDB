package com.tmdb.privalia.tmdb.interactor;

import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.interactor.services.ServiceTMDB;


/**
 * Created by fernando on 2/11/17.
 */

public class SearchMovieInteractor extends AInteractor<PageMovies> {

    public void getSearch(int _page, String _query, InteractorResponse _mListener) {
        this.mListener = _mListener;
        callPage = ServiceTMDB.getInstace().searchMovies(_page, _query);
        callPage.enqueue(this);
    }

    public void getSearchKeywords(int _page, int _query, InteractorResponse _mListener) {
        this.mListener = _mListener;
        callPage = ServiceTMDB.getInstace().searchMoviesKeywords(_query, _page);
        callPage.enqueue(this);
    }

    public void cancelSearch() {
        if (callPage != null && callPage.isExecuted()) {
            callPage.cancel();
            callPage = callPage.clone();
        }
    }
}
