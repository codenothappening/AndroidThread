package com.example.threadconcept.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.threadconcept.model.Card;

public class CardViewModel extends BaseObservable {
    int userId;
    String userName;
    Card card = new Card(0, null,"");
    @Bindable
    public String getUserName(){
        return card.getUserName();
    }
    @Bindable
    public int getUserId(){
        return card.getUserId();
    }

    public void setUserName(String userName){
        card.setUserName(userName);
    }

    public void setUserId(int userId){
        card.setUserId(userId);
    }
}
