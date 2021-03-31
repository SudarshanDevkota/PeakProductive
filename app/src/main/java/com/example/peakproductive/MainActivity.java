package com.example.peakproductive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


import com.example.peakproductive.fragments.CardFragment;
import com.example.peakproductive.fragments.TaskFragment;
import com.example.peakproductive.fragments.TimerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final Fragment taskFragment = new TaskFragment();
    private final Fragment timerFragment = new TimerFragment();
    private final Fragment cardFragment = new CardFragment();
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment active = taskFragment;
    private final BottomNavigationView.OnNavigationItemSelectedListener navigation_listener =
            item -> {

                switch (item.getItemId()) {
                    case R.id.tab_flashcard:
                        fragmentManager.beginTransaction().hide(active).show(cardFragment).commit();
                        active = cardFragment;
                        break;
                    case R.id.tab_tasks:
                        fragmentManager.beginTransaction().hide(active).show(taskFragment).commit();
                        active = taskFragment;
                        break;
                    case R.id.tab_timer:
                        fragmentManager.beginTransaction().hide(active).show(timerFragment).commit();
                        active = timerFragment;

                        break;
                }

                return true;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.tab_tasks);
        fragmentManager.beginTransaction().add(R.id.fragment_view, timerFragment, "3").hide(timerFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_view, cardFragment, "1").hide(cardFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_view, taskFragment, "2").commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(navigation_listener);
    }
}