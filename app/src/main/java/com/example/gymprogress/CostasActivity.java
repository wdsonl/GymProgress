package com.example.gymprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class CostasActivity extends AppCompatActivity {

    private Button voltarBt;

    //listview para exibir a lista de contatos
    private ListView mylist;

    //adapter da listView
    private ArrayAdapter adapter;

    //array para a lista de contatos
    private static ArrayList<exercise> showList;

    private exerciseDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costas);

        voltarBt = findViewById(R.id.voltarBt);
        voltarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voltar = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(voltar);
            }
        });

        db = new exerciseDB(this);

        mylist = (ListView) findViewById(R.id.listViewXML);
        //chama o método findAll que devolve um array e guarda em exibeLista
        showList = db.showallexercises();
        //criação de uma instância de um ListAdapter utilizando um layout nativo
        adapter = new ArrayAdapter<exercise>(this, android.R.layout.simple_list_item_1, showList);

        //associação a ListView com o adapter
        mylist.setAdapter(adapter);
    }
}