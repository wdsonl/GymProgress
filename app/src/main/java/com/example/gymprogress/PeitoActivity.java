package com.example.gymprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PeitoActivity extends AppCompatActivity {

    private Button voltarBt;
//    private EditText pesoJAVA;
//    private static String arquivo_peso = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peito);

        ListView list = findViewById(R.id.listExercises);
        final ArrayList<String> exercises = preencherDados();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercises);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (exercises.get(position).toString()){
                    case "benchpress":
                        Intent benchPress = new Intent(getApplicationContext(), BenchpressActivity.class);
                        startActivity(benchPress);
                    break;
                }
            };
        });


//        pesoJAVA = findViewById(R.id.editTextPeso);
        voltarBt = findViewById(R.id.voltarBt);
        voltarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences Peso = getSharedPreferences(arquivo_peso, 0);
//                SharedPreferences.Editor pesoedit = Peso.edit();
//
//                String Peso1 = pesoJAVA.getText().toString();
//                pesoedit.putString("peso", Peso1);
//                pesoedit.commit();
//                pesoJAVA.setText(Peso1);

                Intent voltar = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(voltar);
            }
        });

//        SharedPreferences Peso = getSharedPreferences(arquivo_peso, 0);
//        String Peso1 = Peso.getString("peso", "peso não definido");
//        pesoJAVA.setText(Peso1);

    }

    private ArrayList<String> preencherDados() {
        ArrayList<String> dados = new ArrayList<String>();
        dados.add("benchpress");
        dados.add("b");
        dados.add("c");
        dados.add("d");
        return dados;
    }

}