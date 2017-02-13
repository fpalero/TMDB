package com.tmdb.privalia.tmdb;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by fernando on 2/11/17.
 */

public class ApplicationTMDB extends Application {

    private static ApplicationTMDB singleton;
    private static Context context;
    private static com.tmdb.privalia.tmdb.interactor.model.Configuration configuration;
    public static ApplicationTMDB getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        ApplicationTMDB.context = getApplicationContext();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public Context getContext(){
        return context;
    }

    public void setTMDBConfig(com.tmdb.privalia.tmdb.interactor.model.Configuration _confiuration){
        configuration = _confiuration;
    }

    public com.tmdb.privalia.tmdb.interactor.model.Configuration getTMDBConfig(){
        return configuration;
    }
}
