package com.example.rickandmorda.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.databinding.ItemLocationBinding;
import com.example.rickandmorda.interfaces.OnItemClickListener;
import com.example.rickandmorda.models.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    public OnItemClickListener listener;
    public List<Location> list = new ArrayList<>();
    private ItemLocationBinding binding;

    public void addLocationsList(List<Location> getList) {
        list.addAll(getList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LocationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        ItemLocationBinding binding;

        public LocationViewHolder(ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Location location) {
            binding.name.setText(location.getName());

            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClickListener(location.getId());
            });
        }
    }
}
