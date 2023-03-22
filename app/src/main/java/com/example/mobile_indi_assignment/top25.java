package com.example.mobile_indi_assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class top25 extends AppCompatActivity {

    TextView top25_name_title, top25_score_title, top25_score;
    EditText top25_name_enter;
    Button top25_cancelbutton, top25_savebutton;
    MediaPlayer splash_click, background_music;
    Animation bounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top25);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        top25_name_title = findViewById(R.id.top25_name_title);
        top25_score_title = findViewById(R.id.top25_score_title);
        top25_name_enter = findViewById(R.id.top25_name_enter);
        top25_score = findViewById(R.id.top25_score);
        top25_cancelbutton = findViewById(R.id.top25_cancelbutton);
        top25_savebutton = findViewById(R.id.top25_savebutton);
        bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click=MediaPlayer.create(top25.this,R.raw.click_button);

        //Get the score from level1 & Assigned into the "top25_score" TextView
        int score = getIntent().getIntExtra("score_earned",0);
        top25_score.setText(String.valueOf(score));

        //Create OnClickListener for rule_button & Intent to corresponding page
        top25_cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                top25_cancelbutton.startAnimation(bounce);
                onBackPressed();
            }
        });

        //Create OnClickListener for rule_button & Intent to corresponding page
        top25_savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                top25_savebutton.startAnimation(bounce);
                background_music.stop();

                // Get the user name from the pop-out window
                String userName = top25_name_enter.getText().toString();
                int score_database = Integer.parseInt(top25_score.getText().toString());

                // Add the user to the database
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(top25.this);
                dbHelper.saveScoreIfTop25(userName, score_database);

                Intent score_board = new Intent(top25.this, score_board.class);
                startActivity(score_board);
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