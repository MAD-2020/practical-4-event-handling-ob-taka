package com.example.whack_a_mole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3;
    TextView score;
    int totalScore;
    int addedScore;

    public MainActivity() {
        super();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("#g", "created");

        btn_1 = (Button) findViewById(R.id.Left);
        btn_2 = (Button) findViewById(R.id.Middle);
        btn_3 = (Button) findViewById(R.id.Right);
        score = (TextView) findViewById(R.id.myTextView);

    }

    public void alertbox(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(getApplicationContext(), AdvanceMode.class);
                        intent.putExtra("totalScore", totalScore) ;
                        startActivity(intent);
                        Log.d("#g","User accepts!");
                        Log.d("#g","Current User Score: " + totalScore);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.d("#g","User decline!");
                        break;
                }
            }
        };
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
        alertBox.setMessage("Would you like to advance to advance mode?").setTitle("Warning! insane Whack-A-Mole incoming!")
                .setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener);
        alertBox.show();
    }

    private void swap(Button x, Button y, Button z){
        String a,b,c;
        a = x.getText().toString();
        b = y.getText().toString();
        c = z.getText().toString();
        String[] list = {a,b,c};
        Random rnd = new Random();
        for(int i = list.length -1; i > 0; i--){
            int index = rnd.nextInt(i + 1);
            String d = list[index];
            list[index] = list[i];
            list[i] = d;
        }
        x.setText(list[0]);
        y.setText(list[1]);
        z.setText(list[2]);

    }

    public void btnclicked(View v){
        Button clicked = (Button) v;
        String result = clicked.getText().toString();
        if (result.equals("*")) {
            totalScore += 1;
            addedScore += 1;
            score.setText(String.valueOf(totalScore));
            Log.d("#g", "Button " + String.valueOf(v.getTag()) +" clicked!");
            Log.d("#g", "Hit, Score added!");

        } else {
            totalScore -= 1;
            score.setText(String.valueOf(totalScore));
            //Log.d("#g", "Button right clicked! Missed, score deducted!");
            Log.d("#g", "Button " + String.valueOf(v.getTag()) +" clicked!");
            Log.d("#g", "Missed, point deducted");
        }


        swap(btn_1, btn_2, btn_3);

        if (addedScore == 10){
            alertbox();
            Log.d("#g", "Advance option given to user!");
            addedScore = 0;
        }
    }
}
