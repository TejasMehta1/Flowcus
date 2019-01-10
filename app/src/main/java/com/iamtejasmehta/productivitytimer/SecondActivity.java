package com.iamtejasmehta.productivitytimer;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE =
            "com.iamtejasmehta.productivitytimer.extra.MESSAGE";

    private long[] settings = new long[4];

    private EditText min1;
    private EditText min2;
    private EditText min3;
    private EditText min4;
    private EditText sec1;
    private EditText sec2;
    private EditText sec3;
    private EditText sec4;
    
    private long minutesTen;
    private long mintuesOne;
    private long secondsTen;
    private long secondsOne;
    private long m1;
    private long m2;
    private long m3;
    private long m4;
    private long s1;
    private long s2;
    private long s3;
    private long s4;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void saveData(View view) {
        min1 = (EditText) findViewById(R.id.min1);
        min2 = (EditText) findViewById(R.id.min2);
        min3 = (EditText) findViewById(R.id.min3);
        min4 = (EditText) findViewById(R.id.min4);
        sec1 = (EditText) findViewById(R.id.sec1);
        sec2 = (EditText) findViewById(R.id.sec2);
        sec3 = (EditText) findViewById(R.id.sec3);
        sec4 = (EditText) findViewById(R.id.sec4);

        try {
            m1 = Long.parseLong(min1.getText().toString());
        }
        catch(Exception e) {
            m1 = 20;
        }
        try {
            m2 = Long.parseLong(min2.getText().toString());
        }
        catch (Exception e){
            m2 = 5;
        }
        try {
            m3 = Long.parseLong(min3.getText().toString());
        }
        catch(Exception e) {
            m3 = 20;
        }
        try {
            m4 =Long.parseLong(min4.getText().toString());
        }
        catch (Exception e){
            m4 = 10;
        }

        try {
            s1 = Long.parseLong(sec1.getText().toString());
        }
        catch(Exception e) {
            s1 = 0;
        }
        try {
            s2 = Long.parseLong(sec2.getText().toString());
        }
        catch (Exception e){
            s2 = 0;
        }
        try {
            s3 = Long.parseLong(sec3.getText().toString());
        }
        catch(Exception e) {
            s3 = 0;
        }
        try {
            s4 =Long.parseLong(sec4.getText().toString());
        }
        catch (Exception e){
            s4 = 0;
        }

        MainActivity.grind1millis = (m1*60+s1)*1000;
        MainActivity.break1millis = (m2*60+s2)*1000;
        MainActivity.grind2millis = (m3*60+s3)*1000;
        MainActivity.break2millis = (m4*60+s4)*1000;

         minutesTen = ((MainActivity.grind1millis / 1000) / 60) / 10;
         mintuesOne = ((MainActivity.grind1millis / 1000) / 60) % 10;
         secondsTen = ((MainActivity.grind1millis / 1000) % 60) / 10;
         secondsOne = ((MainActivity.grind1millis / 1000) % 60) % 10;

        MainActivity.grind1string = (minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);

        minutesTen = ((MainActivity.break1millis / 1000) / 60) / 10;
        mintuesOne = ((MainActivity.break1millis / 1000) / 60) % 10;
        secondsTen = ((MainActivity.break1millis / 1000) % 60) / 10;
        secondsOne = ((MainActivity.break1millis / 1000) %60) % 10;

        MainActivity.break1string = (minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);

        minutesTen = ((MainActivity.grind2millis / 1000) / 60) / 10;
        mintuesOne = ((MainActivity.grind2millis / 1000) / 60) % 10;
        secondsTen = ((MainActivity.grind2millis / 1000) % 60) / 10;
        secondsOne = ((MainActivity.grind2millis / 1000) % 60) % 10;

        MainActivity.grind2string = (minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);

        minutesTen = ((MainActivity.break2millis / 1000) / 60) / 10;
        mintuesOne = ((MainActivity.break2millis / 1000) / 60) % 10;
        secondsTen = ((MainActivity.break2millis / 1000) % 60) / 10;
        secondsOne = ((MainActivity.break2millis / 1000) % 60) % 10;

        MainActivity.break2string = (minutesTen + "" + mintuesOne + ":" + secondsTen + secondsOne);
        
        

        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }
}
