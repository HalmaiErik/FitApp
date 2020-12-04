package com.example.fitapp.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitapp.database.schemas.IRunSchema;

public class RunDAO extends AbstractDAO implements IRunSchema {
    public RunDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected <T> T cursorToEntity(Cursor cursor) {
        return null;
    }

    @Override
    protected String[] cursorToData(Cursor cursor) {
        return new String[0];
    }
}
