package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
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

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    ImageView f1;
    ImageView f2;
    ImageView f3;
    ImageView f4;
    ImageView f5;
    ImageView f6;
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
        f1=(ImageView)findViewById(R.id.glyph1);
        f2=(ImageView)findViewById(R.id.glyph2);
        f3=(ImageView)findViewById(R.id.glyph3);
        f4=(ImageView)findViewById(R.id.glyph4);
        f5=(ImageView)findViewById(R.id.glyph5);
        f6=(ImageView)findViewById(R.id.glyph6);



    }
    void enterPassword(int i){

        if(entry<6) {
            password[entry]=i;
            entry++;
            drawn = true;
        }
    }
    boolean validatePassword(){


        if(Arrays.equals(code,password)){
            //do if true
            return true;
        }
        else{
            // do if false
            entry = 0;
            f1.setAlpha(0.f);
            f2.setAlpha(0.f);
            f3.setAlpha(0.f);
            f4.setAlpha(0.f);
            f5.setAlpha(0.f);
            f6.setAlpha(0.f);
            g1.setVisibility(View.VISIBLE);
            g2.setVisibility(View.VISIBLE);
            g3.setVisibility(View.VISIBLE);
            g4.setVisibility(View.VISIBLE);
            g5.setVisibility(View.VISIBLE);
            g6.setVisibility(View.VISIBLE);
            return false;
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

        SoundPool sounds;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes aA = new AudioAttributes.Builder()
                    .build();
            sounds = new SoundPool.Builder().setAudioAttributes(aA).setMaxStreams(6).build();
        }
        else {

            sounds = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }


        int pull = sounds.load(this,R.raw.pull2,1);
        int forest = sounds.load(this,R.raw.forest,1);
        int glow = sounds.load(this,R.raw.glow,1);

        long glowdur = 1000;




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
                if(drawn ==false) {
                    password = new int[]{0, 0, 0, 0, 0, 0};
                    Log.d("myTag", "num " + Float.toString(g1.getY()));
                    g1.animate().translationY(0).setDuration(500);
                    g2.animate().translationY(0).setDuration(500);
                    g3.animate().translationY(0).setDuration(500);
                    g4.animate().translationY(0).setDuration(500);
                    g5.animate().translationY(0).setDuration(500);
                    g6.animate().translationY(0).setDuration(500);
                    Log.d("myTag", "num " + Float.toString(g1.getY()));
                    img.animate().translationY(img2.getY() - 1000).setDuration(500).setListener(new AnimatorListenerAdapter() {
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
                    Log.d("myTag", "num " + Float.toString(img2.getY() - 900));
                }
                if (drawn ==true){
                    g1.setVisibility(View.INVISIBLE);
                    g2.setVisibility(View.INVISIBLE);
                    g3.setVisibility(View.INVISIBLE);
                    g4.setVisibility(View.INVISIBLE);
                    g5.setVisibility(View.INVISIBLE);
                    g6.setVisibility(View.INVISIBLE);
                    if(validatePassword()){
                        sounds.play(pull,1,1,0,0,1);
                        img.animate().translationY(-3300).setDuration(1000);
                        f1.animate().translationY(-3300).setDuration(1000);
                        f2.animate().translationY(-3300).setDuration(1000);
                        f3.animate().translationY(-3300).setDuration(1000);
                        f4.animate().translationY(-3300).setDuration(1000);
                        f5.animate().translationY(-3300).setDuration(1000);
                        f6.animate().translationY(-3300).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                            }
                        });

                    }else{
                        drawn = false;

                    }

                }


            }
            public void onSwipeBottom(){
                f1.setAlpha(0.f);
                f2.setAlpha(0.f);
                f3.setAlpha(0.f);
                f4.setAlpha(0.f);
                f5.setAlpha(0.f);
                f6.setAlpha(0.f);
                g1.setVisibility(View.VISIBLE);
                g2.setVisibility(View.VISIBLE);
                g3.setVisibility(View.VISIBLE);
                g4.setVisibility(View.VISIBLE);
                g5.setVisibility(View.VISIBLE);
                g6.setVisibility(View.VISIBLE);
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
                Log.d("myTag", "bottom: "+Float.toString(img2.getY()));
                drawn = false;
                entry = 0;


            }
        });




            g1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f1.animate().alpha(1f).setDuration(glowdur);
                    enterPassword(1);

                }
            });
            g2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f2.animate().alpha(1f).setDuration(glowdur);
                    enterPassword(2);
                }
            });
            g3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);

                    f3.animate().alpha(1f).setDuration(glowdur);
                    enterPassword(3);
                }
            });
            g4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f4.animate().alpha(1f).setDuration(glowdur);
                    enterPassword(4);
                }
            });
            g5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f5.animate().alpha(1f).setDuration(glowdur);
                    enterPassword(5);
                }
            });
            g6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f6.animate().alpha(1f).setDuration(glowdur);
                    enterPassword(6);
                }
            });



    }
}

