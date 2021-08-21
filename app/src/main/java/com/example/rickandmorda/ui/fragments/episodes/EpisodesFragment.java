package com.example.rickandmorda.ui.fragments.episodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rickandmorda.databinding.FragmentEpisodesBinding;
import com.example.rickandmorda.ui.adapters.EpisodeAdapter;

public class EpisodesFragment extends Fragment {
    FragmentEpisodesBinding binding;
    EpisodesViewModel episodesViewModel;
    EpisodeAdapter adapter = new EpisodeAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupRequests();
    }

    private void setupRequests() {
        episodesViewModel.fetchEpisode().observe(getViewLifecycleOwner(), episodeRickAndMortyResponse -> {
            adapter.addEpisodesList(episodeRickAndMortyResponse.getResults());
        });
    }

    private void initialize() {
        episodesViewModel = new ViewModelProvider(this).get(EpisodesViewModel.class);
        setupEpisodeRecycler();
    }

    private void setupEpisodeRecycler() {
        binding.episodesRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.episodesRecycler.setAdapter(adapter);
    }
}