package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
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

    public boolean editProfile(Profile profile) {
        ContentValues values = createContentValues(profile);
        return super.update(TABLE_PROFILE, values, COL_PNAME, new String[] {profile.getName()}) > 0;
    }

    @Override
    protected Profile cursorToEntity(Cursor cursor) {
        String name = "";
        int age = -1;
        String gender = "";
        float currWeight = -1;
        float goalWeight = -1;

        if (cursor != null) {
            if (cursor.getColumnIndex(COL_PNAME) != -1) {
                int nameIndex = cursor.getColumnIndexOrThrow(COL_PNAME);
                name = cursor.getString(nameIndex);
            }
            if (cursor.getColumnIndex(COL_AGE) != -1) {
                int ageIndex = cursor.getColumnIndexOrThrow(COL_AGE);
                age = cursor.getInt(ageIndex);
            }
            if (cursor.getColumnIndex(COL_GENDER) != -1) {
                int genderIndex = cursor.getColumnIndexOrThrow(COL_GENDER);
                gender = cursor.getString(genderIndex);
            }
            if (cursor.getColumnIndex(COL_CURRENT_WEIGHT) != -1) {
                int currWeightIndex = cursor.getColumnIndexOrThrow(COL_CURRENT_WEIGHT);
                currWeight = cursor.getFloat(currWeightIndex);
            }
            if (cursor.getColumnIndex(COL_GOAL_WEIGHT) != -1) {
                int goalWeightIndex = cursor.getColumnIndexOrThrow(COL_GOAL_WEIGHT);
                goalWeight = cursor.getFloat(goalWeightIndex);
            }
            Profile profile = new Profile(name, age, gender, currWeight, goalWeight);
            return profile;
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
            if (cursor.getColumnIndex(COL_PID) != -1) {
                int idIndex = cursor.getColumnIndexOrThrow(COL_PID);
                id = String.valueOf(cursor.getInt(idIndex));
            }
            if (cursor.getColumnIndex(COL_PNAME) != -1) {
                int nameIndex = cursor.getColumnIndexOrThrow(COL_PNAME);
                name = cursor.getString(nameIndex);
            }
            if (cursor.getColumnIndex(COL_AGE) != -1) {
                int ageIndex = cursor.getColumnIndexOrThrow(COL_AGE);
                age = String.valueOf(cursor.getInt(ageIndex));
            }
            if (cursor.getColumnIndex(COL_GENDER) != -1) {
                int genderIndex = cursor.getColumnIndexOrThrow(COL_GENDER);
                gender = cursor.getString(genderIndex);
            }
            if (cursor.getColumnIndex(COL_CURRENT_WEIGHT) != -1) {
                int currWeightIndex = cursor.getColumnIndexOrThrow(COL_CURRENT_WEIGHT);
                currWeight = String.valueOf(cursor.getFloat(currWeightIndex));
            }
            if (cursor.getColumnIndex(COL_GOAL_WEIGHT) != -1) {
                int goalWeightIndex = cursor.getColumnIndexOrThrow(COL_GOAL_WEIGHT);
                goalWeight = String.valueOf(cursor.getFloat(goalWeightIndex));
            }
            return new String[] {id, name, age, gender, currWeight, goalWeight};
        }
        else return null;
    }

    public Cursor getProfileID(String name) {
        return super.db.query(TABLE_PROFILE, new String[] {COL_PID}, COL_PNAME, new String[] {name},
                null, null, null);
    }
}
