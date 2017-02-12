package com.tmdb.privalia.tmdb.view.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmdb.privalia.tmdb.R;
import com.tmdb.privalia.tmdb.presenter.LoadInitDataPresenter;
import com.tmdb.privalia.tmdb.presenter.interfaces.ILoadConfiguration;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentInit.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentInit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInit extends Fragment implements ILoadConfiguration{


    private OnFragmentInteractionListener mListener;
    private LoadInitDataPresenter loadInitDataPresenter;
    private AVLoadingIndicatorView avi;
    private LoadAnimation loadAnimation;
    public static FragmentInit newInstance() {
        FragmentInit fragment = new FragmentInit();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v  = inflater.inflate(R.layout.fragment_fragment_init, container, false);
        avi = (AVLoadingIndicatorView) v.findViewById(R.id.load_animation);
        loadAnimation = new LoadAnimation();
        loadInitDataPresenter = new LoadInitDataPresenter(this);

        loadAnimation.execute();


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void endLoadConfiguration(ArrayList<String> _keywords) {
        if(mListener != null)
            mListener.onFragmentInteraction(_keywords);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ArrayList<String> keywords);
    }





    private class LoadAnimation extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            loadInitDataPresenter.loadConfiguration();
        }
    }
}
