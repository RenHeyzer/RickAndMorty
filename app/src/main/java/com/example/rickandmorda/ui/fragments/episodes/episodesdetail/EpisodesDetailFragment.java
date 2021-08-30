package com.example.rickandmorda.ui.fragments.episodes.episodesdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorda.bases.BaseFragment;
import com.example.rickandmorda.databinding.FragmentEpisodesDetailBinding;
import com.example.rickandmorda.ui.fragments.episodes.EpisodesViewModel;

public class EpisodesDetailFragment extends BaseFragment<EpisodesViewModel, FragmentEpisodesDetailBinding> {

    FragmentEpisodesDetailBinding binding;
    EpisodesViewModel viewModel;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodesDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodesViewModel.class);
    }

    @Override
    protected void setupRequests() {
        id = EpisodesDetailFragmentArgs.fromBundle(getArguments()).getPosition();

        viewModel.fetchEpisodesId(id).observe(getViewLifecycleOwner(), episode -> {
            binding.name.setText(episode.getName());
            binding.episode.setText(episode.getEpisode());
            binding.created.setText(episode.getCreated());
        });
    }
}