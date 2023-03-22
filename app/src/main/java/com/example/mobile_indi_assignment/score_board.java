package com.example.mobile_indi_assignment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class score_board extends AppCompatActivity {

    TextView scoreboard_title;
    ImageButton scoreboard_closebutton;
    TableLayout tablelayout_scoreboard;
    MediaPlayer splash_click, background_music;
    Animation bounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        //Hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hide the action bar
        getSupportActionBar().hide();

        //Assign variable name for each element in splash screen
        scoreboard_title = findViewById(R.id.scoreboard_title);
        scoreboard_closebutton = findViewById(R.id.scoreboard_closebutton);
        tablelayout_scoreboard = findViewById(R.id.tablelayout_scoreboard);

        //Assign Sound Effect name for specific animation & Start sound
        splash_click= MediaPlayer.create(score_board.this,R.raw.click_button);

        // Display the scores
        displayScores();

        //Create OnClickListener for rule_button & Intent to corresponding page
        scoreboard_closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash_click.start();
                scoreboard_closebutton.startAnimation(bounce);
                onBackPressed();
            }
        });

        //To start the music
        background_music = MediaPlayer.create(this,R.raw.background_music);
        background_music.setLooping(true);
        background_music.start();
    }

    @SuppressLint("SetTextI18n")
    private void displayScores() {
        // Create an instance of the DatabaseHelper class
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        // Query the scores table and get the top 25 scores
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                MyDatabaseHelper.COLUMN_NAME,
                MyDatabaseHelper.COLUMN_SCORE
        };
        String sortOrder = MyDatabaseHelper.COLUMN_SCORE + " DESC";
        Cursor cursor = db.query(
                MyDatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                "25"
        );

        // Add each score to the score table
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_NAME));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_SCORE));


            TableRow row = new TableRow(this);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(name);
            nameTextView.setTextColor(Color.parseColor("#000000"));
            nameTextView.setPadding(10, 10, 10, 10);
            nameTextView.setGravity(Gravity.CENTER);
            row.addView(nameTextView);

            TextView scoreTextView = new TextView(this);
            scoreTextView.setText(Integer.toString(score));
            scoreTextView.setTextColor(Color.parseColor("#000000"));
            scoreTextView.setPadding(10, 10, 10, 10);
            scoreTextView.setGravity(Gravity.CENTER);
            row.addView(scoreTextView);

            tablelayout_scoreboard.addView(row);
        }

        cursor.close();
        db.close();
    }

    //To stop music
    @Override
    protected void onStop(){
        super.onStop();
        background_music.stop();
    }
}