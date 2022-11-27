package com.example.gymprogress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gymprogress.db.DBexercises;
import com.example.gymprogress.db.exercises;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class see_exercise extends AppCompatActivity {

    EditText txtName, txtWeight, txtCategory;
    Button btnSave;
    FloatingActionButton fabEdit, fabDelete;

    exercises exercise;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_exercise);

        txtName = findViewById(R.id.seetxtName);
        txtWeight = findViewById(R.id.seetxtWeight);
        txtCategory = findViewById(R.id.seetxtCategory);
        fabEdit = findViewById(R.id.fabseeEdit);
        fabDelete = findViewById(R.id.fabseeDelete);
        btnSave = findViewById(R.id.btnseeSave);
        btnSave.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBexercises dbexercises = new DBexercises(see_exercise.this);
        exercise = dbexercises.seeExercises(id);

        if(exercise != null){
            txtName.setText(exercise.getexercise_name());
            txtWeight.setText(exercise.getexercise_weight());
            txtCategory.setText(exercise.getexercise_category());
            txtName.setInputType(InputType.TYPE_NULL);
            txtWeight.setInputType(InputType.TYPE_NULL);
            txtCategory.setInputType(InputType.TYPE_NULL);
        }

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(see_exercise.this, edit_exercise.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(see_exercise.this);
                builder.setMessage("Do you want to delete this exercise?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(dbexercises.deleteExercise(id)){
                                    backtoMain();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void backtoMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}