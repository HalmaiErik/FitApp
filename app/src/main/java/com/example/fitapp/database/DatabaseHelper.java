package com.example.fitapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitapp.model.Profile;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitapp_database";

    private static final String TABLE_RUN = "run";

    // Columns of Run table
    private static final String COL_DISTANCE = "distance";
    private static final String COL_PACE = "pace";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableRun = "CREATE TABLE " + TABLE_RUN +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DATE + " TEXT, " +
                COL_TIME + " TEXT, " +
                COL_DISTANCE + " FLOAT, " +
                COL_PACE+ " FLOAT, " +
                "FOREIGN KEY (" + COL_FK_PROFILE +") REFERENCES " + TABLE_PROFILE + " (" + COL_PID + "))";

        db.execSQL(tableProfile);
        db.execSQL(tableExercise);
        db.execSQL(tableRun);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PNAME, profile.getName(), )
    }

}
