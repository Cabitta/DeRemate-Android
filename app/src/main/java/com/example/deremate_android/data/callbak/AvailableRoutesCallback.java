package com.example.deremate_android.data.callbak;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deremate_android.data.model.AvailableRoute;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableRoutesCallback implements Callback<List<AvailableRoute>> {

    private final Context context;
    private final ListView routesView;

    public AvailableRoutesCallback(Context context, ListView routesView) {
        this.context = context;
        this.routesView = routesView;
    }

    @Override
    public void onResponse(Call<List<AvailableRoute>> call, Response<List<AvailableRoute>> response) {
        if (response.isSuccessful() && response.body() != null) {
            List<AvailableRoute> routes = response.body();

            List<String> displayList = routes.stream()
                    .map(AvailableRoute::getAddress)
                    .collect(Collectors.toList());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    context,
                    android.R.layout.simple_list_item_1,
                    displayList
            );

            routesView.setAdapter(adapter);
        } else {
            Log.e("API", "Error en respuesta: " + response.code());
        }
    }

    @Override
    public void onFailure(Call<List<AvailableRoute>> call, Throwable t) {
        Log.e("API", "Error en la conexión", t);
    }
}
