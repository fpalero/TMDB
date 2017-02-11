package com.tmdb.privalia.tmdb.interactor.services;

import com.tmdb.privalia.tmdb.BuildConfig;
import com.tmdb.privalia.tmdb.interactor.services.interfaces.tmdbAPI;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fernando on 2/9/17.
 */

public class ServiceTMDB {
    private static Retrofit retrofit;
    private static tmdbAPI api;

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();


     public static tmdbAPI getInstace(){
        if(retrofit == null) {

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.TMDB_KEY)
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

            api = retrofit.create(tmdbAPI.class);


        }

        return api;
    }
}
