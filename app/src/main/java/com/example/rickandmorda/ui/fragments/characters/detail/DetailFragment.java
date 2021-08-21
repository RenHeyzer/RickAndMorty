package com.example.rickandmorda.ui.fragments.characters.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.rickandmorda.databinding.FragmentDetailBinding;
import com.example.rickandmorda.ui.fragments.characters.CharactersViewModel;

public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;
    CharactersViewModel viewModel;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        getDetailData();
        setupRequest();
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
    }

    private void getDetailData() {
        id = DetailFragmentArgs.fromBundle(getArguments()).getPosition();
    }

    private void setupRequest() {
        viewModel.fetchCharacterId(id).observe(getViewLifecycleOwner(), character -> {
            Glide
                    .with(binding.image)
                    .load(character.getImage())
                    .into(binding.image);
            binding.title.setText(character.getName());
            binding.status.setText(character.getStatus());
            binding.species.setText(character.getSpecies());
            binding.type.setText(character.getType());
            binding.gender.setText(character.getGender());
            binding.created.setText(character.getCreated());
        });
    }
}