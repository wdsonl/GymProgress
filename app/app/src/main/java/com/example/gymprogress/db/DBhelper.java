package com.example.gymprogress.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GymProgressExercises.db";
    public static final String TABLE_EXERCISES = "t_exercises";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EXERCISES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "exercise_name TEXT NOT NULL," +
                "exercise_weight TEXT NOT NULL," +
                "exercise_category TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EXERCISES);
        onCreate(sqLiteDatabase);

    }
}
