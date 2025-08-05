package com.example.threadconcept;

import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("login")
    private String userName;
    @SerializedName("id")
    private int userId;


    public Card(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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
