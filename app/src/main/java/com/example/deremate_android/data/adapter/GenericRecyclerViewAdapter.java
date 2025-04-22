package com.example.deremate_android.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericRecyclerViewAdapter.GenericViewHolder> {

    private List<T> itemList;
    private final int itemLayoutId;
    private final GenericItemBinder<T> binder;
    private final OnItemClickListener<T> clickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }

    public GenericRecyclerViewAdapter(List<T> itemList,
                                      int itemLayoutId,
                                      GenericItemBinder<T> binder,
                                      OnItemClickListener<T> clickListener) {
        this.itemList = itemList;
        this.itemLayoutId = itemLayoutId;
        this.binder = binder;
        this.clickListener = clickListener;
    }

    public void setItemList(List<T> newList) {
        this.itemList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(itemLayoutId, parent, false);
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        T item = itemList.get(position);
        binder.bind(holder.itemView, item);
        holder.itemView.setOnClickListener(v -> clickListener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    static class GenericViewHolder extends RecyclerView.ViewHolder {
        public GenericViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //  Interfaz para bindear los datos
    public interface GenericItemBinder<T> {
        void bind(View itemView, T item);
    }

}
