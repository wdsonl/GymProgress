package com.example.gymprogress.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBexercises extends DBhelper {

    Context context;

    public DBexercises(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long addExercises(String exercise_name, String exercise_weight, String exercise_category) {

        long id = 0;

        try {
            DBhelper dbHelper = new DBhelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("exercise_name", exercise_name);
            values.put("exercise_weight", exercise_weight);
            values.put("exercise_category", exercise_category);

            id = db.insert(TABLE_EXERCISES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<exercises> showExercises(String category) {

        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<exercises> listExercises = new ArrayList<>();
        exercises exercise;
        Cursor cursorExercises;

        cursorExercises = db.query(TABLE_EXERCISES, null, "exercise_category=?", new String[]{category}, null, null, null);

        if (cursorExercises.moveToFirst()) {
            do {
                exercise = new exercises();
                exercise.setId(cursorExercises.getInt(0));
                exercise.setexercise_name(cursorExercises.getString(1));
                exercise.setexercise_weight(cursorExercises.getString(2));
                exercise.setexercise_category(cursorExercises.getString(3));
                listExercises.add(exercise);
            } while (cursorExercises.moveToNext());
        }

        cursorExercises.close();

        return listExercises;
    }

    public exercises seeExercises(int id) {

        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        exercises exercise = null;
        Cursor cursorExercises;

        cursorExercises = db.rawQuery("SELECT * FROM " + TABLE_EXERCISES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorExercises.moveToFirst()) {
            exercise = new exercises();
            exercise.setId(cursorExercises.getInt(0));
            exercise.setexercise_name(cursorExercises.getString(1));
            exercise.setexercise_weight(cursorExercises.getString(2));
            exercise.setexercise_category(cursorExercises.getString(3));
        }

        cursorExercises.close();

        return exercise;
    }

    public boolean editExercise(int id, String exercise_name, String exercise_weight, String exercise_category) {

        boolean correct = false;

        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_EXERCISES + " SET exercise_name = '" + exercise_name + "', exercise_weight = '" + exercise_weight + "', exercise_category = '" + exercise_category + "' WHERE id='" + id + "' ");
            correct = true;
        } catch (Exception ex) {
            ex.toString();
            correct = false;
        } finally {
            db.close();
        }

        return correct;
    }

    public boolean deleteExercise(int id) {

        boolean correct = false;

        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_EXERCISES + " WHERE id = '" + id + "'");
            correct = true;
        } catch (Exception ex) {
            ex.toString();
            correct = false;
        } finally {
            db.close();
        }

        return correct;
    }
}
