package com.example.mobile_indi_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private  static final int DATABASE_VERSION = 1;
    private  static final String DATABASE_NAME = "top25.db";
    public  static final String TABLE_NAME = "top25_user";
    public  static final String COLUMN_ID = "id";
    public  static final String COLUMN_NAME = "name";
    public  static final String COLUMN_SCORE = "score";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_SCORE + " INTEGER);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void checkScoreIfTop25(String userName, int score) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Step 1: Retrieve the top 25 scores
        String[] columns = { "name", "score" };
        String orderBy = "score DESC";
        String limit = "25";
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, orderBy, limit);

        // Step 2: Check if the user's score is in the top 25
        boolean userInTop25 = false;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int topScore = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
                if (score > topScore) {
                    userInTop25 = true;
                    break;
                }
            } while (cursor.moveToNext());
        }

        if (userInTop25) {
            // Step 3: Start the top25 activity
            Intent intent = new Intent(mContext, top25.class);
            // Set any necessary extras on the intent
            mContext.startActivity(intent);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }

    public void saveScoreIfTop25(String userName, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.COLUMN_NAME, userName);
        values.put(MyDatabaseHelper.COLUMN_SCORE, score);
        db.insert(MyDatabaseHelper.TABLE_NAME, null, values);
    }
}
