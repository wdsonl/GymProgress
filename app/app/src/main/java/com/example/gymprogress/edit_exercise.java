package com.example.gymprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymprogress.db.DBexercises;
import com.example.gymprogress.db.exercises;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class edit_exercise extends AppCompatActivity {

    EditText txtName, txtWeight, txtCategory;
    Button btnSave;
    FloatingActionButton fabEdit, fabDelete;
    boolean correct = false;
    exercises exercise;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_exercise);

        txtName = findViewById(R.id.seetxtName);
        txtWeight = findViewById(R.id.seetxtWeight);
        txtCategory = findViewById(R.id.seetxtCategory);
        btnSave = findViewById(R.id.btnseeSave);
        fabEdit = findViewById(R.id.fabseeEdit);
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete = findViewById(R.id.fabseeDelete);
        fabDelete.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBexercises dbexercises = new DBexercises(edit_exercise.this);
        exercise = dbexercises.seeExercises(id);

        if (exercise != null) {

            txtName.setText(exercise.getexercise_name());

            if(exercise.getexercise_weight().equals("   ")){
                txtWeight.setText("");
            }
            else{
                txtWeight.setText(exercise.getexercise_weight());
            }

            txtCategory.setText(exercise.getexercise_category());

            txtCategory.setInputType(InputType.TYPE_NULL);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtName.getText().toString().equals("") && !txtWeight.getText().toString().equals("")) {
                    correct = dbexercises.editExercise(id, txtName.getText().toString(), txtWeight.getText().toString(), txtCategory.getText().toString());

                    if(correct){
                        Toast.makeText(edit_exercise.this, "Sucess!", Toast.LENGTH_LONG).show();
                        seeRegistry();
                    } else {
                        Toast.makeText(edit_exercise.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(edit_exercise.this, "Please, fill all the fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void seeRegistry(){
        Intent intent = new Intent(this, see_exercise.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}