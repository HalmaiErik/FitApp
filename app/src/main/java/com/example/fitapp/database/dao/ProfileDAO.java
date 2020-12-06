package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitapp.database.schemas.IProfileSchema;
import com.example.fitapp.model.Profile;

public class ProfileDAO extends AbstractDAO implements IProfileSchema {
    public ProfileDAO(SQLiteDatabase db) {
        super(db);
    }

    private ContentValues createContentValues(Profile profile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PNAME, profile.getName());
        contentValues.put(COL_AGE, profile.getAge());
        contentValues.put(COL_GENDER, profile.getGender());
        contentValues.put(COL_CURRENT_WEIGHT, profile.getCurrWeight());
        contentValues.put(COL_GOAL_WEIGHT, profile.getGoalWeight());

        return contentValues;
    }

    public boolean addProfile(Profile profile) {
        ContentValues values = createContentValues(profile);
        return super.insert(TABLE_PROFILE, values) > 0;
    }

    public boolean editProfile(Profile oldProfile, Profile newProfile) {
        Cursor idCursor = getProfileID(oldProfile);
        String id = "";

        if (idCursor.moveToNext()) {
            if (idCursor.getColumnIndex(COL_PID) != -1) {
                int idIndex = idCursor.getColumnIndexOrThrow(COL_PID);
                id = String.valueOf(idCursor.getInt(idIndex));
                ContentValues contentValues = createContentValues(newProfile);
                return super.update(TABLE_PROFILE, contentValues, COL_PID + " = ?", new String[]{id}) > 0;
            }
        }

        return false;
    }

    public Cursor getProfileID(Profile profile) {
        String selection = COL_PNAME + " = ? AND " + COL_AGE + " = ? AND " + COL_GENDER + " = ? AND " +
                COL_CURRENT_WEIGHT + " = ? AND " + COL_GOAL_WEIGHT + " = ?";
        String[] selectionArgs = new String[] {profile.getName(), String.valueOf(profile.getAge()),
                profile.getGender(), String.valueOf(profile.getCurrWeight()),
                String.valueOf(profile.getGoalWeight())};

        return super.db.query(TABLE_PROFILE, new String[] {COL_PID}, selection, selectionArgs,
                null, null, null);
    }

    public Profile getLastProfile() {
        Cursor cursor = super.db.query(TABLE_PROFILE, null, null, null,
                null, null, COL_PID + " DESC", "1");
        return cursorToEntity(cursor);
    }

    public boolean isTableEmpty() {
        long rowNumber = DatabaseUtils.queryNumEntries(db, TABLE_PROFILE);
        return rowNumber == 0;
    }

    @Override
    protected Profile cursorToEntity(Cursor cursor) {
        String name = "";
        int age = -1;
        String gender = "";
        float currWeight = -1;
        float goalWeight = -1;

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getColumnIndex(COL_PNAME) != -1) {
                int nameIndex = cursor.getColumnIndexOrThrow(COL_PNAME);
                name = cursor.getString(nameIndex);
            }
            else return null;

            if (cursor.getColumnIndex(COL_AGE) != -1) {
                int ageIndex = cursor.getColumnIndexOrThrow(COL_AGE);
                age = cursor.getInt(ageIndex);
            }
            else return null;

            if (cursor.getColumnIndex(COL_GENDER) != -1) {
                int genderIndex = cursor.getColumnIndexOrThrow(COL_GENDER);
                gender = cursor.getString(genderIndex);
            }
            else return null;

            if (cursor.getColumnIndex(COL_CURRENT_WEIGHT) != -1) {
                int currWeightIndex = cursor.getColumnIndexOrThrow(COL_CURRENT_WEIGHT);
                currWeight = cursor.getFloat(currWeightIndex);
            }
            else return null;

            if (cursor.getColumnIndex(COL_GOAL_WEIGHT) != -1) {
                int goalWeightIndex = cursor.getColumnIndexOrThrow(COL_GOAL_WEIGHT);
                goalWeight = cursor.getFloat(goalWeightIndex);
            }
            else return null;

            return new Profile(name, age, gender, currWeight, goalWeight);
        }
        else return null;
    }

    @Override
    protected String[] cursorToData(Cursor cursor) {
        String id = "";
        String name = "";
        String age = "";
        String gender = "";
        String currWeight = "";
        String goalWeight = "";

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getColumnIndex(COL_PID) != -1) {
                int idIndex = cursor.getColumnIndexOrThrow(COL_PID);
                id = String.valueOf(cursor.getInt(idIndex));
            }
            else return null;

            if (cursor.getColumnIndex(COL_PNAME) != -1) {
                int nameIndex = cursor.getColumnIndexOrThrow(COL_PNAME);
                name = cursor.getString(nameIndex);
            }
            else return null;

            if (cursor.getColumnIndex(COL_AGE) != -1) {
                int ageIndex = cursor.getColumnIndexOrThrow(COL_AGE);
                age = String.valueOf(cursor.getInt(ageIndex));
            }
            else return null;

            if (cursor.getColumnIndex(COL_GENDER) != -1) {
                int genderIndex = cursor.getColumnIndexOrThrow(COL_GENDER);
                gender = cursor.getString(genderIndex);
            }
            else return null;

            if (cursor.getColumnIndex(COL_CURRENT_WEIGHT) != -1) {
                int currWeightIndex = cursor.getColumnIndexOrThrow(COL_CURRENT_WEIGHT);
                currWeight = String.valueOf(cursor.getFloat(currWeightIndex));
            }
            else return null;

            if (cursor.getColumnIndex(COL_GOAL_WEIGHT) != -1) {
                int goalWeightIndex = cursor.getColumnIndexOrThrow(COL_GOAL_WEIGHT);
                goalWeight = String.valueOf(cursor.getFloat(goalWeightIndex));
            }
            else return null;

            return new String[] {id, name, age, gender, currWeight, goalWeight};
        }
        else return null;
    }
}
