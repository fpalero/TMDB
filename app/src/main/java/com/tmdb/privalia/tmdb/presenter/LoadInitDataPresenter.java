package com.tmdb.privalia.tmdb.presenter;

import android.os.AsyncTask;

import com.tmdb.privalia.tmdb.ApplicationTMDB;
import com.tmdb.privalia.tmdb.interactor.ConfigurationInteractor;
import com.tmdb.privalia.tmdb.interactor.PageMovieInteractor;
import com.tmdb.privalia.tmdb.interactor.interfaces.AInteractor;
import com.tmdb.privalia.tmdb.interactor.model.Configuration;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.presenter.interfaces.ILoadConfiguration;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;

/**
 * Created by fernando on 2/11/17.
 */

public class LoadInitDataPresenter {
    private ConfigurationInteractor configurationInteractor;
    private ILoadConfiguration iLoadConfiguration;
    private AInteractor.InteractorResponse iResponse;

    public LoadInitDataPresenter(ILoadConfiguration _iLoadConfiguration) {
        this.configurationInteractor = new ConfigurationInteractor();
        this.iLoadConfiguration = _iLoadConfiguration;

        this.iResponse = new AInteractor.InteractorResponse<Configuration>() {
            @Override
            public void success(Configuration _configuration) {

                    ApplicationTMDB.getInstance().setTMDBConfig(_configuration);
                    iLoadConfiguration.endLoadConfiguration();

            }

            @Override
            public void failure(String errMsg) {

            }
        };
    }

    public void loadConfiguration(){
        this.configurationInteractor.getConfiguration( iResponse);
    }


}

