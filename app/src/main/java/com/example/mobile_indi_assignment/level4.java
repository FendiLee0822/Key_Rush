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

public class level4 extends AppCompatActivity {

    //Create the variable name
    TextView level4_title, level4_time, level4_score;
    Button level4_key1, level4_key2, level4_key3, level4_key4, level4_key5, level4_key6, level4_key7, level4_key8, level4_key9,
            level4_key10, level4_key11, level4_key12, level4_key13, level4_key14, level4_key15, level4_key16, level4_key17,
            level4_key18, level4_key19, level4_key20, level4_key21, level4_key22, level4_key23, level4_key24, level4_key25,
            selected_pianokey;
    List<Button> pianokeys;
    MediaPlayer level_4;
    CountDownTimer countDownTimer;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level4_title = findViewById(R.id.level4_title);
        level4_time = findViewById(R.id.level4_time);
        level4_score = findViewById(R.id.level4_score);
        level4_key1 = findViewById(R.id.level4_key1);
        level4_key2 = findViewById(R.id.level4_key2);
        level4_key3 = findViewById(R.id.level4_key3);
        level4_key4 = findViewById(R.id.level4_key4);
        level4_key5 = findViewById(R.id.level4_key5);
        level4_key6 = findViewById(R.id.level4_key6);
        level4_key7 = findViewById(R.id.level4_key7);
        level4_key8 = findViewById(R.id.level4_key8);
        level4_key9 = findViewById(R.id.level4_key9);
        level4_key10 = findViewById(R.id.level4_key10);
        level4_key11 = findViewById(R.id.level4_key11);
        level4_key12 = findViewById(R.id.level4_key12);
        level4_key13 = findViewById(R.id.level4_key13);
        level4_key14 = findViewById(R.id.level4_key14);
        level4_key15 = findViewById(R.id.level4_key15);
        level4_key16 = findViewById(R.id.level4_key16);
        level4_key17 = findViewById(R.id.level4_key17);
        level4_key18 = findViewById(R.id.level4_key18);
        level4_key19 = findViewById(R.id.level4_key19);
        level4_key20 = findViewById(R.id.level4_key20);
        level4_key21 = findViewById(R.id.level4_key21);
        level4_key22 = findViewById(R.id.level4_key22);
        level4_key23 = findViewById(R.id.level4_key23);
        level4_key24 = findViewById(R.id.level4_key24);
        level4_key25 = findViewById(R.id.level4_key25);

        //Assign Sound Effect name for specific animation & Start sound
        level_4=MediaPlayer.create(level4.this,R.raw.level_4);

        //Get the score from level1 & Assigned into the "level2_score" TextView
        count = getIntent().getIntExtra("score_earned",0);
        level4_score.setText(String.valueOf(count));
        //To assign all piano buttons into a List
        pianokeys = new ArrayList<>();
        pianokeys.add(level4_key1);
        pianokeys.add(level4_key2);
        pianokeys.add(level4_key3);
        pianokeys.add(level4_key4);
        pianokeys.add(level4_key5);
        pianokeys.add(level4_key6);
        pianokeys.add(level4_key7);
        pianokeys.add(level4_key8);
        pianokeys.add(level4_key9);
        pianokeys.add(level4_key10);
        pianokeys.add(level4_key11);
        pianokeys.add(level4_key12);
        pianokeys.add(level4_key13);
        pianokeys.add(level4_key14);
        pianokeys.add(level4_key15);
        pianokeys.add(level4_key16);
        pianokeys.add(level4_key17);
        pianokeys.add(level4_key18);
        pianokeys.add(level4_key19);
        pianokeys.add(level4_key20);
        pianokeys.add(level4_key21);
        pianokeys.add(level4_key22);
        pianokeys.add(level4_key23);
        pianokeys.add(level4_key24);
        pianokeys.add(level4_key25);

        //Show Timer and intent to next page when end
        countDownTimer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                level4_time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Reset the state of all Buttons to "unselected" when the timer finishes
                for (Button button : pianokeys) {
                    button.setSelected(false);
                }

                //Intent to level2_end
                Intent level3_end = new Intent(level4.this, level4_end.class);
                Intent top25 = new Intent(level4.this, top25.class);
                level_4.stop();

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
                        level4_score.setText("" + count);

                        // Directly select the next Button
                        select_next_pianokey();
                    }
                }
            });
        }

        // Select a random button initially
        select_next_pianokey();

        //To start the music
        level_4 = MediaPlayer.create(this,R.raw.level_4);
        level_4.setLooping(true);
        level_4.start();
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