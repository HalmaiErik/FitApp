package com.example.fitapp.database.schemas;

public interface IProfileSchema {
    String TABLE_PROFILE = "profile";

    String COL_PID = "id";
    String COL_PNAME = "name";
    String COL_GENDER = "gender";
    String COL_AGE = "age";
    String COL_CURRENT_WEIGHT = "currweight";
    String COL_GOAL_WEIGHT = "goalweight";

    String CREATE_TABLE = "CREATE TABLE " + TABLE_PROFILE +
            "(" + COL_PID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_PNAME + " TEXT, " +
            COL_AGE + " INTEGER, " +
            COL_GENDER + " TEXT, " +
            COL_CURRENT_WEIGHT + " FLOAT, " +
            COL_GOAL_WEIGHT + " FLOAT)";

    String[] PROFILE_COLS = new String [] {COL_PID, COL_PNAME, COL_GENDER, COL_AGE,
            COL_CURRENT_WEIGHT, COL_GOAL_WEIGHT};
}
