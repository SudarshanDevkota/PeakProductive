package com.example.peakproductive.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.peakproductive.EditCardDetailsActivity;
import com.example.peakproductive.R;
import com.example.peakproductive.adaptors.CardModelAdaptor;

import com.example.peakproductive.models.CardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;



public class CardFragment extends Fragment {

    private static final String TAG = "Card Fragment";
    private static ArrayList<CardModel> cardList;
    private CardModelAdaptor adapter;
    private FloatingActionButton addBtn;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        addBtn = view.findViewById(R.id.btn_addCard);
        addBtn.setOnClickListener(addCard);





        return view;
    }

    View.OnClickListener addCard = v -> {
        Intent intent = new Intent(getActivity(), EditCardDetailsActivity.class);
        startActivity(intent);


    };



    //    private ArrayList<CardModel> generateSimpleList() {
//        cardList = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            cardList.add(new CardModel(i, "Title", String.format("this is the %d view", i), "color"));
//        }
//
//        return cardList;
//    }

}