package com.example.peakproductive;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.peakproductive.fragments.CardDetailsFragment;
import com.example.peakproductive.models.CardModel;


public class EditDetails extends AppCompatActivity {


    private static final String ACTIVITY_ARG = "com.example.peakproductive.activity_arg";
    private  static int current=0;
    Fragment fragment;




    public static Intent EditDetailIntent(Context context) {
        Intent intent = new Intent(context, EditDetails.class);
        current =0;
        return intent;
    }
    public static Intent EditDetailIntent(Context context, CardModel model) {
        Intent intent = new Intent(context, EditDetails.class);
        intent.putExtra(ACTIVITY_ARG,model);
        current =1;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        if(current==0){

            fragment = CardDetailsFragment.getNewInstance();
        }
        else{
            CardModel c = (CardModel) getIntent().getSerializableExtra(ACTIVITY_ARG);
            fragment = CardDetailsFragment.getNewInstance(c);
        }


        getSupportFragmentManager().beginTransaction().add(R.id.card_detail_container,fragment).commit();




    }
}