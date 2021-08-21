package com.example.rickandmorda.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorda.databinding.ItemCharacterBinding;
import com.example.rickandmorda.models.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewModel> {
    public ItemCharacterBinding binding;
    public List<Character> list = new ArrayList<>();
    public OnItemClickListener listener;

    public void addList(ArrayList<Character> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewModel(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewModel holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewModel extends RecyclerView.ViewHolder {


        public MyViewModel(View itemView) {
            super(itemView);
        }

        private void onBind(Character item) {
            Glide
                    .with(binding.imageContainer)
                    .load(item.getImage())
                    .into(binding.image);
            binding.title.setText(item.getName());

            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClick(item.getId());
            });
        }
    }
}
