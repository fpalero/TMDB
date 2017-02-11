package com.tmdb.privalia.tmdb.interactor.services;

import com.tmdb.privalia.tmdb.BuildConfig;
import com.tmdb.privalia.tmdb.interactor.services.interfaces.tmdbAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fernando on 2/9/17.
 */

public class ServiceTMDB {
    private static Retrofit retrofit;
    private static tmdbAPI api;

     public static tmdbAPI getInstace(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

            api = retrofit.create(tmdbAPI.class);
        }

        return api;
    }
}
