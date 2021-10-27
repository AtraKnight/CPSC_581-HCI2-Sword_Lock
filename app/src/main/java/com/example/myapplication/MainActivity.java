package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;

import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

import android.widget.ImageView;
import android.widget.RelativeLayout;

//to send to new activity
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    ImageView img;
    ImageView img2;

    ImageView g1;
    ImageView g2;
    ImageView g3;
    ImageView g4;
    ImageView g5;
    ImageView g6;
    int[] password;
    int[] code;
    int entry;
    boolean drawn;




    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        code = new int[]{1,2,3,4,5,6};
        password = new int[]{0,0,0,0,0,0};
        entry = 0;
        drawn = false;
        onStart(savedInstanceState);
    }
    void enterPassword(int i){

        if(entry<6) {
            password[entry]=i;
            entry++;
            if(entry == 6){
                validatePassword(password);
            }
        }
    }
    void validatePassword(int [] p){


        if(Arrays.equals(code,password)){
            //do if true
            System.exit(0);
        }
        else{
            // do if false
            g1=(ImageView)findViewById(R.id.oGlyph1);
            g2=(ImageView)findViewById(R.id.oGlyph2);
            g3=(ImageView)findViewById(R.id.oGlyph3);
            g4=(ImageView)findViewById(R.id.oGlyph4);
            g5=(ImageView)findViewById(R.id.oGlyph5);
            g6=(ImageView)findViewById(R.id.oGlyph6);
            entry = 0;
            g1.setVisibility(View.VISIBLE);
            g2.setVisibility(View.VISIBLE);
            g3.setVisibility(View.VISIBLE);
            g4.setVisibility(View.VISIBLE);
            g5.setVisibility(View.VISIBLE);
            g6.setVisibility(View.VISIBLE);
        }
    }
    protected void onStart(Bundle savedInstanceState){
        img=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);

        //set glyphs
        g1=(ImageView)findViewById(R.id.oGlyph1);
        g2=(ImageView)findViewById(R.id.oGlyph2);
        g3=(ImageView)findViewById(R.id.oGlyph3);
        g4=(ImageView)findViewById(R.id.oGlyph4);
        g5=(ImageView)findViewById(R.id.oGlyph5);
        g6=(ImageView)findViewById(R.id.oGlyph6);


        /* REMOVE this and REPLACE with Swipe Guestures
        //add dragging
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(img);

                v.startDrag(dragData,myShadow,null,0);
                return true;
            }
        });

        img.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                        // Do nothing
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        int y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        y_cord = (int) event.getY();
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                        break;

                    case DragEvent.ACTION_DROP:
                        Log.d(msg, "ACTION_DROP event");

                        break;
                    default: break;
                }
                return true;
            }
        });


        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);

                    img.startDrag(data, shadowBuilder, img, 0);
                    img.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });

        //drag doesn't work as intended



        //listener that waits for user to click on the hilt
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                startActivity(intent);
            }
        });*/


        //swipe Gesture


        img.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            public void onSwipeTop(){
                password = new int[]{0,0,0,0,0,0};
                Log.d("myTag", "num "+Float.toString(g1.getY()));
                g1.animate().translationY(0).setDuration(500);
                g2.animate().translationY(0).setDuration(500);
                g3.animate().translationY(0).setDuration(500);
                g4.animate().translationY(0).setDuration(500);
                g5.animate().translationY(0).setDuration(500);
                g6.animate().translationY(0).setDuration(500);
                Log.d("myTag", "num "+Float.toString(g1.getY()));
                img.animate().translationY(img2.getY()-900).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //when first animation ends, start the next one
                        //Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        //startActivity(intent);
                        g1.setClickable(true);
                        g2.setClickable(true);
                        g3.setClickable(true);
                        g4.setClickable(true);
                        g5.setClickable(true);
                        g6.setClickable(true);


                    }
                });
                Log.d("myTag", "num "+Float.toString(img2.getY()-900));


            }
            public void onSwipeBottom(){
                g1.setClickable(false);
                g2.setClickable(false);
                g3.setClickable(false);
                g4.setClickable(false);
                g5.setClickable(false);
                g6.setClickable(false);
                g1.animate().translationY(900).setDuration(500);
                g2.animate().translationY(900).setDuration(500);
                g3.animate().translationY(900).setDuration(500);
                g4.animate().translationY(900).setDuration(500);
                g5.animate().translationY(900).setDuration(500);
                g6.animate().translationY(900).setDuration(500);
                img.animate().translationY(img2.getY()).setDuration(500);
                Log.d("myTag", "num "+Float.toString(img2.getY()));
                drawn = false;
                entry = 0;

            }
        });


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
}

