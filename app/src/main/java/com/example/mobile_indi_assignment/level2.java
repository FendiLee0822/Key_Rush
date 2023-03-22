package com.example.mobile_indi_assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class level2 extends AppCompatActivity {

    //Create the variable name
    TextView level2_title, level2_time, level2_score;
    Button level2_key1, level2_key2, level2_key3, level2_key4, level2_key5, level2_key6, level2_key7, level2_key8, level2_key9, selected_pianokey;
    List<Button> pianokeys;
    MediaPlayer level_2_music;
    CountDownTimer countDownTimer;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level2_title = findViewById(R.id.level2_title);
        level2_time = findViewById(R.id.level2_time);
        level2_score = findViewById(R.id.level2_score);
        level2_key1 = findViewById(R.id.level2_key1);
        level2_key2 = findViewById(R.id.level2_key2);
        level2_key3 = findViewById(R.id.level2_key3);
        level2_key4 = findViewById(R.id.level2_key4);
        level2_key5 = findViewById(R.id.level2_key5);
        level2_key6 = findViewById(R.id.level2_key6);
        level2_key7 = findViewById(R.id.level2_key7);
        level2_key8 = findViewById(R.id.level2_key8);
        level2_key9 = findViewById(R.id.level2_key9);

        //Assign Sound Effect name for specific animation & Start sound
        level_2_music=MediaPlayer.create(level2.this,R.raw.level_2);

        //Get the score from level1 & Assigned into the "level2_score" TextView
        count = getIntent().getIntExtra("score_earned",0);
        level2_score.setText(String.valueOf(count));
        //To assign all piano buttons into a List
        pianokeys = new ArrayList<>();
        pianokeys.add(level2_key1);
        pianokeys.add(level2_key2);
        pianokeys.add(level2_key3);
        pianokeys.add(level2_key4);
        pianokeys.add(level2_key5);
        pianokeys.add(level2_key6);
        pianokeys.add(level2_key7);
        pianokeys.add(level2_key8);
        pianokeys.add(level2_key9);

        //Show Timer and intent to next page when end
        countDownTimer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                level2_time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Reset the state of all Buttons to "unselected" when the timer finishes
                for (Button button : pianokeys) {
                    button.setSelected(false);
                }
                //Intent to level2_end
                Intent level2_end = new Intent(level2.this, level2_end .class);
                Intent top25 = new Intent(level2.this, top25.class);
                level_2_music.stop();

                level2_end.putExtra("score_earned", count);
                top25.putExtra("score_earned", count);
                startActivity(level2_end);
                startActivity(top25);
                finish();

            }
        }.start();

        // Set an OnClickListener on each Button
        for (Button button : pianokeys) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Check if the clicked button is the currently selected button
                    if (v == selected_pianokey) {
                        //Increase the count of selected pianokey has been touch
                        count++;

                        //Set the selected key be false
                        selected_pianokey.setSelected(false);

                        //Set the current count of selected pianokey has been touch in the level1_score
                        level2_score.setText("" + count);

                        // Directly select the next Button
                        select_next_pianokey();
                    }
                }
            });
        }

        // Select a random button initially
        select_next_pianokey();

        //To start the music
        level_2_music = MediaPlayer.create(this,R.raw.level_2);
        level_2_music.setLooping(true);
        level_2_music.start();
    }

    private void select_next_pianokey() {

        // Generate a random number between 0 and the size of your Button array or List.
        Random rand = new Random();
        int randomIndex = rand.nextInt(pianokeys.size());

        // Use the random number to select the next Button from the array or List, and set its state to "selected".
        selected_pianokey = pianokeys.get(randomIndex);
        selected_pianokey.setSelected(true);
    }
}