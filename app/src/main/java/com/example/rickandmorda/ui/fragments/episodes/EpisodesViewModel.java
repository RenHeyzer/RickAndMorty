package com.example.rickandmorda.ui.fragments.episodes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorda.App;
import com.example.rickandmorda.data.repositories.EpisodeRepository;
import com.example.rickandmorda.models.Episode;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodesViewModel extends ViewModel {
    EpisodeRepository episodeRepository = new EpisodeRepository();
    public MutableLiveData<RickAndMortyResponse<Episode>> fetchEpisode() {
        return episodeRepository.fetchEpisode();
    }

    public List<Episode> getEpisodes() {
        return episodeRepository.getEpisodes();
    }
}
