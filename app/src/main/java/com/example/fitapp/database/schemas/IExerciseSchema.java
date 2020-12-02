package com.example.fitapp.database.schemas;

public interface IExerciseSchema {
    String TABLE_EXERCISE = "exercise";

    String TABLE_PROFILE = IProfileSchema.TABLE_PROFILE;
    String COL_PROFILE_ID = IProfileSchema.COL_PID;

    String COL_ID = "id";
    String COL_ENAME = "name";
    String COL_SETS = "sets";
    String COL_REPS = "reps";
    String COL_WEIGHT = "weight";
    String COL_FK_PROFILE = "idprofile";

    String CREATE_TABLE_EXERCISE = "CREATE TABLE " + TABLE_EXERCISE +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_ENAME + " TEXT, " +
            COL_SETS + " INTEGER, " +
            COL_REPS + " INTEGER, " +
            COL_WEIGHT + " FLOAT, " +
            "FOREIGN KEY (" + COL_FK_PROFILE +") REFERENCES " + TABLE_PROFILE + " (" + COL_PROFILE_ID + "))";

    String[] EXERCISE_COLS = new String [] {COL_ID, COL_ENAME, COL_SETS, COL_REPS, COL_WEIGHT,
            COL_FK_PROFILE};
}
