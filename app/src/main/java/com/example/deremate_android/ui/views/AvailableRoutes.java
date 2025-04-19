package com.example.deremate_android.ui.views;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.repository.RouteRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AvailableRoutes extends AppCompatActivity {

    @Inject
    RouteRepository routeRepository;
    private ListView rutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "⭐ onCreate");
        setContentView(R.layout.available_routes);

        rutas = findViewById(R.id.listaRutas);
        String[] ejemplo = {"Ruta 1", "Ruta 2", "Ruta 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ejemplo);
        rutas.setAdapter(adapter);
    }
}

