package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitapp.database.schemas.IExerciseSchema;
import com.example.fitapp.model.Exercise;

public class ExerciseDAO extends AbstractDAO implements IExerciseSchema {
    private Cursor cursor;

    public ExerciseDAO(SQLiteDatabase db) {
        super(db);
    }

    private ContentValues createContentValues(Exercise exercise) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ENAME, exercise.getName());
        contentValues.put(COL_SETS, exercise.getSets());
        contentValues.put(COL_REPS, exercise.getReps());
        contentValues.put(COL_WEIGHT, exercise.getWeight());
        contentValues.put(COL_GOAL_WEIGHT, profile.getGoalWeight());

        return contentValues;
    }

    public boolean addExercise(Exercise exercise) {

    }

    @Override
    protected <T> T cursorToEntity(Cursor cursor) {
        return null;
    }
}
