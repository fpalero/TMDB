package com.tmdb.privalia.tmdb.presenter.interfaces;

import android.widget.ArrayAdapter;

/**
 * Created by fernando on 2/12/17.
 */

public interface ILoadKeywords<T> {

    ArrayAdapter<T> getAdapter();
}
