package com.example.mobile_indi_assignment;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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

public class level5 extends AppCompatActivity {

    //Create the variable name
    TextView level5_title, level5_time, level5_score;
    Button level5_key1, level5_key2, level5_key3, level5_key4, level5_key5, level5_key6, level5_key7, level5_key8, level5_key9,
            level5_key10, level5_key11, level5_key12, level5_key13, level5_key14, level5_key15, level5_key16, level5_key17,
            level5_key18, level5_key19, level5_key20, level5_key21, level5_key22, level5_key23, level5_key24, level5_key25,
            level5_key26, level5_key27, level5_key28, level5_key29, level5_key30, level5_key31, level5_key32, level5_key33,
            level5_key34, level5_key35, level5_key36, selected_pianokey;
    List<Button> pianokeys;
    MediaPlayer level_5;
    CountDownTimer countDownTimer;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        level5_title = findViewById(R.id.level5_title);
        level5_time = findViewById(R.id.level5_time);
        level5_score = findViewById(R.id.level5_score);
        level5_key1 = findViewById(R.id.level5_key1);
        level5_key2 = findViewById(R.id.level5_key2);
        level5_key3 = findViewById(R.id.level5_key3);
        level5_key4 = findViewById(R.id.level5_key4);
        level5_key5 = findViewById(R.id.level5_key5);
        level5_key6 = findViewById(R.id.level5_key6);
        level5_key7 = findViewById(R.id.level5_key7);
        level5_key8 = findViewById(R.id.level5_key8);
        level5_key9 = findViewById(R.id.level5_key9);
        level5_key10 = findViewById(R.id.level5_key10);
        level5_key11 = findViewById(R.id.level5_key11);
        level5_key12 = findViewById(R.id.level5_key12);
        level5_key13 = findViewById(R.id.level5_key13);
        level5_key14 = findViewById(R.id.level5_key14);
        level5_key15 = findViewById(R.id.level5_key15);
        level5_key16 = findViewById(R.id.level5_key16);
        level5_key17 = findViewById(R.id.level5_key17);
        level5_key18 = findViewById(R.id.level5_key18);
        level5_key19 = findViewById(R.id.level5_key19);
        level5_key20 = findViewById(R.id.level5_key20);
        level5_key21 = findViewById(R.id.level5_key21);
        level5_key22 = findViewById(R.id.level5_key22);
        level5_key23 = findViewById(R.id.level5_key23);
        level5_key24 = findViewById(R.id.level5_key24);
        level5_key25 = findViewById(R.id.level5_key25);
        level5_key26 = findViewById(R.id.level5_key26);
        level5_key27 = findViewById(R.id.level5_key27);
        level5_key28 = findViewById(R.id.level5_key28);
        level5_key29 = findViewById(R.id.level5_key29);
        level5_key30 = findViewById(R.id.level5_key30);
        level5_key31 = findViewById(R.id.level5_key31);
        level5_key32 = findViewById(R.id.level5_key32);
        level5_key33 = findViewById(R.id.level5_key33);
        level5_key34 = findViewById(R.id.level5_key34);
        level5_key35 = findViewById(R.id.level5_key35);
        level5_key36 = findViewById(R.id.level5_key36);

        //Assign Sound Effect name for specific animation & Start sound
        level_5=MediaPlayer.create(level5.this,R.raw.level_5);

        //Get the score from level1 & Assigned into the "level2_score" TextView
        count = getIntent().getIntExtra("score_earned",0);
        level5_score.setText(String.valueOf(count));
        //To assign all piano buttons into a List
        pianokeys = new ArrayList<>();
        pianokeys.add(level5_key1);
        pianokeys.add(level5_key2);
        pianokeys.add(level5_key3);
        pianokeys.add(level5_key4);
        pianokeys.add(level5_key5);
        pianokeys.add(level5_key6);
        pianokeys.add(level5_key7);
        pianokeys.add(level5_key8);
        pianokeys.add(level5_key9);
        pianokeys.add(level5_key10);
        pianokeys.add(level5_key11);
        pianokeys.add(level5_key12);
        pianokeys.add(level5_key13);
        pianokeys.add(level5_key14);
        pianokeys.add(level5_key15);
        pianokeys.add(level5_key16);
        pianokeys.add(level5_key17);
        pianokeys.add(level5_key18);
        pianokeys.add(level5_key19);
        pianokeys.add(level5_key20);
        pianokeys.add(level5_key21);
        pianokeys.add(level5_key22);
        pianokeys.add(level5_key23);
        pianokeys.add(level5_key24);
        pianokeys.add(level5_key25);
        pianokeys.add(level5_key26);
        pianokeys.add(level5_key27);
        pianokeys.add(level5_key28);
        pianokeys.add(level5_key29);
        pianokeys.add(level5_key30);
        pianokeys.add(level5_key31);
        pianokeys.add(level5_key32);
        pianokeys.add(level5_key33);
        pianokeys.add(level5_key34);
        pianokeys.add(level5_key35);
        pianokeys.add(level5_key36);

        //Show Timer and intent to next page when end
        countDownTimer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                level5_time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Reset the state of all Buttons to "unselected" when the timer finishes
                for (Button button : pianokeys) {
                    button.setSelected(false);
                }

                //Intent to level5_end
                Intent level5_end = new Intent(level5.this, level5_end.class);
                Intent top25 = new Intent(level5.this, top25.class);
                level_5.stop();

                level5_end.putExtra("score_earned", count);
                top25.putExtra("score_earned", count);
                startActivity(level5_end);
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
                        level5_score.setText("" + count);

                        // Directly select the next Button
                        select_next_pianokey();
                    }
                }
            });
        }

        // Select a random button initially
        select_next_pianokey();

        //To start the music
        level_5 = MediaPlayer.create(this,R.raw.level_5);
        level_5.setLooping(true);
        level_5.start();
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