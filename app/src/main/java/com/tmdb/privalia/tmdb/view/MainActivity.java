package com.tmdb.privalia.tmdb.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.interactor.model.Keyword;
import com.tmdb.privalia.tmdb.presenter.adapters.KeywordsAdapter;
import com.tmdb.privalia.tmdb.presenter.interfaces.ILoadKeywords;
import com.tmdb.privalia.tmdb.view.fragment.FragmentInit;
import com.tmdb.privalia.tmdb.view.fragment.FragmentPopularMovies;
import com.tmdb.privalia.tmdb.view.fragment.FragmentSearchMovies;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentInit.OnFragmentInteractionListener, ILoadKeywords<Keyword>/*, SearchView.OnQueryTextListener */ {
    private FragmentSearchMovies fragmentSearchMovies = null;
    private ImageButton searchButton;
    private LinearLayout searchLayout;
    private Toolbar myToolbar;
    private boolean isKeywordsChecked = false;
    private boolean searchOn = false;
    private String TAG_FRAGMENT_SEARCH = "SearchFragment";
    Spinner spinner;
    CheckBox checkBoxKeyords;
    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(!searchOn) {
                searchOn = true;

                searchLayout.setVisibility(View.VISIBLE);
                editQuery.setFocusable(true);

                fragmentSearchMovies = FragmentSearchMovies.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                fragmentTransaction.replace(R.id.container_main, fragmentSearchMovies, TAG_FRAGMENT_SEARCH);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // keywordsPresenter.updateAdapter(charSequence.toString());
            if (fragmentSearchMovies != null) {
                String query = charSequence.toString();
                if (!isKeywordsChecked)
                    fragmentSearchMovies.makeQuery(query, isKeywordsChecked);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            isKeywordsChecked = b;
            if(isKeywordsChecked)
                spinner.setVisibility(View.VISIBLE);
            else
                spinner.setVisibility(View.GONE);
        }
    };

    AdapterView.OnItemClickListener autoSeletItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (isKeywordsChecked) {
                spinner.setSelection(i);

            }
        }
    };

    AdapterView.OnItemSelectedListener spinnerSelectItem = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            if (isKeywordsChecked) {
                String query = autoCompleteAdapter.getItem(i).getId()+"";
                fragmentSearchMovies.makeQuery(query, isKeywordsChecked);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    AutoCompleteTextView editQuery;
    KeywordsAdapter autoCompleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.tmdb_toolbar);


        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolobar);
        getSupportActionBar().hide();

        searchButton = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.search_button);

        searchButton.setOnClickListener(searchListener);

        autoCompleteAdapter = new KeywordsAdapter(this,
                new ArrayList<Keyword>());


        checkBoxKeyords = (CheckBox) getSupportActionBar().getCustomView().findViewById(R.id.checkbox_keywords);
        checkBoxKeyords.setOnCheckedChangeListener(checkListener);


        searchLayout = (LinearLayout) getSupportActionBar().getCustomView().findViewById(R.id.search_layout);



        editQuery = (AutoCompleteTextView) getSupportActionBar().getCustomView().findViewById(R.id.edittext_query);

        editQuery.setAdapter(autoCompleteAdapter);

        editQuery.addTextChangedListener(textWatcher);
        editQuery.setOnItemClickListener(autoSeletItem);
        autoCompleteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) getSupportActionBar().getCustomView().findViewById(R.id.spinner_keywords);
        spinner.setAdapter(autoCompleteAdapter);

        spinner.setOnItemSelectedListener(spinnerSelectItem);

        boolean showLoading = true;
        if (savedInstanceState != null) {
            showLoading = savedInstanceState.getBoolean(SHOW_LOADING, true);
            searchOn = savedInstanceState.getBoolean(SEARCH_ON, true);

        }

        getSupportActionBar().show();
        if (showLoading) {
            getSupportActionBar().hide();
            changeFrament(FragmentInit.newInstance());
        }

        if(searchOn)
            searchLayout.setVisibility(View.VISIBLE);
        else
            searchLayout.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment f = fragmentManager.findFragmentByTag(TAG_FRAGMENT_SEARCH);
        if(f != null)
            fragmentSearchMovies = (FragmentSearchMovies) f;
    }

    private void changeFrament(Fragment _fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.container_main, _fragment);
        fragmentTransaction.commit();
    }

    private String SHOW_LOADING = "showLoading";
    private String SEARCH_ON = "seaarchOn";
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOW_LOADING, false);
        outState.putBoolean(SEARCH_ON, searchOn);
    }

    @Override
    public void onFragmentInteraction(ArrayList<String> _keywords) {

        changeFrament(FragmentPopularMovies.newInstance());
        getSupportActionBar().show();
    }

    @Override
    public ArrayAdapter<Keyword> getAdapter() {
        return autoCompleteAdapter;
    }


    @Override
    public void onBackPressed() {

        searchLayout.setVisibility(View.INVISIBLE);
        editQuery.setText("");
        searchOn = false;
        super.onBackPressed();

    }
}
