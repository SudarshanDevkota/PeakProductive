package com.example.peakproductive.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.peakproductive.EditDetails;
import com.example.peakproductive.R;
import com.example.peakproductive.adaptors.CardModelAdaptor;
import com.example.peakproductive.adaptors.ItemClickInterface;
import com.example.peakproductive.database.CardDatabaseHelper;
import com.example.peakproductive.models.CardFactory;
import com.example.peakproductive.models.CardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;



public class CardFragment extends Fragment  implements ItemClickInterface {

    private static final String TAG = "Card Fragment";
    private static ArrayList<CardModel> cardList;

    private RecyclerView recyclerView_card;
    private CardModelAdaptor adapter;
    private FloatingActionButton addbtn;
    private CardFactory cardFactory;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        addbtn = view.findViewById(R.id.btn_addCard);
        addbtn.setOnClickListener(addCard);
        recyclerView_card = view.findViewById(R.id.card_list);
        cardFactory = new CardFactory(getActivity());
        recyclerView_card.setLayoutManager(new LinearLayoutManager(getActivity()));




        return view;
    }

    View.OnClickListener addCard = v -> {
        Intent intent = EditDetails.EditDetailIntent(getActivity());
        startActivity(intent);


    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: *****************Called***********");
        cardList = cardFactory.getCardList();

        adapter = new CardModelAdaptor(getActivity(),cardList,this);
        recyclerView_card.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        //implementation pending for the recycler view items
        Intent intent = EditDetails.EditDetailIntent(getActivity(),cardList.get(position));
        startActivity(intent);

    }

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