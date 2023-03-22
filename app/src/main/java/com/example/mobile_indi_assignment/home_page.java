package com.example.mobile_indi_assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class home_page extends AppCompatActivity {

    //Create the variable name
    ImageButton rule_button, scoreboard_button, start_button, soundButton;
    ImageView splash_app_logo;
    MediaPlayer splash_click, background_music;
    Animation bounce;
    private boolean isSoundOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        rule_button = findViewById(R.id.rule_button);
        soundButton = findViewById(R.id.sound_button);
        scoreboard_button = findViewById(R.id.scoreboard_button);
        start_button = findViewById(R.id.start_button);
        splash_app_logo = findViewById(R.id.splash_app_logo);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click=MediaPlayer.create(home_page.this,R.raw.click_button);

        //Create OnClickListener for rule_button & Intent to corresponding page
        rule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                rule_button.startAnimation(bounce);
                Intent intent = new Intent(home_page.this, rule_page.class);
                startActivity(intent);
            }
        });

        //Create OnClickListener for scoreboard_button & Intent to corresponding page
        scoreboard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                scoreboard_button.startAnimation(bounce);
                Intent intent = new Intent(home_page.this, score_board.class);
                startActivity(intent);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSoundOn) {
                    // Turn off the background music
                    isSoundOn = false;
                    soundButton.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    if (background_music != null) {
                        background_music.pause();
                    }
                } else {
                    // Turn on the background music
                    isSoundOn = true;
                    soundButton.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    if (background_music != null) {
                        background_music.start();
                    }
                }
            }
        });

        //Create OnClickListener for start_button & Intent to corresponding page
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                start_button.startAnimation(bounce);
                background_music.stop();
                Intent intent = new Intent(home_page.this, level1.class);
                startActivity(intent);
            }
        });

        //To start the music
        background_music = MediaPlayer.create(this,R.raw.background_music);
        background_music.setLooping(true);
        background_music.start();
    }

    //To stop music
    @Override
    protected void onStop(){
        super.onStop();
        background_music.stop();
    }
}