package com.tmdb.privalia.tmdb.view;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.view.fragment.FragmentPopularMovies;
import com.tmdb.privalia.tmdb.view.fragment.FragmentInit;

public class MainActivity extends AppCompatActivity implements FragmentInit.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tmdb_toolbar);
        setSupportActionBar(myToolbar);

        boolean showLoading = true;
        if (savedInstanceState != null)
            showLoading = savedInstanceState.getBoolean(SHOW_LOADING, true);

        if (showLoading)
            changeFrament(FragmentInit.newInstance());
    }

    private void changeFrament(Fragment _fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.container_main, _fragment);
        fragmentTransaction.commit();
    }

    private String SHOW_LOADING = "showLoading";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOW_LOADING, false);
    }

    @Override
    public void onFragmentInteraction() {
        changeFrament(FragmentPopularMovies.newInstance());
    }
}
