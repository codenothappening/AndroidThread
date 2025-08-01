package com.example.threadconcept;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList = new ArrayList<>();
    public void setCards(List<Card> cardList){
        this.cardList.clear();
        this.cardList.addAll(cardList);
        notifyDataSetChanged();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        TextView tv_id;
        TextView tv_name;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.tv_id.setText(String.valueOf(card.getUserId()));
        holder.tv_name.setText(card.getUserName());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void updateData(List<Card> newCards) {
        this.cardList = newCards;
        notifyDataSetChanged();
    }
}