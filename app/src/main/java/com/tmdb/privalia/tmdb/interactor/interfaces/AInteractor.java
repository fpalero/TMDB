package com.tmdb.privalia.tmdb.interactor.interfaces;

import com.tmdb.privalia.tmdb.interactor.model.Movie;
import com.tmdb.privalia.tmdb.interactor.model.PageMovies;
import com.tmdb.privalia.tmdb.interactor.services.ServiceTMDB;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernando on 2/11/17.
 */

public class AInteractor<T> implements Callback<T> {
    protected Call<T> callPage;
    protected InteractorResponse mListener;




    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int code = response.code();
        if (code == 200) {
            T page = response.body();

            if (mListener != null)
                mListener.success(page);
        } else {
            if (mListener != null)
                mListener.failure("Error loading movies");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (mListener != null)
            mListener.failure("Error loading movies");
    }

    public interface InteractorResponse<T> {
        void success(T listMovies);

        void failure(String errMsg);
    }
}
