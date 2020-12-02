package com.example.fitapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDAO {
    public SQLiteDatabase db;

    public AbstractDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public boolean insert(String tableName, ContentValues values) {
        long result = db.insert(tableName, null, values);

        return result != -1;
    }

    public int delete(String tableName, String selection, String[] selectionArgs) {
        return db.delete(tableName, selection, selectionArgs);
    }

    public int update(String tableName, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(tableName, values, selection, selectionArgs);
    }

    protected abstract <T> T cursorToEntity(Cursor cursor);


}
