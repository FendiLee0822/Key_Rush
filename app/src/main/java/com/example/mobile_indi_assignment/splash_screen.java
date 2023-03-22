package com.example.mobile_indi_assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    //Create the variable name
    ImageView splash_app_logo;
    Animation fade_in;
    MediaPlayer splash_whoosh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        splash_app_logo = findViewById(R.id.splash_app_logo);
        fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //Setting Animations to the elements of Splash
        splash_app_logo.setAnimation(fade_in);

        //Assign Sound Effect name for specific animation & Start sound
        splash_whoosh=MediaPlayer.create(splash_screen.this,R.raw.whoosh);
        splash_whoosh.start();


        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                finally{
                    Intent intent = new Intent(splash_screen.this, home_page.class);
                    startActivity(intent);
                }
            }
        };timer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        splash_whoosh.release();
        finish();
    }
}