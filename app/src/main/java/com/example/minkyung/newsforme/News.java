package com.example.minkyung.newsforme;

/**
 * Created by minkyung on 2017-05-27.
 */

public class News {
    private String title;
    private String description;
    private String url;

    News(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    String getTitle() {
        return this.title;
    }

    String getDescription() {
        return this.description;
    }

    String getUrl() {
        return this.url;
    }
}
