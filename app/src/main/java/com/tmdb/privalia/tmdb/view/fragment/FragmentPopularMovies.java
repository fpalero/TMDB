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
import com.tmdb.privalia.tmdb.presenter.adapters.AdapterPopularMovies;
import com.tmdb.privalia.tmdb.presenter.adapters.EndlessScrollListener;
import com.tmdb.privalia.tmdb.view.interfaces.InterfaceMovies;

public class FragmentPopularMovies extends Fragment implements InterfaceMovies {

    // TODO: Customize parameter argument names
    private static final String FIRST_VISIBLE_ITEM = "first visible element";
    // TODO: Customize parameters
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

    public static FragmentPopularMovies newInstance() {
        FragmentPopularMovies fragment = new FragmentPopularMovies();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_item_list, container, false);
        if (view instanceof RecyclerView) {

            recyclerView = (RecyclerView) view;
            // Set the adapter
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                    getContext().getResources().getInteger(R.integer.num_columns),
                    StaggeredGridLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(null);

            endlessScrollListener.setLayoutManager(recyclerView.getLayoutManager());
            recyclerView.addOnScrollListener(endlessScrollListener);

            if (savedInstanceState == null) {

                // Initialize the movie adapter
                adapterPopularMovies = new AdapterPopularMovies();
                recyclerView.setAdapter(adapterPopularMovies);


                this.pageMoviesPresenter = new PageMoviesPresenter(this);
                this.pageMoviesPresenter.updateAdapter(1);

            } else {

                recyclerView.setAdapter(adapterPopularMovies);

                adapterPopularMovies.notifyDataSetChanged();
                recyclerView.scrollToPosition(savedInstanceState.getInt(FIRST_VISIBLE_ITEM,0));

            }


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

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FIRST_VISIBLE_ITEM, endlessScrollListener.getFirstVisibleItem());
    }


}
