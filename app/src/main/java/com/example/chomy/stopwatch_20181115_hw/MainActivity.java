package com.example.chomy.stopwatch_20181115_hw;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//   집에와서 반장쌤 코드 보고 똑같이 복습

    TextView textViewTimer, textViewLap;
    Handler handler = new Handler();
    long startTime, updateTime, millisecondTime, timeBuff;
    int milliseconds;
    int currenMode = 0;
    SimpleDateFormat format = new SimpleDateFormat("mm : ss : SSS");
    StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTimer = findViewById(R.id.textViewTimer);
        textViewLap = findViewById(R.id.textViewLap);

        ((Button)findViewById(R.id.buttonStart)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonPause)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonReset)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonSaveLap)).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonStart){
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable,0);
            currenMode = 1;
        } else if (v.getId() == R.id.buttonPause) {
            timeBuff += millisecondTime;
            handler.removeCallbacks(runnable);
            currenMode = 0;
        } else if (v.getId() == R.id.buttonReset) {
            handler.removeCallbacks(runnable);
            startTime = 0;
            timeBuff = 0;
            textViewTimer.setText("00 : 00 : 000");
            textViewLap.setText("");
            stringBuffer.setLength(0);
            currenMode = 0;
        } else if (v.getId() == R.id.buttonSaveLap) {
            if(currenMode==0) return;
            stringBuffer.append(format.format(updateTime)+"\n");
            textViewLap.setText(stringBuffer.toString());
        }

    }

    public Runnable runnable = new Runnable() {

        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            textViewTimer.setText(format.format(updateTime));
            handler.postDelayed(this, 0);

        }
    };
}
