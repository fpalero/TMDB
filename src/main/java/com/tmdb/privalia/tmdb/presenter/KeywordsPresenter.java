package com.tmdb.privalia.tmdb.presenter;

import com.tmdb.privalia.tmdb.interactor.KeywordsInteractor;
import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.Keyword;
import com.tmdb.privalia.tmdb.interactor.model.PageKeywords;
import com.tmdb.privalia.tmdb.presenter.interfaces.ILoadKeywords;


/**
 * Created by fernando on 2/11/17.
 */

public class KeywordsPresenter {
    private ILoadKeywords<Keyword> stringILoadKeywords;
    private KeywordsInteractor keywordsInteractor;

    private AInteractor.InteractorResponse iResponse;


    public KeywordsPresenter(ILoadKeywords<Keyword> _stringILoadKeywords) {
        this.stringILoadKeywords = _stringILoadKeywords;
        this.keywordsInteractor = new KeywordsInteractor();


        this.iResponse = new AInteractor.InteractorResponse<PageKeywords>() {
            @Override
            public void success(PageKeywords _pageKeywords) {

                updateKeywordsList(_pageKeywords);
            }

            @Override
            public void failure(String errMsg) {

            }
        };
    }

    public void updateAdapter( String _query) {
            this.keywordsInteractor.cancelSearch();
            this.keywordsInteractor.getKeyworks( _query, iResponse);
    }


    private void updateKeywordsList(PageKeywords _pageKeywords) {
        stringILoadKeywords.getAdapter().clear();
        stringILoadKeywords.getAdapter().addAll(_pageKeywords.getResults());
        stringILoadKeywords.getAdapter().notifyDataSetChanged();
    }


}
