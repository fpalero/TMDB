package com.tmdb.privalia.tmdb.interactor;

import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.Configuration;
import com.tmdb.privalia.tmdb.interactor.services.ServiceTMDB;

import java.io.IOException;

/**
 * Created by fernando on 2/11/17.
 */

public class ConfigurationInteractor extends AInteractor<Configuration>{
    public void getConfiguration(InteractorResponse _mListener) {
        this.mListener = _mListener;
        callPage = ServiceTMDB.getInstace().getConfiguration();
        callPage.enqueue(this);
    }

}
