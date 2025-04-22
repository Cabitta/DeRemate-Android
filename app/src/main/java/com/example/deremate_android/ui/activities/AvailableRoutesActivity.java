package com.example.deremate_android.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deremate_android.R;
import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.model.AvailableRoute;
import com.example.deremate_android.data.model.DeliveryHistory;
import com.example.deremate_android.data.repository.RouteRepository;
import com.example.deremate_android.data.service.AvailableRoutesService;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class AvailableRoutesActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";

    private AvailableRoutesService service = ApiClient.getClient().create(AvailableRoutesService.class);

    @Inject
    RouteRepository routeRepository;

    private ListView routesView;

    private List<String> routesDisplayList;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "⭐ onCreate");
        setContentView(R.layout.available_routes);

        if (savedInstanceState == null) {
            ImageView backArrow = findViewById(R.id.backArrow);
            backArrow.setOnClickListener(v -> finish());

            Log.d("AvailableRoutesActivity", "Se crea la actividad por primera vez"); // Debug line
            Call<List<AvailableRoute>> call = service.getAvailableRoutes("3f606fe38e646890030881ef");
            routesView = findViewById(R.id.listaRutas);
            call.enqueue(new Callback<List<AvailableRoute>>() {
                @Override
                public void onResponse(Call<List<AvailableRoute>> call, Response<List<AvailableRoute>> response) {
                    if (response.isSuccessful()) {
                        List<AvailableRoute> availableRoutes = response.body();
                        routesDisplayList = availableRoutes
                                .stream()
                                .map(AvailableRoute::getAddress)
                                .collect(Collectors.toList());
                        adapter = new ArrayAdapter<>(AvailableRoutesActivity.this,
                                android.R.layout.simple_list_item_1,
                                routesDisplayList);
                        routesView.setAdapter(adapter);
                    } else {
                        Log.e("API", "Error en respuesta: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<AvailableRoute>> call, Throwable t) {
                    Log.e("API", "Error en la conexión", t);
                }
            });
            /*

            routesView = findViewById(R.id.listaRutas); // Inicializar rutas

            if (routeRepository != null) {
                List<AvailableRoute> availableRoutes = routeRepository.getAllAvailableRoutes();
                routesDisplayList = availableRoutes
                        .stream()
                        .map(route -> route.getId() + " - " + route.getAddress())
                        .collect(Collectors.toList());

                adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        routesDisplayList);
                routesView.setAdapter(adapter);
            } else {
                Log.e("AvailableRoutesActivity", "RouteRepository is null");
            }

             */
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "⭐ onStart: La Activity está a punto de hacerse visible");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "⭐ onResume: La Activity es visible y tiene el foco");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "⭐ onPause: La Activity está perdiendo el foco");
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "⭐ onStop: La Activity ya no es visible");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "⭐ onRestart: La Activity está volviendo a empezar después de detenerse");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "⭐ onDestroy: La Activity está siendo destruida");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "⭐ onSaveInstanceState: Guardando el estado de la Activity");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "⭐ onRestoreInstanceState: Restaurando el estado guardado de la Activity");
    }
}

