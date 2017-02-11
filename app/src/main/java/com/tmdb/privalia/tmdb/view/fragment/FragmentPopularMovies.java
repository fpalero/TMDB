package com.tmdb.privalia.tmdb.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.presenter.PageMoviesPresenter;
import com.tmdb.privalia.tmdb.view.fragment.dummy.DummyContent;
import com.tmdb.privalia.tmdb.view.fragment.dummy.DummyContent.DummyItem;
import com.tmdb.privalia.tmdb.view.interfaces.IListPopularMovies;
import com.tmdb.privalia.tmdb.presenter.adapters.*;
/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interfaces.
 */
public class FragmentPopularMovies extends Fragment implements IListPopularMovies{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    //Clases capa Presenter
    private PageMoviesPresenter pageMoviesPresenter;
    private AdapterPopularMovies adapterPopularMovies;
    public EndlessScrollListener endlessScrollListener = new EndlessScrollListener() {
        @Override
        public void onLoadMore(int page, int totalItemsCount) {
            pageMoviesPresenter.updateAdapter();
        }
    };

    //Fin Clases capa Presenter

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentPopularMovies() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FragmentPopularMovies newInstance(int columnCount) {
        FragmentPopularMovies fragment = new FragmentPopularMovies();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            // Initialize the movie adapter
            adapterPopularMovies = new AdapterPopularMovies(DummyContent.ITEMS, mListener);
            recyclerView.setAdapter(adapterPopularMovies);
            endlessScrollListener.setLayoutManager(recyclerView.getLayoutManager());
            recyclerView.addOnScrollListener(endlessScrollListener);
        }

        // This snippet of code links thi class with the presentar layer,
        // which updates the list of movies
        this.pageMoviesPresenter = new PageMoviesPresenter(this);
        this.pageMoviesPresenter.updateAdapter();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }/* else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setListAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public RecyclerView.Adapter getListAdapter() {
        return this.adapterPopularMovies;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
