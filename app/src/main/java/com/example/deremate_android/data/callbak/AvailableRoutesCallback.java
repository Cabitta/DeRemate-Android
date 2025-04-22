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
            Log.d("AvailableRoutesCallback", "Recibe response");

            // Depura la primera ruta para verificar datos
            if (!routes.isEmpty()) {
                AvailableRoute firstRoute = routes.get(0);
                Log.d("AvailableRoutesCallback", "Primera ruta: " +
                        firstRoute.getClient_name() + " " +
                        firstRoute.getClient_email());
            }

            adapter = new GenericRecyclerViewAdapter<>(
                    routes,
                    R.layout.fragment_card,
                    (itemView, availableRoute) -> {
                        Log.d("AvailableRoutesCallback", "Binding item: " + availableRoute.getClient_email());

                        TextView name = itemView.findViewById(R.id.clientName);
                        TextView email = itemView.findViewById(R.id.clientEmail);
                        TextView paquete = itemView.findViewById(R.id.packageInfo);

                        if (name == null || email == null || paquete == null) {
                            Log.e("AvailableRoutesCallback", "Uno o más TextViews no encontrados");
                            return;
                        }

                        name.setText("Cliente: " + availableRoute.getClient_name() + " " + availableRoute.getClient_lastname());
                        email.setText("Email: " + availableRoute.getClient_email());
                        paquete.setText("Package: " + availableRoute.getPackage_sector() + " Estante: " + availableRoute.getPackage_estante() + " Columna: " + availableRoute.getPackage_columna_estante());
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
