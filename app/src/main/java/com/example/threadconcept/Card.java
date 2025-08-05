package com.example.threadconcept;

import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("login")
    private String userName;
    @SerializedName("id")
    private int userId;
    @SerializedName("avatar_url")
    private String profile;


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
