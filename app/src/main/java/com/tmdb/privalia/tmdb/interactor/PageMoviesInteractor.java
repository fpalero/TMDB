package com.tmdb.privalia.tmdb.interactor;

import com.tmdb.privalia.tmdb.interactor.model.MoviePage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernando on 2/11/17.
 */

public class PageMoviesInteractor implements Callback<MoviePage> {

    @Override
    public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {

    }

    @Override
    public void onFailure(Call<MoviePage> call, Throwable t) {

    }
}
