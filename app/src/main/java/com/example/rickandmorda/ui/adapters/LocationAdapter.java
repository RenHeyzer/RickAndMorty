package com.example.rickandmorda.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.databinding.ItemLocationBinding;
import com.example.rickandmorda.models.Location;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    ItemLocationBinding binding;
    ArrayList<Location> list = new ArrayList<>();

    public void addLocationsList(ArrayList<Location> getList) {
        list = getList;
        notifyDataSetChanged();
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LocationViewHolder(binding.getRoot());
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
        public LocationViewHolder(View itemView) {
            super(itemView);
        }

        public void onBind(Location location) {
            binding.name.setText(location.getName());
            binding.type.setText(location.getType());
            binding.dimension.setText(location.getDimension());
            binding.created.setText(location.getCreated());
        }
    }
}
