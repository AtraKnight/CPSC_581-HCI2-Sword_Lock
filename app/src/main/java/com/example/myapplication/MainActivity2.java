package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    //to store glyphs
    ImageView g1;
    ImageView g2;
    ImageView g3;
    ImageView g4;
    ImageView g5;
    ImageView g6;
    //to store password
    int[] password;
    int entry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        password = new int[5];
        entry = 0;

        //set glyphs
        g1=(ImageView)findViewById(R.id.glyph1);
        g2=(ImageView)findViewById(R.id.glyph2);
        g3=(ImageView)findViewById(R.id.glyph3);
        g4=(ImageView)findViewById(R.id.glyph4);
        g5=(ImageView)findViewById(R.id.glyph5);
        g6=(ImageView)findViewById(R.id.glyph6);



//glyph click functionality
        g1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g1.setVisibility(View.INVISIBLE);
                enterPassword(1);

            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g2.setVisibility(View.INVISIBLE);
                enterPassword(2);
            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g3.setVisibility(View.INVISIBLE);
                enterPassword(3);
            }
        });
        g4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g4.setVisibility(View.INVISIBLE);
                enterPassword(4);
            }
        });
        g5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g5.setVisibility(View.INVISIBLE);
                enterPassword(5);
            }
        });
        g6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                g6.setVisibility(View.INVISIBLE);
                enterPassword(6);
            }
        });

    }

    //password entry, no validation yet returns to main activity

    void enterPassword(int i){
        /*
        if(entry<6) {
            password[entry] = i;
            entry++;
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        */
    }

}