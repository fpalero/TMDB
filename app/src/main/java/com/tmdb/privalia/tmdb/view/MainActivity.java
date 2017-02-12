package com.tmdb.privalia.tmdb.view;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.presenter.LoadInitDataPresenter;
import com.tmdb.privalia.tmdb.presenter.adapters.KeywordAdapter;
import com.tmdb.privalia.tmdb.view.fragment.FragmentPopularMovies;
import com.tmdb.privalia.tmdb.view.fragment.FragmentInit;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentInit.OnFragmentInteractionListener, SearchView.OnQueryTextListener {
    private boolean expandable = true;
    private SearchView searchView;
    private LoadInitDataPresenter loadInitDataPresenter;
    private AppBarLayout appBarLayout;
    private FloatingActionButton fabSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_layout);

        fabSearch = ( FloatingActionButton)findViewById(R.id.fab_search);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        appBarLayout.setVisibility(View.GONE);
        fabSearch.setVisibility(View.GONE);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tmdb_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().collapseActionView();



        boolean showLoading = true;
        if (savedInstanceState != null) {
            showLoading = savedInstanceState.getBoolean(SHOW_LOADING, true);

        }

        if (showLoading) {
            changeFrament(FragmentInit.newInstance());
        } else {
            loadInitDataPresenter = new LoadInitDataPresenter(null);
            appBarLayout.setVisibility(View.VISIBLE);
            fabSearch.setVisibility(View.VISIBLE);
            getSupportActionBar().show();

        }

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appBarLayout.setExpanded(expandable, true);
                expandable=!expandable;
            }
        });

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
    public void onFragmentInteraction(ArrayList<String> _keywords) {
        ArrayAdapter<String> dataAdapter = new KeywordAdapter(this,
                _keywords);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_keyword_item);
        appBarLayout.setVisibility(View.VISIBLE);
        fabSearch.setVisibility(View.VISIBLE);
        changeFrament(FragmentPopularMovies.newInstance());
    }


    /////////////////////////////
    // SEARCHABLE CODE

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.context_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );


        searchView.setQueryHint("Search Movie");
        searchView.setMaxWidth((int) getApplicationContext().getResources().getDimension(R.dimen.search_view_width));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
