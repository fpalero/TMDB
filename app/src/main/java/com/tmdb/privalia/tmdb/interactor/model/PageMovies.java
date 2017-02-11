package com.tmdb.privalia.tmdb.interactor.model;

import java.util.List;

/**
 * Created by fernando on 2/9/17.
 */

public class PageMovies {

    public final int page;
    public final int total_results;
    public final int total_pages;

    private final List<Movie> results;

    public PageMovies(int _page, int _total_results, int _total_pages, List<Movie> _results ){
        this.page = _page;
        this.total_results = _total_results;
        this.total_pages = _total_pages;
        this.results = _results;
    }

    public int getId() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Movie> getListMovies() {
        return results;
    }

}
