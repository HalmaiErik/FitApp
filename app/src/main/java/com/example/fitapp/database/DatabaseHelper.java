package com.example.fitapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.fitapp.database.dao.ExerciseDAO;
import com.example.fitapp.database.dao.ProfileDAO;
import com.example.fitapp.database.dao.RunDAO;
import com.example.fitapp.database.schemas.IExerciseSchema;
import com.example.fitapp.database.schemas.IProfileSchema;
import com.example.fitapp.database.schemas.IRunSchema;
import com.example.fitapp.model.Exercise;
import com.example.fitapp.model.Profile;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitapp_database";
    private static ProfileDAO profileDAO;
    private static ExerciseDAO exerciseDAO;
    private static RunDAO runDAO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        profileDAO = new ProfileDAO(this.getWritableDatabase());
        exerciseDAO = new ExerciseDAO(this.getWritableDatabase());
        runDAO = new RunDAO(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IProfileSchema.CREATE_TABLE_PROFILE);
        db.execSQL(IExerciseSchema.CREATE_TABLE_EXERCISE);
        db.execSQL(IRunSchema.CREATE_TABLE_RUN);

        profileDAO = new ProfileDAO(db);
        exerciseDAO = new ExerciseDAO(db);
        runDAO = new RunDAO(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DATABASE_NAME, "Upgrading database from version "
                + oldVersion + " to "
                + newVersion + " which destroys all old data");
        db.execSQL("DROP TABLE IF EXISTS " + IProfileSchema.TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + IExerciseSchema.TABLE_EXERCISE);
        db.execSQL("DROP TABLE IF EXISTS " + IRunSchema.TABLE_RUN);
        onCreate(db);
    }

    public boolean addProfile(Profile profile) {
        Log.d(DATABASE_NAME, "addProfile: " + profile.toString() + " to " + IProfileSchema.TABLE_PROFILE);
        return profileDAO.addProfile(profile);
    }

    public boolean editProfile(Profile oldProfile, Profile newProfile) {
        Log.d(DATABASE_NAME, "editProfile: " + oldProfile.toString() + " to " + newProfile.toString());
        return profileDAO.editProfile(oldProfile, newProfile);
    }

    public Profile getLastProfile() {
        return profileDAO.getLastProfile();
    }

    public int getLastProfileId() {return profileDAO.getLastProfileID();}

    public boolean isEmptyProfileTable() {
        boolean isEmpty = profileDAO.isTableEmpty();
        Log.d(DATABASE_NAME, "isEmptyProfileTable: " + isEmpty);
        return isEmpty;
    }

    public boolean addExercise(Exercise exercise) {
        Log.d(DATABASE_NAME, "addExercise: " + exercise.toString() + " to " + IExerciseSchema.TABLE_EXERCISE);
        return exerciseDAO.addExercise(exercise);
    }

    public boolean editExercise(Exercise oldExercise, Exercise newExercise) {
        Log.d(DATABASE_NAME, "editExercise: " + oldExercise.toString() + " to " + newExercise.toString());
        return exerciseDAO.editExercise(oldExercise, newExercise);
    }

    public boolean deleteExercise(Exercise exercise) {
        Log.d(DATABASE_NAME, "deleteExercise: " + exercise.toString() + " from " + IExerciseSchema.TABLE_EXERCISE);
        return exerciseDAO.deleteExercise(exercise);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = exerciseDAO.getAllExercises();
        Log.d(DATABASE_NAME, "getAllExercises: count = " + exercises.size());
        return exercises;
    }
}
