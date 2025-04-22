package com.example.deremate_android.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate_android.R;
import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.callbak.AvailableRoutesCallback;
import com.example.deremate_android.data.model.AvailableRoute;
import com.example.deremate_android.data.repository.RouteRepository;
import com.example.deremate_android.data.service.AvailableRoutesService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;

@AndroidEntryPoint
public class AvailableRoutesActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";

    private AvailableRoutesService service = ApiClient.getClient().create(AvailableRoutesService.class);

    @Inject
    RouteRepository routeRepository;
    private RecyclerView recyclerView;
    private ListView routesView;
    private Call<List<AvailableRoute>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "⭐ onCreate");
        setContentView(R.layout.activity_available_routes);

        if (savedInstanceState == null) {
            Log.d("AvailableRoutesActivity", "Se crea la actividad por primera vez"); // Debug line
            recyclerView = findViewById(R.id.recycler_view); // Inicializar RecyclerView de rutas

            // Volver a la actividad anterior
            ImageView backArrow = findViewById(R.id.backArrow);
            backArrow.setOnClickListener(v -> finish());

            call = service.getAvailableRoutes("3f606fe38e646890030881ef"); // TODO: CAMBIAR A DINAMICO
            call.enqueue(new AvailableRoutesCallback(this, recyclerView));
        }
        else {
            Log.d("AvailableRoutesActivity", "Se recupera la actividad desde el estado guardado"); // Debug line
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

