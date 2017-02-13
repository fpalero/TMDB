package com.tmdb.privalia.tmdb.interactor.model;

/**
 * Created by fernando on 2/9/17.
 */

public class Movie {
    private final boolean adult;

    private final String backdrop_path;

    private final int id;

    private final String media_type;

    private final String original_language;

    private final String original_title;

    private final String overview;

    private final float popularity;

    private final String poster_path;

    private final String release_date;

    private final String title;

    private final boolean video;

    private final float vota_average;

    private final int vote_count;

    public Movie(boolean adult, String backdrop_path, int id, String media_type, String original_language, String original_title, String overview, float popularity, String poster_path, String release_date, String title, boolean video, float vota_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.media_type = media_type;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vota_average = vota_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int getId() {
        return id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVota_average() {
        return vota_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}
