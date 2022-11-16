package com.example.gymprogress;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ExibePeito extends AppCompatActivity {
   private ListView listPeito;
   private ArrayAdapter adapter;
   private static ArrayList<exercise> exibepeito;
   private exerciseDB db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new exerciseDB((ExibePeito) getApplicationContext());

        listPeito = findViewById(R.id.listpeito);
        exibepeito = db.showallexercises();
        adapter = new ArrayAdapter<exercise>(getApplicationContext(), android.R.layout.simple_list_item_1, exibepeito);

        listPeito.setAdapter(adapter);
    }
}
