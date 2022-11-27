package com.example.gymprogress;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymprogress.adapters.ListExercisesAdapter;
import com.example.gymprogress.db.DBexercises;
import com.example.gymprogress.db.exercises;

import java.util.ArrayList;

public class LegsActivity extends AppCompatActivity {

    RecyclerView listexercises;
    ArrayList<exercises> listArrayExercises;
    ListExercisesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        listexercises = findViewById(R.id.listExercisesLegs);
        listexercises.setLayoutManager(new LinearLayoutManager(this));

        DBexercises dbexercises = new DBexercises(LegsActivity.this);

        listArrayExercises = new ArrayList<>();

        adapter = new ListExercisesAdapter(dbexercises.showExercises("Legs"));
        listexercises.setAdapter(adapter);


    }
}