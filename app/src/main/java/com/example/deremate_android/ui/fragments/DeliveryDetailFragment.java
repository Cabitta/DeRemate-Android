package com.example.deremate_android.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.deremate_android.R;
import com.example.deremate_android.data.model.DeliveryDetailItem;
import com.example.deremate_android.data.service.DeliveryDetailService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class DeliveryDetailFragment extends Fragment {
    @Inject
    DeliveryDetailService deliveryDetailService;

    private TextView clientNameText, addressText, statusText;
    private TextView qrCodeText, packageLocationText;
    private TextView datesText, deliveryTimeText;
    private ImageView backArrow;

    public static DeliveryDetailFragment newInstance(String agentId, String deliveryId) {
        DeliveryDetailFragment fragment = new DeliveryDetailFragment();
        Bundle args = new Bundle();
        args.putString("agentId", agentId);
        args.putString("deliveryId", deliveryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_detail, container, false);
        initViews(view);

        Bundle args = getArguments();
        if (args != null) {
            String agentId = args.getString("agentId");
            String deliveryId = args.getString("deliveryId");
            loadDeliveryDetail(agentId, deliveryId);
        }

        return view;
    }

    private void initViews(View view) {
        clientNameText = view.findViewById(R.id.clientName);
        addressText = view.findViewById(R.id.address);
        statusText = view.findViewById(R.id.status);
        qrCodeText = view.findViewById(R.id.qrCode);
        packageLocationText = view.findViewById(R.id.packageLocation);
        datesText = view.findViewById(R.id.dates);
        deliveryTimeText = view.findViewById(R.id.deliveryTime);
        backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void loadDeliveryDetail(String agentId, String deliveryId) {
        deliveryDetailService.getDeliveryDetail(agentId, deliveryId).enqueue(new Callback<DeliveryDetailItem>() {
            @Override
            public void onResponse(Call<DeliveryDetailItem> call, Response<DeliveryDetailItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    Toast.makeText(getContext(), "Error: No se encontraron los detalles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeliveryDetailItem> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(DeliveryDetailItem detail) {
        clientNameText.setText(detail.getClientFullName());
        addressText.setText(detail.getAddress());
        statusText.setText("Estado: " + detail.getStatus());
        qrCodeText.setText("QR: " + detail.getQrCode());

        DeliveryDetailItem.PackageLocation location = detail.getPackageLocation();
        String locationText = String.format("Ubicación: Sector %s, Estante %s, Columna %s",
                location.getSector(), location.getShelf(), location.getColumn());
        packageLocationText.setText(locationText);

        String dates = String.format("Inicio: %s\nFin: %s",
                detail.getInitDateTime().split("T")[0],
                detail.getEndDateTime().split("T")[0]);
        datesText.setText(dates);

        deliveryTimeText.setText("Tiempo de entrega: " + detail.getDeliveryTime());
    }
}