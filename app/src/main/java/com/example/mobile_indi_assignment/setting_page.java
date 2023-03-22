package com.example.mobile_indi_assignment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class setting_page extends AppCompatActivity {

    //Create the variable name
    TextView setting_title;
    ImageButton setting_close_button;
    MediaPlayer splash_click, background_music;
    Animation bounce;
    ImageView music_icon, speaker_icon;
    Switch music_switch, speaker_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        setting_title = findViewById(R.id.setting_title);
        setting_close_button = findViewById(R.id.setting_close_button);
        music_icon = findViewById(R.id.music_icon);
        speaker_icon = findViewById(R.id.speaker_icon);
        music_switch = findViewById(R.id.music_switch);
        speaker_switch = findViewById(R.id.speaker_switch);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click=MediaPlayer.create(setting_page.this,R.raw.click_button);

        //Create OnClickListener for rule_button & Intent to corresponding page
        setting_close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                setting_close_button.startAnimation(bounce);
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