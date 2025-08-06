package com.example.threadconcept.model;

import com.google.gson.annotations.SerializedName;


// This is the Model Class and in the MVVM architecture pattern it represents the data and business logic of the application.
// It is responsible for managing the data and providing an interface to access and manipulate the data
public class Card {
    @SerializedName("login")
    private String userName;
    @SerializedName("id")
    private int userId;
    @SerializedName("avatar_url")
    private String profile;
    private boolean isExpanded = false;


    public String getProfile() {
        return profile;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public Card(int userId, String userName, String profile) {
        this.userId = userId;
        this.userName = userName;
        this.profile = profile;
    }



    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserId(int id){
        this.userId = id;
    }
}
