package com.example.fitapp.database.schemas;

public interface IRunSchema {
    String TABLE_RUN = "run";

    String TABLE_PROFILE = IProfileSchema.TABLE_PROFILE;
    String COL_PROFILE_ID = IProfileSchema.COL_PID;

    String COL_ID = "id";
    String COL_DISTANCE = "distance";
    String COL_PACE = "pace";
    String COL_DATE = "date";
    String COL_TIME = "time";
    String COL_FK_PROFILE = "idprofile";

    String CREATE_TABLE_RUN = "CREATE TABLE " + TABLE_RUN +
            "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_DATE + " TEXT, " +
            COL_TIME + " TEXT, " +
            COL_DISTANCE + " FLOAT, " +
            COL_PACE + " FLOAT, " +
            COL_FK_PROFILE + " INTEGER, " +
            "FOREIGN KEY (" + COL_FK_PROFILE + ") REFERENCES " +
            TABLE_PROFILE + " (" + COL_PROFILE_ID + ") ON UPDATE CASCADE ON DELETE CASCADE)";

    String[] RUN_COLS = new String [] {COL_ID, COL_DISTANCE, COL_PACE, COL_DATE, COL_TIME,
            COL_FK_PROFILE};
}
