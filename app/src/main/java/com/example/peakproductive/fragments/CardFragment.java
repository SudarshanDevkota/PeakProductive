package com.example.peakproductive.fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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
        recyclerView_card = view.findViewById(R.id.task_list);
        cardFactory = new CardFactory(getActivity());
        recyclerView_card.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView_card);

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

        adapter = new CardModelAdaptor(getActivity(), cardList, this);
        recyclerView_card.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        //implementation pending for the recycler view items


    }

    //touch swipe
    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int pos= viewHolder.getAdapterPosition();
            switch (direction) {

                case ItemTouchHelper.LEFT:

                    int id = cardList.get(pos).getCardId();
                    cardFactory.deleteCard(id);
                    cardList.remove(pos);
                    adapter.notifyItemRemoved(pos);
                    break;
                case ItemTouchHelper.RIGHT:
                    Intent intent = EditDetails.EditDetailIntent(getActivity(), cardList.get(pos));
                    startActivity(intent);
                    break;

            }
        }
    ///setting the background color and icon for swipe gestures in the recycler view

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            Drawable icon;
            ColorDrawable background;

            View itemView = viewHolder.itemView;
            int  backgroundCornerOffset = 20;

            if(dX > 0){
                icon = ContextCompat.getDrawable(adapter.getContext(),R.drawable.ic_edit);
                background = new ColorDrawable(ContextCompat.getColor(adapter.getContext(),R.color.green_dark));
            }
            else{
                icon = ContextCompat.getDrawable(adapter.getContext(),R.drawable.ic_delete);
                background = new ColorDrawable(ContextCompat.getColor(adapter.getContext(),R.color.red_dark));

            }
            int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight() ) /2;
            int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight())/2;
            int iconBottom = iconTop + icon.getIntrinsicHeight();

            if(dX > 0 ){ //swipping to the right

                int iconLeft = itemView.getLeft() + iconMargin;
                int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
                icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);

                background.setBounds(itemView.getLeft(),itemView.getTop(),itemView.getLeft()+((int) dX)+backgroundCornerOffset,itemView.getBottom());

            }
            else if(dX < 0){//swipping to the left
                int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
                int iconRight = itemView.getRight() - iconMargin;
                icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);

                background.setBounds(itemView.getRight() + ((int)dX - backgroundCornerOffset),itemView.getTop(),itemView.getRight(),itemView.getBottom());



            }else{
                background.setBounds(0,0,0,0);
            }
            background.draw(c);
            icon.draw(c);



        }
    };
}