package com.example.deremate_android.ui.views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;


public class AvailableRoutes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_routes);

        ListView lista = findViewById(R.id.listaRutas);
        String[] rutas = {"Ruta 1", "Ruta 2", "Ruta 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rutas);
        lista.setAdapter(adapter);
    }
}

