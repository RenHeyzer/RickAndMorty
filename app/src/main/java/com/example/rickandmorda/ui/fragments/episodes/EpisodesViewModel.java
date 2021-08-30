package com.example.rickandmorda.ui.fragments.episodes;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorda.bases.BaseViewModel;
import com.example.rickandmorda.data.repositories.EpisodeRepository;
import com.example.rickandmorda.models.Episode;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

public class EpisodesViewModel extends BaseViewModel {

    private final EpisodeRepository episodeRepository = new EpisodeRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<Episode>> fetchEpisodes() {
        return episodeRepository.fetchEpisodes(page);
    }

    public MutableLiveData<Episode> fetchEpisodesId(int id) {
        return episodeRepository.fetchEpisodesId(id);
    }

    public List<Episode> getEpisodes() {
        return episodeRepository.getEpisodes();
    }
}
