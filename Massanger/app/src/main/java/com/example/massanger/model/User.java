package com.example.massanger.model;

public class User {
    private String id;
    private String username;
    private String imageUrl;
    private String status;
    private String search;

    public User() {
    }

    public User(String id, String username, String imageUrl, String status,String search) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
        this.status = status;
        this.search = search;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setMagUrl(String magUrl) {
        this.imageUrl = magUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
