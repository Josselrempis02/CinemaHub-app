package com.example.cinemahub;

import java.util.List;

public class Movie {
    private String title;
    private String imageUrl;
    private List<String> genres;
    private String price;
    private String description;

    public Movie(String title, String imageUrl, List<String> genres, String price, String description) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.genres = genres;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
