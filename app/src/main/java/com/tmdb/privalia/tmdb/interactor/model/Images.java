package com.tmdb.privalia.tmdb.interactor.model;

import java.util.ArrayList;

/**
 * Created by fernando on 2/11/17.
 */

public class Images {
    final String base_url;
    final String secure_base_url;
    final ArrayList<String> backdrop_sizes;
    final ArrayList<String> logo_sizes;
    final ArrayList<String> poster_sizes;
    final ArrayList<String> profile_sizes;
    final ArrayList<String> still_sizes;

    public Images(String base_url, String secure_base_url, ArrayList<String> backdrop_sizes, ArrayList<String> logo_sizes, ArrayList<String> poster_sizes, ArrayList<String> profile_sizes, ArrayList<String> still_sizes) {
        this.base_url = base_url;
        this.secure_base_url = secure_base_url;
        this.backdrop_sizes = backdrop_sizes;
        this.logo_sizes = logo_sizes;
        this.poster_sizes = poster_sizes;
        this.profile_sizes = profile_sizes;
        this.still_sizes = still_sizes;
    }

    public String getBase_url() {
        return base_url;
    }

    public String getSecure_base_url() {
        return secure_base_url;
    }

    public ArrayList<String> getBackdrop_sizes() {
        return backdrop_sizes;
    }

    public ArrayList<String> getLogo_sizes() {
        return logo_sizes;
    }

    public ArrayList<String> getPoster_sizes() {
        return poster_sizes;
    }

    public ArrayList<String> getProfile_sizes() {
        return profile_sizes;
    }

    public ArrayList<String> getStill_sizes() {
        return still_sizes;
    }
}
