package com.example.peakproductive.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.peakproductive.EditCardDetailsActivity;
import com.example.peakproductive.R;
import com.example.peakproductive.adaptors.CardModelAdaptor;
import com.example.peakproductive.models.CardModel;
import com.example.peakproductive.repo.MainRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class CardFragment extends Fragment {

    private static ArrayList<CardModel> cardList;
    private CardModelAdaptor adapter;
    ViewPager2 viewPager;
    MainRepository mainRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        FloatingActionButton addBtn = view.findViewById(R.id.btn_addCard);
        addBtn.setOnClickListener(addCard);
        viewPager = view.findViewById(R.id.viewpager);
        cardList = new ArrayList<>();
        adapter = new CardModelAdaptor(getContext(), cardList);
        viewPager.setAdapter(adapter);
        mainRepository = new MainRepository(getActivity());
        getAllCards();

        return view;
    }

    View.OnClickListener addCard = v -> {
        Intent intent = new Intent(getActivity(), EditCardDetailsActivity.class);
        startActivity(intent);


    };
    private void getAllCards(){
        mainRepository.getAllFlashCards().observe(getActivity(), cardModels -> {
            cardList = (ArrayList<CardModel>) cardModels;
            adapter = new CardModelAdaptor(getActivity(), cardList);
            viewPager.setAdapter(adapter);
            adapter.notifyItemInserted(cardList.size()-1);
        });
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