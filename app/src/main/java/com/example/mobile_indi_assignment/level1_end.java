package com.example.mobile_indi_assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class level1_end extends AppCompatActivity {

    TextView level1_end_title, level1_end_scoretitle, level1_end_score;
    ImageButton end_button, continue_button;
    MediaPlayer splash_click, background_music;
    Animation bounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_end);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level1_end_title = findViewById(R.id.level1_end_title);
        level1_end_scoretitle = findViewById(R.id.level1_end_scoretitle);
        level1_end_score = findViewById(R.id.level1_end_score);
        end_button = findViewById(R.id.end_button);
        continue_button = findViewById(R.id.continue_button);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click=MediaPlayer.create(level1_end.this,R.raw.click_button);

        //Get the score from level1 & Assigned into the "level1_end_score" TextView
        int score = getIntent().getIntExtra("score_earned",0);
        level1_end_score.setText(String.valueOf(score));

        //Create OnClickListener for rule_button & Intent to corresponding page
        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                end_button.startAnimation(bounce);

                String userName = "";
                // Check user is Top 25 or not
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(level1_end.this);
                dbHelper.checkScoreIfTop25(userName, score);

                Intent intent = new Intent(level1_end.this, home_page.class);
                startActivity(intent);
            }
        });

        //Create OnClickListener for rule_button & Intent to corresponding page
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                continue_button.startAnimation(bounce);
                background_music.stop();
                Intent level2 = new Intent(level1_end.this, level2.class);
                level2.putExtra("score_earned", score);
                startActivity(level2);
                finish();
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