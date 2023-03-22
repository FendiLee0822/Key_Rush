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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class level1 extends AppCompatActivity {

    //Create the variable name
    TextView level1_title, level1_time, level1_score;
    Button level1_key1, level1_key2, level1_key3, level1_key4, selected_pianokey;
    List<Button> pianokeys;
    MediaPlayer level_1;
    CountDownTimer countDownTimer;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level1_title = findViewById(R.id.level1_title);
        level1_time = findViewById(R.id.level1_time);
        level1_score = findViewById(R.id.level1_score);
        level1_key1 = findViewById(R.id.level1_key1);
        level1_key2 = findViewById(R.id.level1_key2);
        level1_key3 = findViewById(R.id.level1_key3);
        level1_key4 = findViewById(R.id.level1_key4);

        //Assign Sound Effect name for specific animation & Start sound
        level_1=MediaPlayer.create(level1.this,R.raw.level_1);

        //To assign all piano buttons into a List
        pianokeys = new ArrayList<>();
        pianokeys.add(level1_key1);
        pianokeys.add(level1_key2);
        pianokeys.add(level1_key3);
        pianokeys.add(level1_key4);

        //Show Timer and intent to next page when end
        countDownTimer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                level1_time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Reset the state of all Buttons to "unselected" when the timer finishes
                for (Button button : pianokeys) {
                    button.setSelected(false);
                }

                //Intent to another level1_end
                Intent level1_end = new Intent(level1.this, level1_end.class);
                Intent top25 = new Intent(level1.this, top25.class);
                level_1.stop();

                level1_end.putExtra("score_earned", count);
                top25.putExtra("score_earned", count);
                startActivity(level1_end);
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
                        level1_score.setText("" + count);

                        // Directly select the next Button
                        select_next_pianokey();
                    }
                }
            });
        }

        // Select a random button initially
        select_next_pianokey();

        //To start the music
        level_1 = MediaPlayer.create(this,R.raw.level_1);
        level_1.setLooping(true);
        level_1.start();
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