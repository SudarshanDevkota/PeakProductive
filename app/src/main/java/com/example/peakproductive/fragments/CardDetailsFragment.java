package com.example.peakproductive.fragments;

import android.graphics.ColorSpace;
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
import com.example.peakproductive.database.CardDatabaseHelper;
import com.example.peakproductive.models.CardFactory;
import com.example.peakproductive.models.CardModel;



public class CardDetailsFragment extends Fragment {
    private static final String FRAGMENT_ARG= "com.example.peakproductive.fragments.fragment_arg";

    private EditText title, description,tag;
    private Button savebutton;
    private static int current=0;
    private CardModel cardModel;


    //implementation pending
    public static CardDetailsFragment getNewInstance(CardModel model) {
        Bundle args = new Bundle();
        current = 1;
        args.putSerializable(FRAGMENT_ARG,model);

        CardDetailsFragment fragment = new CardDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }
    public static CardDetailsFragment getNewInstance() {
        current =0;
        CardDetailsFragment cardDetailsFragment = new CardDetailsFragment();
        return  cardDetailsFragment;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(current ==1){
            CardModel model = (CardModel) getArguments().getSerializable(FRAGMENT_ARG);
            title.setText(model.getCardTitle());
            description.setText(model.getCardContent());
            tag.setText(model.getCardTag());
        }
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