package com.iamtejasmehta.productivitytimer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();


    private Button startButton;
    private Button pauseButton;
    private Button skipButton;

    private TextView timerValue;
    private TextView stageNumber;
    private CountDownTimer tim;
    private long startTime = 0L;

    private int counter = 0;
    private long millis = 0;
    private boolean playpause = false;


    public static long grind1millis = 1200000;
    public static long break1millis = 300000;
    public static long grind2millis = 1200000;
    public static long break2millis = 600000;

    public static String grind1string = "20:00";
    public static String break1string = "05:00";
    public static String grind2string = "20:00";
    public static String break2string = "10:00";

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
       /* Intent intent = getIntent();
        long settings[] = intent.getLongArrayExtra(SecondActivity.EXTRA_MESSAGE);
        grind1millis = settings[0];
        break1millis = settings[0];
        grind2millis = settings[0];
        break2millis = settings[0];*/
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        timerValue = (TextView) findViewById(R.id.timerValue);
        stageNumber = (TextView) findViewById(R.id.stageNumber);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        skipButton = (Button) findViewById(R.id.skip);
        skipButton.setEnabled(false);
        timerValue.setText(grind1string);
        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
//                startTime = SystemClock.uptimeMillis();
//                customHandler.postDelayed(updateTimerThread, 0);

                startButton.setEnabled(false);
                skipButton.setEnabled(true);
               if (counter == 0) {
                    millis = grind1millis;
               }
               else if (counter == 1) {
                    millis = break1millis;
               }
               else if (counter == 2) {
                   millis = grind2millis;
               }
               else if (counter == 3) {
                   millis = break2millis;
               }

                   tim = new CountDownTimer(millis, 1000) {

                       public void onTick(long millisUntilFinished) {
                           long minutesTen = ((millisUntilFinished / 1000) / 60) / 10;
                           long mintuesOne = ((millisUntilFinished / 1000) / 60) % 10;
                           long secondsTen = ((millisUntilFinished / 1000) % 60) / 10;
                           long secondsOne = ((millisUntilFinished / 1000) % 60) % 10;
                           millis = millisUntilFinished;
                           timerValue.setText(minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);

                       }

                       public void onFinish() {
                           startButton.setEnabled(true);
                           skipButton.setEnabled(false);

// Vibrate for 400 milliseconds
                           Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                           final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                           r.play();
                           ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(5000,10));
                           new Handler().postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   r.stop();
                               }
                           }, 5000);

                           if (counter ==0){
                               timerValue.setText(break1string);
                                stageNumber.setText("Chill");
                           }
                           else if(counter == 1){
                               timerValue.setText(grind2string);
                               stageNumber.setText("Sprint #2");
                           }
                           else if(counter == 2){
                               timerValue.setText(break2string);
                               stageNumber.setText("Relax");
                           }
                           else if(counter == 3){
                               timerValue.setText(grind1string);
                               stageNumber.setText("Sprint #1");
                           }

                       }
                   }.start();

                counter = (counter+1)%4;
               }



        });



        pauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(!playpause) {
                     ((Button) findViewById(R.id.pauseButton)).setText("play");
                    long minutesTen = ((millis / 1000) / 60) / 10;
                    long mintuesOne = ((millis / 1000) / 60) % 10;
                    long secondsTen = ((millis / 1000) % 60) / 10;
                    long secondsOne = ((millis / 1000) % 60) % 10;
                    timerValue.setText(minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);
                    tim.cancel();
                    playpause = true;
                }
                else if (playpause){
                    tim = new CountDownTimer(millis, 1000) {
                        public void onTick(long millisUntilFinished) {
                            ((Button) findViewById(R.id.pauseButton)).setText("pause");
                            playpause = false;
                            long minutesTen = ((millisUntilFinished / 1000) / 60) / 10;
                            long mintuesOne = ((millisUntilFinished / 1000) / 60) % 10;
                            long secondsTen = ((millisUntilFinished / 1000) % 60) / 10;
                            long secondsOne = ((millisUntilFinished / 1000) % 60) % 10;
                            millis = millisUntilFinished;
                            timerValue.setText(minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);
                        }

                        public void onFinish() {
                            startButton.setEnabled(true);
                            skipButton.setEnabled(false);
                            if (counter ==0){
                                timerValue.setText(break1string);
                                stageNumber.setText("Chill");
                            }
                            else if(counter == 1){
                                timerValue.setText(grind2string);
                                stageNumber.setText("Sprint #2");
                            }
                            else if(counter == 2){
                                timerValue.setText(break2string);
                                stageNumber.setText("Relax");
                            }
                            else if(counter == 3){
                                timerValue.setText(grind1string);
                                stageNumber.setText("Sprint #1");
                            }

                            counter = (counter+1)%4;

                        }
                    }.start();
                }

            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                skipButton.setEnabled(false);
                tim.cancel();
                startButton.setEnabled(true);
//                counter = (counter+1)%3;
                if (counter == 1){
                    timerValue.setText(break1string);
                    stageNumber.setText("Chill");
                }
                else if(counter == 2){
                    timerValue.setText(grind2string);
                    stageNumber.setText("Sprint #2");
                }
                else if(counter == 3){
                    timerValue.setText(break2string);
                    stageNumber.setText("Relax");
                }
                else if(counter == 0){
                    timerValue.setText(grind1string);
                    stageNumber.setText("Sprint #1");
                }
                playpause = false;
                ((Button) findViewById(R.id.pauseButton)).setText("pause");
            }
        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }

    };

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}