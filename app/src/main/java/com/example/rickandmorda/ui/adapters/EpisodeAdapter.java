package com.example.rickandmorda.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.databinding.ItemEpisodeBinding;
import com.example.rickandmorda.models.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodesViewHolder> {
    public List<Episode> list = new ArrayList<>();
    ItemEpisodeBinding binding;

    public void addEpisodesList(ArrayList<Episode> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public EpisodesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EpisodesViewHolder(binding.getRoot());
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


        public EpisodesViewHolder(View itemView) {
            super(itemView);

        }

        private void onBind(Episode episode) {
            binding.name.setText(episode.getName());
            binding.airDate.setText(episode.getAir_date());
            binding.episode.setText(episode.getEpisode());
            binding.created.setText(episode.getCreated());
        }
    }
}
