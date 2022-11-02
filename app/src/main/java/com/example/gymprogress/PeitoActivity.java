package com.example.gymprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PeitoActivity extends AppCompatActivity {

    private Button voltarBt;

    private TextView pesoJAVA;
    private Button alterarJAVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peito);

        voltarBt = findViewById(R.id.voltarBt);
        voltarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        alterarJAVA = findViewById(R.id.alterarXML);
        pesoJAVA = findViewById(R.id.pesoXML);

        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int botao = v.getId();

                int valornovo = 0;

                switch (botao){

                    case R.id.alterarXML:
                        //intent, entra em outra activity e troca
                        valornovo = 100;
                        break;

                }

                pesoJAVA.setText(Integer.toString(valornovo));
            }
        };

        alterarJAVA.setOnClickListener(handler);

    }
}