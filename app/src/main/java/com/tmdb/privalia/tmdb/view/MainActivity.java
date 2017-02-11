package com.tmdb.privalia.tmdb.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.view.fragment.FragmentPopularMovies;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentPopularMovies fragmentPopularMovies = FragmentPopularMovies.newInstance(1);
        fragmentTransaction.add(R.id.container_main, fragmentPopularMovies);
        fragmentTransaction.commit();
    }
}
