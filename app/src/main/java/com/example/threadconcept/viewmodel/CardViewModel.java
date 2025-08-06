package com.example.threadconcept.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.threadconcept.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardViewModel extends ViewModel {
    private MutableLiveData <List<Card>> cardList = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<Card>> getCard(){
        return cardList;
    }
    public void setCardList(List<Card> cards){
        cardList.setValue(cards);
    }
}
