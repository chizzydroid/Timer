package com.chizzy.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    SeekBar seekBar;
    CountDownTimer countDownTimer;
    TextView textView;
    Boolean counterIsActive = false;
    Button goButton;
    Boolean counterIsAtive = false;

    public void resetTimer(){
        textView.setText("0:00");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go!");
        counterIsActive = false;
    }


    public  void buttonClick(View view) {
        if (counterIsActive) {
            resetTimer();
        } else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            goButton.setText("Stop!");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bell);
                    mediaPlayer.start();
                    goButton.setText("Done!");
                }
            }.start();
        }
    }

    public  void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds =secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9){
            secondString = "0" + secondString;
        }

        textView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        goButton = findViewById(R.id.button);
         seekBar.setMax(3600);
         seekBar.setProgress(30);

         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
    }
}