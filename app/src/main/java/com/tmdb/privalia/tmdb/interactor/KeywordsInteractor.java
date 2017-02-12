package com.tmdb.privalia.tmdb.interactor;

import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.PageKeywords;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.interactor.services.ServiceTMDB;

import java.io.IOException;


/**
 * Created by fernando on 2/11/17.
 */

public class KeywordsInteractor extends AInteractor<PageKeywords> {

    public void getKeyworks(String _query, InteractorResponse _mListener) {
        this.mListener = _mListener;
        callPage = ServiceTMDB.getInstace().getKeywords(_query);
        callPage.enqueue(this);
    }

    public PageKeywords getKeyworks(String _query) {

        callPage = ServiceTMDB.getInstace().getKeywords(_query);
        try {
            return callPage.execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public void cancelSearch() {
        if (callPage != null && callPage.isExecuted())
            callPage.cancel();
    }
}
