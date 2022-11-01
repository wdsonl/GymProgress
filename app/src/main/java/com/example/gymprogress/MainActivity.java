package com.example.gymprogress;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gymprogress.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ImageButton peitoBt;
    private ImageButton costasBt;
    private ImageButton pernaBt;
    private ImageButton bracoBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        peitoBt = findViewById(R.id.peitoBt);
        peitoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent peito = new Intent(getApplicationContext(), PeitoActivity.class);
                startActivity(peito);
            }
        });

        costasBt = findViewById(R.id.costasBt);
        costasBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent costas = new Intent(getApplicationContext(), CostasActivity.class);
                startActivity(costas);
            }
        });

        pernaBt = findViewById(R.id.pernaBt);
        pernaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perna = new Intent(getApplicationContext(), PernaActivity.class);
                startActivity(perna);
            }
        });

        bracoBt = findViewById(R.id.bracoBt);
        bracoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent braco = new Intent(getApplicationContext(), BracoActivity.class);
                startActivity(braco);
            }
        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}