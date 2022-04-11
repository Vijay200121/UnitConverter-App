package com.vijaygaike.unitconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "history.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "history";
    private static final String HIST_COL = "history";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + HIST_COL + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addNewCourse(String expression) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HIST_COL, expression);
        long r = db.insert(TABLE_NAME, null, values);
        return r != -1;
    }

    public Cursor showHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from history", null);
    }

    public void delete_history() {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = ("delete from " + TABLE_NAME);
        db.execSQL(Query);
    }
}