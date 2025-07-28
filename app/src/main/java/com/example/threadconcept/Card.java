package com.example.threadconcept;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

public class Card {

    @SerializedName("login")
    private String login;
    private int id;

    public Card(int id, String login) {
        this.id = id;
        this.login = login;
    }


    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }
}
