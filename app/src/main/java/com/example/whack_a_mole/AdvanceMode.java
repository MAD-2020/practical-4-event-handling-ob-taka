package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AdvanceMode extends AppCompatActivity{

    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;
    int totalScore;
    TextView score2;
    Toast toast;
    private static final int SHORT_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_mode);

        btn_1 = findViewById(R.id.btn1);
        btn_2 = findViewById(R.id.btn2);
        btn_3 = findViewById(R.id.btn3);
        btn_4 = findViewById(R.id.btn4);
        btn_5 = findViewById(R.id.btn5);
        btn_6 = findViewById(R.id.btn6);
        btn_7 = findViewById(R.id.btn7);
        btn_8 = findViewById(R.id.btn8);
        btn_9 = findViewById(R.id.btn9);
        score2 = findViewById(R.id.advScore);
        totalScore = getIntent().getIntExtra("totalScore",0);
        score2.setText(String.valueOf(totalScore));


        new CountDownTimer(10000, 1000){
          public void onTick(long timer){
             toast = Toast.makeText(getApplicationContext(), "Get Ready in " + timer / 1000 + " seconds", Toast.LENGTH_SHORT);
             toast.show();
              Log.d("#g","Ready CountDown!" + timer/1000);
              configbutton(false);
          }
          public void onFinish() {
              toast = Toast.makeText(getApplicationContext(), "GO!", Toast.LENGTH_SHORT);
              toast.show();
              Log.d("#g","Ready CountDown Complete");
              assignBtn(btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9);
              configbutton(true);
              repeat();
          }
        }.start();



    }

    private void assignBtn(Button a, Button b, Button c, Button d, Button e, Button f, Button g, Button h, Button i){
        String[] list = {"0","0","0","0","0","0","0","0","*"};
        Random rnd = new Random();
        for(int x = list.length -1; x > 0; x--){
            int index = rnd.nextInt(x + 1);
            String y = list[index];
            list[index] = list[x];
            list[x] = y;
        }
            a.setText(list[0]);
            b.setText(list[1]);
            c.setText(list[2]);
            d.setText(list[3]);
            e.setText(list[4]);
            f.setText(list[5]);
            g.setText(list[6]);
            h.setText(list[7]);
            i.setText(list[8]);

    }

    public void btnclicked(View v){

        Button clicked = (Button) v;
        String result = clicked.getText().toString();
        if (result.equals("*")) {
            totalScore += 1;
            score2.setText(String.valueOf(totalScore));
            Log.d("#g", "Hit, score added!");

        } else {
            totalScore -= 1;
            score2.setText(String.valueOf(totalScore));
            Log.d("#g", "Missed, score deducted!");
        }
        assignBtn(btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9);
    }

    public void configbutton(boolean x){
        btn_1.setEnabled(x);
        btn_2.setEnabled(x);
        btn_3.setEnabled(x);
        btn_4.setEnabled(x);
        btn_5.setEnabled(x);
        btn_6.setEnabled(x);
        btn_7.setEnabled(x);
        btn_8.setEnabled(x);
        btn_9.setEnabled(x);

    }

    public void repeat(){
        new CountDownTimer(1000, 1000){
            public void onTick(long timer){
                //log
            }
            public void onFinish() {
                assignBtn(btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9);
                Log.d("#g","New Mole Location!");
                repeat();
            }
        }.start();
    }



}

