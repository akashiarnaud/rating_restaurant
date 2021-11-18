package com.example.examen.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RatingDbHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "rating.db";
    static final int DATABASE_VERSION = 1;
    public RatingDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RATING_SQL = "CREATE TABLE " + RatingContract.RatingEntry.TABLE_NAME + " (" +
                RatingContract.RatingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                RatingContract.RatingEntry.COLUMN_NAME + " TEXT NOT NULL," +
                RatingContract.RatingEntry.COLUMN_DATE_HEURE + " TEXT NOT NULL," +
                RatingContract.RatingEntry.COLUMN_RATING_DECO + " NUMBER NOT NULL," +
                RatingContract.RatingEntry.COLUMN_RATING_FOOD + " NUMBER NOT NULL," +
                RatingContract.RatingEntry.COLUMN_RATING_SERVICE + " NUMBER NOT NULL," +
                RatingContract.RatingEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL"+
            "); ";
        db.execSQL(SQL_CREATE_RATING_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RatingContract.RatingEntry.TABLE_NAME);
        onCreate(db);
    }
}
