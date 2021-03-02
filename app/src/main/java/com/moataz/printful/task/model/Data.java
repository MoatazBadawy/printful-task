package com.moataz.printful.task.model;

public class Data {
    private String imageURL;
    private String title;
    private String description;
    private String infoURL;

    public Data() {
    }
    public Data (String imageURL, String title, String discretion, String infoURL) {
        this.imageURL = imageURL;
        this.title = title;
        this.description = discretion;
        this.infoURL = infoURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public String getInfoURL() {
        return infoURL;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }
}
