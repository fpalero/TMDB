package com.tmdb.privalia.tmdb.interactor;

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

public class PageMoviesInteractor implements Callback<PageMovies> {
    Call<PageMovies> callPage;
    InteractorResponse mListener;

    public void getMovies(int _page, InteractorResponse _mListener) {
        this.mListener = _mListener;
        callPage = ServiceTMDB.getInstace().getPopularMovies(_page);
        callPage.enqueue(this);

    }

    @Override
    public void onResponse(Call<PageMovies> call, Response<PageMovies> response) {
        int code = response.code();
        if (code == 200) {
            PageMovies page = response.body();

            if (mListener != null)
                mListener.success(page.getListMovies());
        } else {
            if (mListener != null)
                mListener.failure("Error loading movies");
        }
    }

    @Override
    public void onFailure(Call<PageMovies> call, Throwable t) {
        if (mListener != null)
            mListener.failure("Error loading movies");
    }

    public interface InteractorResponse {
        void success(List<Movie> listMovies);

        void failure(String errMsg);
    }
}
