package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitapp.database.schemas.IExerciseSchema;
import com.example.fitapp.model.Exercise;

import java.util.ArrayList;
import java.util.List;

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
        Cursor idCursor = getExerciseID(oldExercise);
        String id = "";

        if (idCursor.moveToNext()) {
            if (idCursor.getColumnIndex(COL_ID) != -1) {
                int idIndex = idCursor.getColumnIndexOrThrow(COL_ID);
                id = String.valueOf(idCursor.getInt(idIndex));
                idCursor.close();
                ContentValues values = createContentValues(newExercise);
                return super.update(TABLE_EXERCISE, values, COL_ID + " = ?", new String[]{id}) > 0;
            }
        }
        idCursor.close();

        return false;
    }

    public boolean deleteExercise(Exercise exercise) {
        Cursor idCursor = getExerciseID(exercise);
        String id = "";

        if (idCursor.moveToNext()) {
            if (idCursor.getColumnIndex(COL_ID) != -1) {
                int idIndex = idCursor.getColumnIndexOrThrow(COL_ID);
                id = String.valueOf(idCursor.getInt(idIndex));
                idCursor.close();
                return super.delete(TABLE_EXERCISE, COL_ID + " = ?", new String[]{id}) > 0;
            }
        }
        idCursor.close();

        return false;
    }

    public Cursor getExerciseID(Exercise exercise) {
        String selection = COL_ENAME + " = ? AND " + COL_SETS + " = ? AND " + COL_REPS + " = ? AND " +
                COL_WEIGHT + " = ? AND " + COL_FK_PROFILE + " = ?";
        String[] selectionArgs = new String[] {exercise.getName(), String.valueOf(exercise.getSets()),
                String.valueOf(exercise.getReps()), String.valueOf(exercise.getWeight()),
                String.valueOf(exercise.getFkProfile())};
        return super.db.query(TABLE_EXERCISE, new String[] {COL_ID}, selection, selectionArgs,
                null, null, null);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        Cursor cursor = super.db.query(TABLE_EXERCISE, EXERCISE_COLS, null, null,
                COL_ID, null, COL_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Exercise exercise = cursorToEntity(cursor);
                exerciseList.add(exercise);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return exerciseList;
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
