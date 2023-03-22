package com.example.mobile_indi_assignment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class rule_page extends AppCompatActivity {

    //Create the variable name
    TextView rule_title, rule_123, rule_45;
    ImageButton rule_close_button;
    MediaPlayer splash_click, background_music;
    Animation bounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_page);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        rule_title = findViewById(R.id.rule_title);
        rule_123 = findViewById(R.id.rule_123);
        rule_45 = findViewById(R.id.rule_45);
        rule_close_button = findViewById(R.id.rule_close_button);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click=MediaPlayer.create(rule_page.this,R.raw.click_button);

        //Create OnClickListener for rule_button & Intent to corresponding page
        rule_close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                rule_close_button.startAnimation(bounce);
                onBackPressed();
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
        background_music.release();
    }
}