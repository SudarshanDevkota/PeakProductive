package com.example.peakproductive.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
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

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.peakproductive.R;

import java.util.Locale;

public class TimerFragment extends Fragment {
    private static final String TAG = "Clock Timer";
    private static final double DEFAULT_PRODUCTIVE_TIME = 3000000.0;
    private TextView countDownText;
    private Button startTimerButton, resetTimerButton;
    private ProgressBar progressBar;
    private Button relaxButton;

    private CountDownTimer productiveCountDownTimer;
    private long timeLeftinMillis = (long) DEFAULT_PRODUCTIVE_TIME; //time left in milliseconds unit. started with default value
    private boolean isTimerActive = false; //check whether the timer is already running
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

    //relax button onclick listener
    View.OnClickListener relax = v -> relaxTime();


    //countdown timer implementation to start a new timer

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
                isTimerActive = false;

                startTimerButton.setEnabled(false);
                resetTimerButton.setEnabled(true);
                progressBar.setProgress(0);
                giveNotification("Great Going. Let's Relax for Sometime !!!");
                relaxTime();

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
    //relax Button Timer 10 min implementation

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

    //update textview field
    private void updateCountDownText() {

        int minutes = (int) (timeLeftinMillis / 1000) / 60;
        int seconds = (int) (timeLeftinMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDownText.setText(timeLeftFormatted);


    }

    private void relaxTime() {
        if (isTimerActive)
            productiveCountDownTimer.cancel();
        startTimerButton.setEnabled(false);
        resetTimerButton.setEnabled(true);
        progressBar.setProgress(100);

        productiveCountDownTimer = new CountDownTimer(6000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                double pogrs = (millisUntilFinished / 6000.0) * 100;

                progressBar.setProgress((int) pogrs);
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftinMillis = (long) DEFAULT_PRODUCTIVE_TIME;
                progressBar.setProgress(0);
                startTimerButton.setEnabled(true);
                updateCountDownText();
                isTimerActive = false;
                giveNotification("Relax Time Over. Lets Get Back to Work !!!");
                startTimer();

            }
        }.start();
        isTimerActive = true;
        relaxButton.setEnabled(false);

    }


    private void giveNotification(String msg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("timer_fragment_notification", "Timer Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri soundUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "timer_fragment_notification");
        builder.setContentTitle("Peak Productive");
        builder.setContentText(msg);
        builder.setSound(soundUri);
        builder.setSmallIcon(R.drawable.ic_baseline_timer_24);
        builder.setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(1, builder.build());


    }


}