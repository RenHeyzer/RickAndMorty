package com.example.rickandmorda.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.databinding.ItemLocationBinding;
import com.example.rickandmorda.interfaces.OnItemClickListener;
import com.example.rickandmorda.models.Location;

public class LocationAdapter extends ListAdapter<Location, LocationAdapter.LocationViewHolder> {

    public OnItemClickListener listener;

    public LocationAdapter() {
        super(new LocationDiffUtil());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(LocationAdapter.LocationViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public static class LocationDiffUtil extends DiffUtil.ItemCallback<Location> {

        @Override
        public boolean areItemsTheSame(Location oldItem, Location newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(Location oldItem, Location newItem) {
            return oldItem == newItem;
        }
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public LocationViewHolder(ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(Location location) {
            binding.name.setText(location.getName());

            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClickListener(location.getId());
            });
        }
    }
}
