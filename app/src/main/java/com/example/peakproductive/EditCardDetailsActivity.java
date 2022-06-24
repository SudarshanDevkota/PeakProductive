package com.example.peakproductive;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.peakproductive.models.CardModel;
import com.example.peakproductive.repo.MainRepository;


public class EditCardDetailsActivity extends AppCompatActivity {
        EditText titleET,descriptionET,tagET;
        Button button;
        boolean isUpdate=false;
        CardModel cardModel;
        MainRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card_details);
        titleET = findViewById(R.id.edit_cardTitle);
        descriptionET = findViewById(R.id.edit_cardDescription);
        tagET = findViewById(R.id.edit_cardTag);
        button = findViewById(R.id.btn_done);
        button.setOnClickListener(addUpdateListener);
        repository = new MainRepository(this);


    }

    View.OnClickListener addUpdateListener = v->{
        String title = titleET.getText().toString();
        String content = descriptionET.getText().toString();
        String tag = tagET.getText().toString();

        if(!title.isEmpty() && !content.isEmpty()){
            if(cardModel == null){
                cardModel = new CardModel();
            }
            cardModel.setTitle(title);
            cardModel.setContent(content);
            cardModel.setCardTag(tag);
            if(isUpdate){
                repository.updateFlashCard(cardModel);
            }else{
                repository.addFlashCard(cardModel);
            }
            finish();


        }else{
            Toast.makeText(this, "Empty Title or Description", Toast.LENGTH_SHORT).show();
        }
    };
}