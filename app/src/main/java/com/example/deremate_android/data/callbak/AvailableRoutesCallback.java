package com.example.deremate_android.data.callbak;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate_android.R;
import com.example.deremate_android.data.adapter.GenericRecyclerViewAdapter;
import com.example.deremate_android.data.model.AvailableRoute;
import com.example.deremate_android.ui.activities.AvailableRoutesActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableRoutesCallback implements Callback<List<AvailableRoute>> {

    private final Context context;
    private final RecyclerView recyclerView;
    private GenericRecyclerViewAdapter<AvailableRoute> adapter;

    public AvailableRoutesCallback(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public void onResponse(Call<List<AvailableRoute>> call, Response<List<AvailableRoute>> response) {
        if (response.isSuccessful() && response.body() != null) {

            List<AvailableRoute> routes = response.body();
            // Verificar si la lista de rutas está vacía
            if (routes.isEmpty()) {
                Log.d("AvailableRoutesCallback", "No tiene rutas disponibles");
            }

            adapter = new GenericRecyclerViewAdapter<>(
                    routes,
                    R.layout.fragment_card,
                    (itemView, availableRoute) -> {
                        TextView address = itemView.findViewById(R.id.address);
                        TextView name = itemView.findViewById(R.id.clientName);
                        TextView email = itemView.findViewById(R.id.clientEmail);

                        if (name == null || email == null || address == null) {
                            Log.e("AvailableRoutesCallback", "Uno o más TextViews no encontrados");
                            return;
                        }
                        else {
                            Log.d("AvailableRoutesCallback", "TextViews encontrados");
                        }

                        address.setText("Dirección: " + availableRoute.getAddress());
                        name.setText("Cliente: " + availableRoute.getClient_name() + " " + availableRoute.getClient_lastname());
                        Log.d("AvailableRoutesCallback", availableRoute.getClient_email());
                        email.setText("Email: " + availableRoute.getClient_email());
                    },

                    availableRoute -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Ubicacion del paquete");

                        String mensaje = "Sector del paquete: " + availableRoute.getPackage_sector() + "\n" +
                                "Estante: " + availableRoute.getPackage_estante() + "\n" +
                                "Columna: " + availableRoute.getPackage_columna_estante();

                        builder.setMessage(mensaje);
                        builder.setPositiveButton("Aceptar Ruta", null);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
            );
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        } else {
            Log.e("API", "Error en respuesta: " + response.code());
        }
    }

    @Override
    public void onFailure(Call<List<AvailableRoute>> call, Throwable t) {
        Log.e("API", "Error en la conexión", t);
    }
}
