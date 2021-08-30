package com.example.rickandmorda.ui.fragments.episodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.R;
import com.example.rickandmorda.bases.BaseFragment;
import com.example.rickandmorda.databinding.FragmentEpisodesBinding;
import com.example.rickandmorda.ui.adapters.EpisodeAdapter;

public class EpisodesFragment extends BaseFragment<EpisodesViewModel, FragmentEpisodesBinding> {

    FragmentEpisodesBinding binding;
    EpisodesViewModel episodesViewModel;
    EpisodeAdapter adapter = new EpisodeAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        episodesViewModel = new ViewModelProvider(this).get(EpisodesViewModel.class);
        setupEpisodeRecycler();
    }

    private void setupEpisodeRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.episodesRecycler.setLayoutManager(linearLayoutManager);
        binding.episodesRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            if (checkConnection()) {
                Navigation.findNavController(EpisodesFragment.this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(EpisodesFragmentDirections.
                                actionEpisodesFragmentToEpisodesDetailFragment().setPosition(position));
            } else {
                Toast.makeText(EpisodesFragment.this.getContext(), R.string.internet_dialog_title, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void setupRequests() {
        if (!fetchEpisodes()) {
            adapter.addEpisodesList(episodesViewModel.getEpisodes());
        }

        binding.episodesRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        episodesViewModel.page++;
                        fetchEpisodes();
                    }
                }
            }
        });
    }

    private boolean fetchEpisodes() {
        if (checkConnection()) {
            episodesViewModel.fetchEpisodes().observe(getViewLifecycleOwner(), episodeRickAndMortyResponse -> {
                adapter.addEpisodesList(episodeRickAndMortyResponse.getResults());
            });
            return true;
        } else {
            return false;
        }
    }
}