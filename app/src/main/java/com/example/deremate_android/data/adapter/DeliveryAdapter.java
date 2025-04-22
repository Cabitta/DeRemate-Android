package com.example.deremate_android.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate_android.R;
import com.example.deremate_android.data.model.DeliveryHistory;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private List<DeliveryHistory> deliveryList;

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DeliveryHistory delivery);
    }

    public DeliveryAdapter(List<DeliveryHistory> deliveryList, OnItemClickListener listener) {
        this.deliveryList = deliveryList;
        this.listener = listener;
    }

    public void setDeliveryList(List<DeliveryHistory> newList) {
        this.deliveryList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery, parent, false);
        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        DeliveryHistory delivery = deliveryList.get(position);
        holder.bind(delivery, listener);
    }

    @Override
    public int getItemCount() {
        return deliveryList != null ? deliveryList.size() : 0;
    }

    static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView timeTextView;
        private final TextView dateTextView;
        private final ImageView arrowImage;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.clientName);
            timeTextView = itemView.findViewById(R.id.deliveryTime);
            dateTextView = itemView.findViewById(R.id.endDate);
            arrowImage = itemView.findViewById(R.id.arrowIcon);
        }

        public void bind(final DeliveryHistory delivery, final OnItemClickListener listener) {
            nameTextView.setText(delivery.getClient_name() + " " + delivery.getClient_lastname());
            timeTextView.setText("Tiempo de Entrega: " + delivery.getDelivery_time());
            dateTextView.setText(delivery.getEnd_date_time().split("T")[0]);
            itemView.setOnClickListener(v -> listener.onItemClick(delivery));
        }
    }
}