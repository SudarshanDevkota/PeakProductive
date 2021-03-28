package com.example.peakproductive.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.peakproductive.R;
import com.example.peakproductive.database.CardDatabaseHelper;
import com.example.peakproductive.models.CardFactory;
import com.example.peakproductive.models.CardModel;



public class CardDetailsFragment extends Fragment {
    private static final String CARD_ID_ARGS = "com.example.peakproductive.fragments.arg_crime_id";

    private EditText title, description,tag;
    private Button savebutton;
    private CardModel cardModel;


    //implementation pending
    public static CardDetailsFragment getNewInstance() {
        Bundle args = new Bundle();

        CardDetailsFragment fragment = new CardDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UUID cardId = (UUID)getArguments().getSerializable(CARD_ID_ARGS);
//        cardModel= CardFragment.getCard(cardId);


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

//       title.setText(cardModel.getCardTitle());
//       description.setText(cardModel.getCardContent());


        return view;
    }
    View.OnClickListener save = v -> {
        //implementation for the save button of the edit card fragment

        CardFactory factory = new CardFactory(getActivity());
        String heading = title.getText().toString();
        String content = description.getText().toString();
        String topic = tag.getText().toString();
        if (!heading.isEmpty() && !content.isEmpty()) {
            factory.addCard(heading, content, topic);
            Toast.makeText(getActivity(), "Additoon Successful", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        } else
            Toast.makeText(getActivity(), "Cannot Add Empty Card", Toast.LENGTH_SHORT).show();



    };
}