package com.example.peakproductive.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peakproductive.R;
import com.example.peakproductive.models.CardModel;

import java.util.ArrayList;

public class CardModelAdaptor extends RecyclerView.Adapter<CardModelAdaptor.ViewHolder> {
    private static ArrayList<CardModel> cardList;
    private Context context;


    public CardModelAdaptor(Context context, ArrayList<CardModel> cardList) {
        this.cardList = cardList;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(cardList.get(position).getCardTitle());
        holder.descriptionView.setText(cardList.get(position).getCardContent());
        holder.tag.setText(cardList.get(position).getCardTag());
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, descriptionView, tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.card_title);
            descriptionView = itemView.findViewById(R.id.card_description);
            tag = itemView.findViewById(R.id.card_tag);


        }


    }


}
