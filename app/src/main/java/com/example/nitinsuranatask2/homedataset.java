package com.example.nitinsuranatask2;

public class homedataset {
    private String title,description,imageurl;

    public homedataset(String title, String description, String imageurl) {
        this.title = title;
        this.description = description;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
