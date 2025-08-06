package com.example.threadconcept.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.threadconcept.R;
import com.example.threadconcept.model.Card;

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
        ImageButton btnShowMore;
        ImageButton btnShowLess;
        LinearLayout expandableLayout;

        ImageView img_profile;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            expandableLayout = itemView.findViewById(R.id.layout_extended);
            btnShowMore = itemView.findViewById(R.id.btn_ShowMore);
            btnShowLess = itemView.findViewById(R.id.btn_ShowLess);
            img_profile = itemView.findViewById(R.id.img_profile);
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
        Glide.with(holder.itemView.getContext())
                .load(card.getProfile())
                .into(holder.img_profile);
        boolean isExpanded = card.isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.btnShowMore.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        holder.btnShowLess.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(v -> {
            card.setExpanded(!card.isExpanded());
            notifyItemChanged(position);
        });
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