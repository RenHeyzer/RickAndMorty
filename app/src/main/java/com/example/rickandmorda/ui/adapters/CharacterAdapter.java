package com.example.rickandmorda.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorda.databinding.ItemCharacterBinding;
import com.example.rickandmorda.interfaces.OnItemClickListener;
import com.example.rickandmorda.models.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewModel> {

    public List<Character> list = new ArrayList<>();
    public OnItemClickListener listener;
    private ItemCharacterBinding binding;

    public void addList(List<Character> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewModel(binding);
    }

    @Override
    public void onBindViewHolder(MyViewModel holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder {
        ItemCharacterBinding binding;

        public MyViewModel(ItemCharacterBinding binding) {
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
