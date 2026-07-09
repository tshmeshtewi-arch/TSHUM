package com.nuxflix.app;

public class Movie {
    String title;
    String description;
    String year;
    String duration;
    String rating;
    String videoUrl;
    String posterUrl;
    int posterColor;

    public Movie(String title, String description, String year, String duration, String rating, String videoUrl, String posterUrl, int posterColor) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.duration = duration;
        this.rating = rating;
        this.videoUrl = videoUrl;
        this.posterUrl = posterUrl;
        this.posterColor = posterColor;
    }
}
