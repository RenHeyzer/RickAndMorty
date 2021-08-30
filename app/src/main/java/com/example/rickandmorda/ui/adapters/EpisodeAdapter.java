package com.example.rickandmorda.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.databinding.ItemEpisodeBinding;
import com.example.rickandmorda.interfaces.OnItemClickListener;
import com.example.rickandmorda.models.Episode;

public class EpisodeAdapter extends ListAdapter<Episode, EpisodeAdapter.EpisodesViewHolder> {

    public OnItemClickListener listener;

    public EpisodeAdapter() {
        super(new EpisodeDiffUtil());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public EpisodeAdapter.EpisodesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EpisodeAdapter.EpisodesViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(EpisodeAdapter.EpisodesViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public static class EpisodeDiffUtil extends DiffUtil.ItemCallback<Episode> {

        @Override
        public boolean areItemsTheSame(Episode oldItem, Episode newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(Episode oldItem, Episode newItem) {
            return oldItem == newItem;
        }
    }

    public class EpisodesViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;

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
