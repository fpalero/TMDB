package com.tmdb.privalia.tmdb.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.presenter.PageMoviesPresenter;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;
import com.tmdb.privalia.tmdb.presenter.adapters.*;

public class FragmentPopularMovies extends Fragment implements IListPopularMovies {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    //Clases capa Presenter
    private PageMoviesPresenter pageMoviesPresenter;
    private AdapterPopularMovies adapterPopularMovies;
    public EndlessScrollListener endlessScrollListener = new EndlessScrollListener() {
        @Override
        public void onLoadMore(int page, int totalItemsCount) {
            pageMoviesPresenter.updateAdapter(page);
        }
    };

    //Fin Clases capa Presenter


    public static FragmentPopularMovies newInstance() {
        FragmentPopularMovies fragment = new FragmentPopularMovies();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {

            recyclerView = (RecyclerView) view;

           // getContext().getResources().getConfiguration().orientation;
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                    getContext().getResources().getInteger(R.integer.num_columns),
                    StaggeredGridLayoutManager.VERTICAL);

            if (savedInstanceState != null) {

            }

            recyclerView.setLayoutManager(staggeredGridLayoutManager);

            // Initialize the movie adapter
            adapterPopularMovies = new AdapterPopularMovies();
            recyclerView.setAdapter(adapterPopularMovies);
            endlessScrollListener.setLayoutManager(recyclerView.getLayoutManager());
            recyclerView.addOnScrollListener(endlessScrollListener);

            this.pageMoviesPresenter = new PageMoviesPresenter(this);


            this.pageMoviesPresenter.updateAdapter(1);

        }
        
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void setListAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public RecyclerView.Adapter getListAdapter() {
        return this.adapterPopularMovies;
    }



}
