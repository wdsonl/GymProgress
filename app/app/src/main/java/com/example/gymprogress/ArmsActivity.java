package com.example.gymprogress;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymprogress.adapters.ListExercisesAdapter;
import com.example.gymprogress.db.DBexercises;
import com.example.gymprogress.db.exercises;

import java.util.ArrayList;

public class ArmsActivity extends AppCompatActivity {

    RecyclerView listexercises;
    ArrayList<exercises> listArrayExercises;
    ListExercisesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms);

        listexercises = findViewById(R.id.listExercisesArms);
        listexercises.setLayoutManager(new LinearLayoutManager(this));

        DBexercises dbexercises = new DBexercises(ArmsActivity.this);

        listArrayExercises = new ArrayList<>();

        adapter = new ListExercisesAdapter(dbexercises.showExercises("Arms/Shoulders"));
        listexercises.setAdapter(adapter);


    }
}