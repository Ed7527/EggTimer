package com.edwardtorpy.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean isCountingDown;
    SeekBar seekBar;
    int secondsLeft;
    CountDownTimer countDownTimer;


    public void convertSecondsTimeDisplay(){
        String clock ="";

        TextView clockDisplay = (TextView) findViewById(R.id.timeView);
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        if (secondsLeft == 0){
            clock = "DONE!";
            countDownTimer.cancel();
        } else if (seconds < 10){

            clock = minutes + " : 0" + seconds;

            } else {

                clock = minutes + " : " + seconds;
            }


        clockDisplay.setText(clock);
    }

    public void startTimer(View view) {

        Button startButton = (Button) findViewById(R.id.startButton);

        if (isCountingDown) {

            isCountingDown = false;
            secondsLeft = 600;
            startButton.setText("Start");
            countDownTimer.cancel();

        } else {

            isCountingDown = true;

            startButton.setText("Reset");

            countDown();

        }

    }

    public void countDown () {

        countDownTimer = new CountDownTimer(secondsLeft * 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsLeft--;
                seekBar.setProgress(secondsLeft);
                convertSecondsTimeDisplay();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isCountingDown =false;

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(600);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                secondsLeft = progress;
                convertSecondsTimeDisplay();
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
