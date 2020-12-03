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
        contentValues.put(COL_FK_PROFILE, exercise.getFkProfile());

        return contentValues;
    }

    public boolean addExercise(Exercise exercise) {
        ContentValues values = createContentValues(exercise);
        return super.insert(TABLE_EXERCISE, values) > 0;
    }

    public boolean editExercise(Exercise exercise) {

    }

    public Cursor getExerciseID(String exerciseName) {

    }

    @Override
    protected Exercise cursorToEntity(Cursor cursor) {
        String name = "";
        int sets = -1;
        int reps = -1;
        float weight = -1;
        int fkProfile = -1;

        if (cursor != null) {
            if (cursor.getColumnIndex(COL_ENAME) != -1) {
                int nameIndex = cursor.getColumnIndexOrThrow(COL_ENAME);
                name = cursor.getString(nameIndex);
            }
            if (cursor.getColumnIndex(COL_SETS) != -1) {
                int setsIndex = cursor.getColumnIndexOrThrow(COL_SETS);
                sets = cursor.getInt(setsIndex);
            }
            if (cursor.getColumnIndex(COL_REPS) != -1) {
                int repsIndex = cursor.getColumnIndexOrThrow(COL_REPS);
                reps = cursor.getInt(repsIndex);
            }
            if (cursor.getColumnIndex(COL_WEIGHT) != -1) {
                int weightIndex = cursor.getColumnIndexOrThrow(COL_WEIGHT);
                weight = cursor.getFloat(weightIndex);
            }
            if (cursor.getColumnIndex(COL_FK_PROFILE) != -1) {
                int fkProfileIndex = cursor.getColumnIndexOrThrow(COL_FK_PROFILE);
                fkProfile = cursor.getInt(fkProfileIndex);
            }
            Exercise exercise = new Exercise(name, sets, reps, weight, fkProfile);
            return exercise;
        }
        else return null;
    }
}
