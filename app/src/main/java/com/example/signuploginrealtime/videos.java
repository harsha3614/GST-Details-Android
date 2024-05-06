package com.example.signuploginrealtime;

public class videos {

    private String title;
    private String youtubeLink;
    private String imageUrl;


    // Default constructor required for Firebase
    public videos() {
    }

    public videos(String title, String youtubeLink,  String imageUrl) {
        this.title = title;
        this.youtubeLink = youtubeLink;

        this.imageUrl = imageUrl;

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getyoutubeLink() {
        return youtubeLink;
    }

    public void setyoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
