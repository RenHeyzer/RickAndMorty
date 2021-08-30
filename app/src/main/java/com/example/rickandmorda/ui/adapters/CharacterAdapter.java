package com.example.rickandmorda.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorda.databinding.ItemCharacterBinding;
import com.example.rickandmorda.interfaces.OnItemClickListener;
import com.example.rickandmorda.models.Character;

public class CharacterAdapter extends ListAdapter<Character, CharacterAdapter.MyViewHolder> {

    public OnItemClickListener listener;

    public CharacterAdapter() {
        super(new CharacterDiffUtil());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CharacterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CharacterAdapter.MyViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(CharacterAdapter.MyViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public static class CharacterDiffUtil extends DiffUtil.ItemCallback<Character> {

        @Override
        public boolean areItemsTheSame(Character oldItem, Character newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(Character oldItem, Character newItem) {
            return oldItem == newItem;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemCharacterBinding binding;

        public MyViewHolder(ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(Character item) {
            Glide
                    .with(binding.image)
                    .load(item.getImage())
                    .into(binding.image);
            binding.title.setText(item.getName());

            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClickListener(item.getId());
            });
        }
    }
}
