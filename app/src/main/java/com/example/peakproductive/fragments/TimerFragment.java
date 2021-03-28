package com.example.peakproductive.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.peakproductive.R;

import java.util.Locale;

public class TimerFragment extends Fragment {
    private TextView countDownText;
    private Button startTimerButton, resetTimerButton;
    private ProgressBar progressBar;
    private static final String TAG = "Clock Timer";
    private static final double DEFAULT_PRODUCTIVE_TIME = 3000000.0;
    private Button relaxButton;

    private CountDownTimer productiveCountDownTimer;
    private long timeLeftinMillis = (long) DEFAULT_PRODUCTIVE_TIME; //time left in milliseconds unit. started with default value
    private boolean isTimerActive = false; //check whether the timer is already running





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ##Called##");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        countDownText = view.findViewById(R.id.time_left);
        startTimerButton = view.findViewById(R.id.btn_start);
        resetTimerButton = view.findViewById(R.id.btn_reset);
        progressBar = view.findViewById(R.id.progressBar);
//        progressBar.setMax(100);

        relaxButton = view.findViewById(R.id.btn_relax);
        updateCountDownText();
        startTimerButton.setOnClickListener(start);
        resetTimerButton.setOnClickListener(reset);
        relaxButton.setOnClickListener(relax);


        return view;
    }

    //Start pause button on click listener
    View.OnClickListener start = v -> {
        Log.d(TAG, "onClick: button clicked");

        if (isTimerActive) {
            pauseTimer();

        } else {

            startTimer();

        }
    };

    //reset button on click listener
    View.OnClickListener reset = v -> resetTimer();



    //countdown timer implementation to start a new timer

    private void startTimer() {

        productiveCountDownTimer = new CountDownTimer(timeLeftinMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                double pogrs = (timeLeftinMillis / DEFAULT_PRODUCTIVE_TIME) * 100;
                progressBar.setProgress((int) pogrs);
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(), "time finished", Toast.LENGTH_SHORT).show();
                isTimerActive = false;

                startTimerButton.setEnabled(false);
                resetTimerButton.setEnabled(true);
                progressBar.setProgress(0);

            }
        };

        productiveCountDownTimer.start();
        isTimerActive = true;
        startTimerButton.setText(R.string.pause_text);
        resetTimerButton.setEnabled(false);


    }

    //pause Timer implementation
    private void pauseTimer() {
        productiveCountDownTimer.cancel();
        isTimerActive = false;
        startTimerButton.setText(R.string.start_text);
        resetTimerButton.setEnabled(true);
        updateCountDownText();

    }

    //reset timer implementation
    private void resetTimer() {
        if (productiveCountDownTimer != null) {
            productiveCountDownTimer.cancel();

        }
        isTimerActive = false;
        timeLeftinMillis = (long) DEFAULT_PRODUCTIVE_TIME;
        updateCountDownText();
        resetTimerButton.setEnabled(false);
        startTimerButton.setEnabled(true);
        startTimerButton.setText(R.string.start_text);
        relaxButton.setEnabled(true);

        updateCountDownText();


    }
    //relax Button Timer 10 min implementation

    View.OnClickListener relax = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isTimerActive)
                productiveCountDownTimer.cancel();
            startTimerButton.setEnabled(false);
            resetTimerButton.setEnabled(true);
            progressBar.setProgress(100);

            productiveCountDownTimer = new CountDownTimer(600000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d(TAG, "onTick: Called : time left"+timeLeftinMillis);
                    timeLeftinMillis = millisUntilFinished;
                    double pogrs = (millisUntilFinished / 600000.0) * 100;

                    progressBar.setProgress((int) pogrs);
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    Toast.makeText(getContext(), "Relaxation Time Finished", Toast.LENGTH_SHORT).show();
                    timeLeftinMillis = (long) DEFAULT_PRODUCTIVE_TIME;
                    progressBar.setProgress(0);
                    startTimerButton.setEnabled(true);
                    updateCountDownText();
                    isTimerActive = false;

                }
            }.start();
            isTimerActive = true;
            relaxButton.setEnabled(false);


        }
    };

    //update textview field
    private void updateCountDownText() {
        Log.d(TAG, "updateCountDownText: ##Called##");
        int minutes = (int) (timeLeftinMillis / 1000) / 60;
        int seconds = (int) (timeLeftinMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDownText.setText(timeLeftFormatted);


    }



}