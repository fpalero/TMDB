package com.tmdb.privalia.tmdb.interactor.services.interfaces;

import com.tmdb.privalia.tmdb.interactor.model.Configuration;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by fernando on 2/9/17.
 */

public interface tmdbAPI {
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("movie/popular")
    Call<PageMovies> getPopularMovies(@Query("page") Integer page);

    @GET("configuration")
    Call<Configuration> getConfiguration();

}
