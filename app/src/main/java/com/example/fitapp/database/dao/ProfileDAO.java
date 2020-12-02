package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fitapp.database.schemas.IProfileSchema;
import com.example.fitapp.model.Profile;

public class ProfileDAO extends AbstractDAO implements IProfileSchema {
    private Cursor cursor;

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

        try {
            return super.insert(TABLE_PROFILE, values) > 0;
        } catch (SQLiteConstraintException e) {
            Log.w("Database", e.getMessage());
            return false;
        }
    }

    public boolean editProfile(Profile profile) {

    }

    @Override
    protected <T> T cursorToEntity(Cursor cursor) {
        return null;
    }
}
