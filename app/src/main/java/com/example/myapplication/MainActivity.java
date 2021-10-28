package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    public ImageView img;
    public ImageView img2;

    public ImageView g1;
    public ImageView g2;
    public ImageView g3;
    public ImageView g4;
    public ImageView g5;
    public ImageView g6;
    public ImageView f1;
    public ImageView f2;
    public ImageView f3;
    public ImageView f4;
    public ImageView f5;
    public ImageView f6;
    public int[] password;
    public int[] code;
    public int entry;
    public boolean drawn;


    public String msg;
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
            g1.setVisibility(View.INVISIBLE);
            g2.setVisibility(View.INVISIBLE);
            g3.setVisibility(View.INVISIBLE);
            g4.setVisibility(View.INVISIBLE);
            g5.setVisibility(View.INVISIBLE);
            g6.setVisibility(View.INVISIBLE);
            return true;
        }
        else{

            // do if false
            entry = 0;

            return false;
        }
    }
    protected void onStart(Bundle savedInstanceState){
        img=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
        Log.d("myTag", "sw " + Float.toString(img.getY()));


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
        int glow = sounds.load(this,R.raw.glow,1);
        int first = sounds.load(this,R.raw.pull3,1);

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
                    sounds.play(first,1,1,0,0,.5f);
                    password = new int[]{0, 0, 0, 0, 0, 0};
                    Log.d("myTag", "num " + Float.toString(g1.getY()));
                    Log.d("myTag", "sw " + Float.toString(img.getY()));
                    g1.animate().translationY(0).setDuration(500);
                    g2.animate().translationY(0).setDuration(500);
                    g3.animate().translationY(0).setDuration(500);
                    g4.animate().translationY(0).setDuration(500);
                    g5.animate().translationY(0).setDuration(500);
                    g6.animate().translationY(0).setDuration(500);
                    Log.d("myTag", "num " + Float.toString(g1.getY()));
                    Log.d("myTag", "sw " + Float.toString(img.getY()));
                    img.animate().translationY(img2.getY() - 800).setDuration(500).setListener(new AnimatorListenerAdapter() {
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
                    if(validatePassword()){
                        sounds.play(pull,1,1,0,0,1);
                        synchronized (f6.animate().translationY(-3300).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);


                                sounds.release();
                                onDestroy();
                                System.exit(0);
                            }
                        })) {
                            f1.animate().translationY(-3300).setDuration(1000);
                            f2.animate().translationY(-3300).setDuration(1000);
                            f3.animate().translationY(-3300).setDuration(1000);
                            f4.animate().translationY(-3300).setDuration(1000);
                            f5.animate().translationY(-3300).setDuration(1000);
                            img.animate().translationY(-3300).setDuration(1000);

                        }

                    }else{
                        drawn = false;


                        /*

                        synchronized (img.animate().translationY(img2.getY() - 1050).setDuration(400)) {
                            g1.animate().translationY(-50).setDuration(400);
                            g2.animate().translationY(-50).setDuration(400);
                            g3.animate().translationY(-50).setDuration(400);
                            g4.animate().translationY(-50).setDuration(400);
                            g5.animate().translationY(-50).setDuration(400);
                            g6.animate().translationY(-50).setDuration(400);
                            f1.animate().translationY(-50).setDuration(400);
                            f2.animate().translationY(-50).setDuration(400);
                            f3.animate().translationY(-50).setDuration(400);
                            f4.animate().translationY(-50).setDuration(400);
                            f5.animate().translationY(-50).setDuration(400);
                            f6.animate().translationY(-50).setDuration(400).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);

                                    reset();

                                }
                            });
                        }
                        */

                        long fadeout = 500;
                        synchronized (f1.animate().alpha(0.f).setDuration(fadeout)) {
                            f2.animate().alpha(0.f).setDuration(fadeout);
                            f3.animate().alpha(0.f).setDuration(fadeout);
                            f4.animate().alpha(0.f).setDuration(fadeout);
                            f5.animate().alpha(0.f).setDuration(fadeout);
                            f6.animate().alpha(0.f).setDuration(fadeout);
                        }


                    }

                }


            }
            public void onSwipeBottom(){
                sounds.play(first,1,1,0,0,.5f);
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
                g1.animate().translationY(900-200).setDuration(500);
                g2.animate().translationY(900-200).setDuration(500);
                g3.animate().translationY(900-200).setDuration(500);
                g4.animate().translationY(900-200).setDuration(500);
                g5.animate().translationY(900-200).setDuration(500);
                g6.animate().translationY(900-200).setDuration(500);
                img.animate().translationY(img2.getY()-100).setDuration(500);
                Log.d("myTag", "bottom: "+Float.toString(img.getY()));
                drawn = false;
                entry = 0;


            }
        });




            g1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f1.animate().alpha(.6f).setDuration(glowdur);
                    enterPassword(1);


                }
            });
            g2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f2.animate().alpha(.6f).setDuration(glowdur);
                    enterPassword(2);
                }
            });
            g3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);

                    f3.animate().alpha(.6f).setDuration(glowdur);
                    enterPassword(3);
                }
            });
            g4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f4.animate().alpha(.6f).setDuration(glowdur);
                    enterPassword(4);
                }
            });
            g5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f5.animate().alpha(.6f).setDuration(glowdur);
                    enterPassword(5);
                }
            });
            g6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    sounds.play(glow,1,1,0,0,1);
                    f6.animate().alpha(.6f).setDuration(glowdur);
                    enterPassword(6);
                }
            });



    }

    private void reset() {
        synchronized (img.animate().translationY(img2.getY() - 1000).setDuration(100)){
        f1.animate().translationY(0).setDuration(100);
        f2.animate().translationY(0).setDuration(100);
        f3.animate().translationY(0).setDuration(100);
        f4.animate().translationY(0).setDuration(100);
        f5.animate().translationY(0).setDuration(100);
        f6.animate().translationY(0).setDuration(100);
        g1.animate().translationY(0).setDuration(100);
        g2.animate().translationY(0).setDuration(100);
        g3.animate().translationY(0).setDuration(100);
        g4.animate().translationY(0).setDuration(100);
        g5.animate().translationY(0).setDuration(100);
        g6.animate().translationY(0).setDuration(100);
        }
    }

}

