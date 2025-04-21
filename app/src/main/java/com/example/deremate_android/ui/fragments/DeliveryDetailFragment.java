package com.example.deremate_android.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.deremate_android.R;
import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.model.DeliveryDetailItem;
import com.example.deremate_android.data.service.DeliveryDetailService;
import androidx.fragment.app.Fragment;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class DeliveryDetailFragment extends Fragment {
    // Views
    private TextView clientNameText, addressText, statusText;
    private TextView qrCodeText, packageLocationText;
    private TextView datesText, deliveryTimeText;
    private ImageView backArrow;

    // Datos necesarios para la llamada API
    private String deliveryId;
    private String agentId;

    public static DeliveryDetailFragment newInstance() {
        return new DeliveryDetailFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_detail, container, false);

        // Usar valores por defecto si no hay argumentos
        deliveryId = "507f1f77bcf86cd799439011"; // ID hardcodeado del backend
        agentId = "123"; // ID de agente hardcodeado

        // Obtener IDs del Bundle si existen
        Bundle args = getArguments();
        if (args != null) {
            deliveryId = args.getString("deliveryId", deliveryId);
            agentId = args.getString("agentId", agentId);
        }

        initViews(view);
        loadDeliveryDetail();

        return view;
    }


    private void initViews(View view) {
        // Inicializar vistas
        clientNameText = view.findViewById(R.id.clientName);
        addressText = view.findViewById(R.id.address);
        statusText = view.findViewById(R.id.status);
        qrCodeText = view.findViewById(R.id.qrCode);
        packageLocationText = view.findViewById(R.id.packageLocation);
        datesText = view.findViewById(R.id.dates);
        deliveryTimeText = view.findViewById(R.id.deliveryTime);

        // Configurar flecha atrás
        backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void loadDeliveryDetail() {
        // Crear instancia del servicio
        DeliveryDetailService service = ApiClient.getClient().create(DeliveryDetailService.class);

        // Llamar al endpoint
        service.getDeliveryDetail(agentId, deliveryId).enqueue(new Callback<DeliveryDetailItem>() {
            @Override
            public void onResponse(Call<DeliveryDetailItem> call, Response<DeliveryDetailItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                }
            }

            @Override
            public void onFailure(Call<DeliveryDetailItem> call, Throwable t) {
                // Manejar error
                Toast.makeText(getContext(), "Error al cargar los detalles", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(DeliveryDetailItem detail) {
        // Actualizar vistas con los datos
        clientNameText.setText(detail.getClientFullName());
        addressText.setText(detail.getAddress());
        statusText.setText("Estado: " + detail.getStatus());
        qrCodeText.setText("QR: " + detail.getQrCode());

        // Mostrar ubicación del paquete
        String location = String.format("Ubicación: Sector %s, Estante %s, Columna %s",
                detail.getPackageLocation().getSector(),
                detail.getPackageLocation().getShelf(),
                detail.getPackageLocation().getColumn());
        packageLocationText.setText(location);

        // Mostrar fechas
        datesText.setText(String.format("Inicio: %s\nFin: %s",
                detail.getInitDateTime(),
                detail.getEndDateTime()));

        deliveryTimeText.setText("Tiempo de entrega: " + detail.getDeliveryTime());
    }
}