package com.tmdb.privalia.tmdb.interactor.model;

/**
 * Created by fernando on 2/12/17.
 */

public class Keyword {
    final int id;
    final String name;

    public Keyword(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
