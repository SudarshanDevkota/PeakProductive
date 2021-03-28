package com.example.peakproductive;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.peakproductive.fragments.CardDetailsFragment;




public class EditDetails extends AppCompatActivity {


    private static final String CARD_ID = "com.example.peakproductive.card_id";
    public static int current=0;
    Fragment fragment;




    public static Intent EditDetailIntent(Context context) {
        Intent intent = new Intent(context, EditDetails.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        fragment = new CardDetailsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.card_detail_container,fragment).commit();
//        UUID id=(UUID)getIntent().getSerializableExtra(CARD_ID);
//        fragment = CardDetailsFragment.getNewInstance(id);
//        getSupportFragmentManager().beginTransaction().add(R.id.card_detail_container,fragment).commit();




    }
}