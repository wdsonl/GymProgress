package com.example.gymprogress;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gymprogress.db.DBexercises;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.gymprogress.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Button chestBTN;
    private Button backBTN;
    private Button legsBTN;
    private Button armsBTN;

    private DBexercises db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Welcome! Do you want to add recommended exercises? You can add it later on the option \"Add recommended exercises\"!")
                    .setPositiveButton("Add recommended exercises", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            loadexercises();
                            Toast.makeText(MainActivity.this, "Recommended exercises added!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Add new exercise", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            newExercise();
                        }
                    })
                    .setNeutralButton("Ignore", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "You can use the option \"Add new exercise\"", Toast.LENGTH_SHORT).show();
                        }
                    }).show();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();
        }

        chestBTN = findViewById(R.id.chestBTNXML);
        chestBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent peito = new Intent(MainActivity.this, ChestActivity.class);
                startActivity(peito);
            }
        });

        backBTN = findViewById(R.id.backBTNXML);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent costas = new Intent(getApplicationContext(), BackActivity.class);
                startActivity(costas);
            }
        });

        legsBTN = findViewById(R.id.legsBTNXML);
        legsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perna = new Intent(getApplicationContext(), LegsActivity.class);
                startActivity(perna);
            }
        });

        armsBTN = findViewById(R.id.armsBTNXML);
        armsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent braco = new Intent(getApplicationContext(), ArmsActivity.class);
                startActivity(braco);
            }
        });
    }

    private void loadexercises() {

        db = new DBexercises(this);

        db.addExercises("Incline Dumbbell Bench Press", "   ", "Chest");
        db.addExercises("Flat Dumbbell Bench Press", "   ", "Chest");
        db.addExercises("Decline Dumbbell bench press", "   ", "Chest");
        db.addExercises("Incline Barbell Bench Press", "   ", "Chest");
        db.addExercises("Flat Barbell Bench Press", "   ", "Chest");
        db.addExercises("Decline Barbell bench press", "   ", "Chest");
        db.addExercises("Cable crossover", "   ", "Chest");
        db.addExercises("Fly", "   ", "Chest");

        db.addExercises("Lat pulldown", "   ", "Back");
        db.addExercises("Single-arm dumbbell row", "   ", "Back");
        db.addExercises("Reverse fly", "   ", "Back");
        db.addExercises("Smith machine row", "   ", "Back");
        db.addExercises("Seated row", "   ", "Back");
        db.addExercises("Barbell Deadlift", "   ", "Back");
        db.addExercises("Bent-Over Row", "   ", "Back");
        db.addExercises("T-Bar Row", "   ", "Back");

        db.addExercises("Barbell Back Squat", "   ", "Legs");
        db.addExercises("Hack Squat", "   ", "Legs");
        db.addExercises("Lunge", "   ", "Legs");
        db.addExercises("Leg Press", "   ", "Legs");
        db.addExercises("Seated Leg Curl", "   ", "Legs");
        db.addExercises("Lying Leg Curl", "   ", "Legs");
        db.addExercises("Leg Extension", "   ", "Legs");
        db.addExercises("Barbell Hip Thrust", "   ", "Legs");

        db.addExercises("Concentration Curl", "   ", "Arms/Shoulders");
        db.addExercises("Hammer Curl", "   ", "Arms/Shoulders");
        db.addExercises("Biceps Cable Curl", "   ", "Arms/Shoulders");
        db.addExercises("Dumbbell Skull Crusher", "   ", "Arms/Shoulders");
        db.addExercises("Triceps Pushdown", "   ", "Arms/Shoulders");
        db.addExercises("Triceps Kickback", "   ", "Arms/Shoulders");
        db.addExercises("Barbell Overhead Press", "   ", "Arms/Shoulders");
        db.addExercises("Lateral Raise", "   ", "Arms/Shoulders");
        db.addExercises("Dumbbell Shoulder Press", "   ", "Arms/Shoulders");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addexercisemenu) {
            newExercise();
            return true;
        }

        if (id == R.id.addrecommendedmenu) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to add recommended exercises?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            loadexercises();
                            Toast.makeText(MainActivity.this, "Recommended exercises added!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

            return true;
        }

        if (id == R.id.deleteallmenu) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to delete all exercises?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteDatabase(DBexercises.DATABASE_NAME);
                            Toast.makeText(MainActivity.this, "Exercises deleted!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newExercise(){
        Intent intent = new Intent(this, newExercise.class);
        startActivity(intent);
    }

}