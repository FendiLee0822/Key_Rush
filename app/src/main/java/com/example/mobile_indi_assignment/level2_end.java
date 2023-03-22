package com.example.mobile_indi_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class level2_end extends AppCompatActivity {

    TextView level2_end_title, level2_end_scoretitle, level2_end_score;
    ImageButton end_button, continue_button;
    MediaPlayer splash_click, background_music;
    Animation bounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_end);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level2_end_title = findViewById(R.id.level2_end_title);
        level2_end_scoretitle = findViewById(R.id.level2_end_scoretitle);
        level2_end_score = findViewById(R.id.level2_end_score);
        end_button = findViewById(R.id.level2_end_endbutton);
        continue_button = findViewById(R.id.level2_end_continuebutton);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click=MediaPlayer.create(level2_end.this,R.raw.click_button);

        //Get the score from level1 & Assigned into the "level1_end_score" TextView
        int score = getIntent().getIntExtra("score_earned",0);
        level2_end_score.setText(String.valueOf(score));

        //Create OnClickListener for rule_button & Intent to corresponding page
        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                end_button.startAnimation(bounce);

                String userName = "";
                // Check user is Top 25 or not
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(level2_end.this);
                dbHelper.checkScoreIfTop25(userName, score);

                Intent end_game = new Intent(level2_end.this,home_page.class);
                startActivity(end_game);
            }
        });

        //Create OnClickListener for rule_button & Intent to corresponding page
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                continue_button.startAnimation(bounce);
                background_music.stop();
                Intent level3 = new Intent(level2_end.this, level3.class);
                level3.putExtra("score_earned", score);
                startActivity(level3);
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