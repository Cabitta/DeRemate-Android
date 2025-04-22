package com.example.deremate_android.ui.fragments;


import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate_android.R;
import com.example.deremate_android.data.adapter.DeliveryAdapter;
import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.model.DeliveryHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.deremate_android.data.service.DeliveryHistoryService;

public class DeliveryListFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<DeliveryHistory> deliveryList;
    private DeliveryAdapter deliveryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        TextView emptyMessage = view.findViewById(R.id.emptyMessage);

        SharedPreferences prefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String deliveryId = prefs.getString("delivery_id", null);


        ImageView backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> requireActivity().onBackPressed());

        deliveryAdapter = new DeliveryAdapter(List.of(), delivery -> {
            Fragment detailFragment = DeliveryDetailFragment.newInstance(
                    delivery.getRouteId()
            );
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(deliveryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DeliveryHistoryService service = ApiClient.getClient().create(DeliveryHistoryService.class);
        Call<List<DeliveryHistory>> call = service.getDeliveryHistory(deliveryId);

        call.enqueue(new Callback<List<DeliveryHistory>>() {
            @Override
            public void onResponse(Call<List<DeliveryHistory>> call, Response<List<DeliveryHistory>> response) {
                if (response.isSuccessful()) {
                    List<DeliveryHistory> deliveries = response.body();

                    if (deliveries != null && !deliveries.isEmpty()) {
                        recyclerView.setVisibility(View.VISIBLE);
                        emptyMessage.setVisibility(View.GONE);
                        deliveryAdapter.setDeliveryList(deliveries);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        emptyMessage.setVisibility(View.VISIBLE);
                    }

                } else {
                    Log.e("API", "Error en respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DeliveryHistory>> call, Throwable t) {
                Log.e("API", "Error en la conexión", t);
            }
        });

        return view;
    }
}
