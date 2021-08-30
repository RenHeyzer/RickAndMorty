package com.example.rickandmorda.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.databinding.ItemEpisodeBinding;
import com.example.rickandmorda.interfaces.OnItemClickListener;
import com.example.rickandmorda.models.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodesViewHolder> {

    public List<Episode> list = new ArrayList<>();
    public OnItemClickListener listener;
    private ItemEpisodeBinding binding;

    public void addEpisodesList(List<Episode> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public EpisodesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EpisodesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(EpisodesViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EpisodesViewHolder extends RecyclerView.ViewHolder {
        ItemEpisodeBinding binding;

        public EpisodesViewHolder(ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(Episode episode) {
            binding.name.setText(episode.getName());

            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClickListener(episode.getId());
            });
        }
    }
}
