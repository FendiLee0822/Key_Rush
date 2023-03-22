package com.example.mobile_indi_assignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class level3 extends AppCompatActivity {
    //Create the variable name
    TextView level3_title, level3_time, level3_score;
    Button level3_key1, level3_key2, level3_key3, level3_key4, level3_key5, level3_key6, level3_key7, level3_key8, level3_key9,
            level3_key10, level3_key11, level3_key12, level3_key13, level3_key14, level3_key15, level3_key16, selected_pianokey;
    List<Button> pianokeys;
    MediaPlayer level_3;
    CountDownTimer countDownTimer;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level3_title = findViewById(R.id.level3_title);
        level3_time = findViewById(R.id.level3_time);
        level3_score = findViewById(R.id.level3_score);
        level3_key1 = findViewById(R.id.level3_key1);
        level3_key2 = findViewById(R.id.level3_key2);
        level3_key3 = findViewById(R.id.level3_key3);
        level3_key4 = findViewById(R.id.level3_key4);
        level3_key5 = findViewById(R.id.level3_key5);
        level3_key6 = findViewById(R.id.level3_key6);
        level3_key7 = findViewById(R.id.level3_key7);
        level3_key8 = findViewById(R.id.level3_key8);
        level3_key9 = findViewById(R.id.level3_key9);
        level3_key10 = findViewById(R.id.level3_key10);
        level3_key11 = findViewById(R.id.level3_key11);
        level3_key12 = findViewById(R.id.level3_key12);
        level3_key13 = findViewById(R.id.level3_key13);
        level3_key14 = findViewById(R.id.level3_key14);
        level3_key15 = findViewById(R.id.level3_key15);
        level3_key16 = findViewById(R.id.level3_key16);

        //Assign Sound Effect name for specific animation & Start sound
        level_3=MediaPlayer.create(level3.this,R.raw.level_3);

        //Get the score from level1 & Assigned into the "level2_score" TextView
        count = getIntent().getIntExtra("score_earned",0);
        level3_score.setText(String.valueOf(count));
        //To assign all piano buttons into a List
        pianokeys = new ArrayList<>();
        pianokeys.add(level3_key1);
        pianokeys.add(level3_key2);
        pianokeys.add(level3_key3);
        pianokeys.add(level3_key4);
        pianokeys.add(level3_key5);
        pianokeys.add(level3_key6);
        pianokeys.add(level3_key7);
        pianokeys.add(level3_key8);
        pianokeys.add(level3_key9);
        pianokeys.add(level3_key10);
        pianokeys.add(level3_key11);
        pianokeys.add(level3_key12);
        pianokeys.add(level3_key13);
        pianokeys.add(level3_key14);
        pianokeys.add(level3_key15);
        pianokeys.add(level3_key16);

        //Show Timer and intent to next page when end
        countDownTimer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                level3_time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Reset the state of all Buttons to "unselected" when the timer finishes
                for (Button button : pianokeys) {
                    button.setSelected(false);
                }

                //Intent to level2_end
                Intent level3_end = new Intent(level3.this, level3_end.class);
                Intent top25 = new Intent(level3.this, top25.class);
                level_3.stop();

                level3_end.putExtra("score_earned", count);
                top25.putExtra("score_earned", count);
                startActivity(level3_end);
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
                        level3_score.setText("" + count);

                        // Directly select the next Button
                        select_next_pianokey();
                    }
                }
            });
        }

        // Select a random button initially
        select_next_pianokey();

        //To start the music
        level_3 = MediaPlayer.create(this,R.raw.level_3);
        level_3.setLooping(true);
        level_3.start();
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