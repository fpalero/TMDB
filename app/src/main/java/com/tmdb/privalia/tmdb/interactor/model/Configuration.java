package com.tmdb.privalia.tmdb.interactor.model;

import java.util.ArrayList;

/**
 * Created by fernando on 2/11/17.
 */

public class Configuration {
    Images images;
    ArrayList<String> change_keys;

    public Configuration(Images images, ArrayList<String> change_keys) {
        this.images = images;
        this.change_keys = change_keys;
    }

    public Images getImages() {
        return images;
    }

    public ArrayList<String> getChange_keys() {
        return change_keys;
    }
}
