package com.example.gymprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gymprogress.db.DBexercises;

public class newExercise extends AppCompatActivity {

    EditText txtName, txtWeight;
    Spinner spinnerCategory;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        txtName = findViewById(R.id.txtName);
        txtWeight = findViewById(R.id.txtWeight);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtName.getText().toString().equals("") && !txtWeight.getText().toString().equals("") && spinnerCategory.getSelectedItemPosition() != 0) {

                    DBexercises dbExercises = new DBexercises(newExercise.this);

                    int categoryselectionINT = spinnerCategory.getSelectedItemPosition();
                    String categoryselectionString = null;

                    switch (categoryselectionINT){

                        case 1:
                            categoryselectionString = "Chest";
                            break;
                        case 2:
                            categoryselectionString = "Back";
                            break;
                        case 3:
                            categoryselectionString = "Legs";
                            break;
                        case 4:
                            categoryselectionString = "Arms/Shoulders";
                            break;

                    }

                    long id = dbExercises.addExercises(txtName.getText().toString(), txtWeight.getText().toString(), categoryselectionString);

                    if (id > 0) {
                        Toast.makeText(newExercise.this, "Exercise saved", Toast.LENGTH_LONG).show();
                        clear();
                    } else {
                        Toast.makeText(newExercise.this, "Error saving exercise", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(newExercise.this, "Please, fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void clear() {
        txtName.setText("");
        txtWeight.setText("");
    }
}