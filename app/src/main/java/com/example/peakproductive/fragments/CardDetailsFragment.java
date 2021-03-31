package com.example.peakproductive.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.peakproductive.R;

import com.example.peakproductive.utils.CardFactory;
import com.example.peakproductive.models.CardModel;


public class CardDetailsFragment extends Fragment {
    private static final String CARD_FRAGMENT_ARG = "com.example.peakproductive.fragments.card_fragment_arg";
    private static int current = 0;
    private EditText title, description, tag;
    private Button savebutton;
    private CardModel cardModel;
    private CardFactory factory;
    View.OnClickListener save = v -> {
        //implementation for the save button of the edit card fragment
        String heading = title.getText().toString();
        String content = description.getText().toString();
        String topic = tag.getText().toString();


        if (!heading.isEmpty() && !content.isEmpty()) {
            if (current == 0) {
                factory.addCard(heading, content, topic);

            } else {
                factory.updateCard(cardModel.getCardId(), heading, content, topic);
            }
            getActivity().finish();


        } else
            Toast.makeText(getActivity(), "Cannot Add Empty Card", Toast.LENGTH_SHORT).show();


    };

    public static CardDetailsFragment getNewInstance(CardModel model) {
        Bundle args = new Bundle();
        current = 1;
        args.putSerializable(CARD_FRAGMENT_ARG, model);

        CardDetailsFragment fragment = new CardDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public static CardDetailsFragment getNewInstance() {
        current = 0;
        CardDetailsFragment cardDetailsFragment = new CardDetailsFragment();
        return cardDetailsFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_details, container, false);
        title = view.findViewById(R.id.edit_cardTitle);
        description = view.findViewById(R.id.edit_cardDescription);
        savebutton = view.findViewById(R.id.btn_done);
        tag = view.findViewById(R.id.edit_cardTag);

        savebutton.setOnClickListener(save);
        factory = new CardFactory(getActivity());


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (current == 1) {
            cardModel = (CardModel) getArguments().getSerializable(CARD_FRAGMENT_ARG);
            title.setText(cardModel.getCardTitle());
            description.setText(cardModel.getCardContent());
            tag.setText(cardModel.getCardTag());
            savebutton.setText(R.string.update_text);
        }
    }

}