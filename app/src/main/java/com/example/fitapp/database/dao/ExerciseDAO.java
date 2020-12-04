package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitapp.database.schemas.IExerciseSchema;
import com.example.fitapp.model.Exercise;

public class ExerciseDAO extends AbstractDAO implements IExerciseSchema {
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

    public boolean editExercise(Exercise oldExercise, Exercise newExercise) {
        Cursor cursorId = getExerciseID(oldExercise);
        String[] data = cursorToData(cursorId);
        if (data != null) {
            if (!data[0].equals("")) {
                ContentValues values = createContentValues(newExercise);
                return super.update(TABLE_EXERCISE, values, COL_ID, new String[] {data[0]}) > 0;
            }
        }
        return false;
    }

    public boolean deleteExercise(Exercise exercise) {
        Cursor cursorId = getExerciseID(exercise);
        String[] data = cursorToData(cursorId);
        if (data != null) {
            if (!data[0].equals("")) {
                return super.delete(TABLE_EXERCISE, COL_ID, new String[] {data[0]}) > 0;
            }
        }
        return false;
    }

    public Cursor getExerciseID(Exercise exercise) {
        String selection = COL_ENAME + " =? AND " + COL_REPS + " =? AND " + COL_SETS + " =? AND" +
                COL_WEIGHT + " =? AND " + COL_FK_PROFILE + " =?";
        String[] selectionArgs = new String[] {exercise.getName(), String.valueOf(exercise.getSets()),
                String.valueOf(exercise.getReps()), String.valueOf(exercise.getWeight()),
                String.valueOf(exercise.getFkProfile())};
        return super.db.query(TABLE_EXERCISE, new String[] {COL_ID}, selection, selectionArgs,
                null, null, null);
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

    @Override
    protected String[] cursorToData(Cursor cursor) {
        String id = "";
        String name = "";
        String sets = "";
        String reps = "";
        String weight = "";
        String fkProfile = "";

        if (cursor != null) {
            if (cursor.getColumnIndex(COL_ID) != -1) {
                int idIndex = cursor.getColumnIndexOrThrow(COL_ID);
                id = String.valueOf(cursor.getInt(idIndex));
            }
            if (cursor.getColumnIndex(COL_ENAME) != -1) {
                int nameIndex = cursor.getColumnIndexOrThrow(COL_ENAME);
                name = cursor.getString(nameIndex);
            }
            if (cursor.getColumnIndex(COL_SETS) != -1) {
                int setsIndex = cursor.getColumnIndexOrThrow(COL_SETS);
                sets = String.valueOf(cursor.getInt(setsIndex));
            }
            if (cursor.getColumnIndex(COL_REPS) != -1) {
                int repsIndex = cursor.getColumnIndexOrThrow(COL_REPS);
                reps = String.valueOf(cursor.getInt(repsIndex));
            }
            if (cursor.getColumnIndex(COL_WEIGHT) != -1) {
                int weightIndex = cursor.getColumnIndexOrThrow(COL_WEIGHT);
                weight = String.valueOf(cursor.getFloat(weightIndex));
            }
            if (cursor.getColumnIndex(COL_FK_PROFILE) != -1) {
                int fkProfileIndex = cursor.getColumnIndexOrThrow(COL_FK_PROFILE);
                fkProfile = String.valueOf(cursor.getInt(fkProfileIndex));
            }
            return new String[] {id, name, sets, reps, weight, fkProfile};
        }
        else return null;
    }
}
