package com.tmdb.privalia.tmdb.interactor.model;

import java.util.List;

/**
 * Created by fernando on 2/12/17.
 */

public class PageKeywords {
    final int page;
    final List<Keyword> results;
    final int total_pages;
    final int total_results;

    public PageKeywords(int page, List<Keyword> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public List<Keyword> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
