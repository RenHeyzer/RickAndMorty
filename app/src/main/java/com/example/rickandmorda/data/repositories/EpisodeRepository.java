package com.example.rickandmorda.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorda.App;
import com.example.rickandmorda.models.Episode;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {
    
    public MutableLiveData<RickAndMortyResponse<Episode>> fetchEpisodes(int page) {
        MutableLiveData<RickAndMortyResponse<Episode>> data = new MutableLiveData<>();
        App.episodeApiService.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<Episode>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Episode>> call, Response<RickAndMortyResponse<Episode>> response) {
                if (response.body() != null) {
                    App.episodeDao.insertAll(response.body().getResults());
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Episode>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Episode> fetchEpisodesId(int id) {
        MutableLiveData<Episode> episodeData = new MutableLiveData<>();
        App.episodeApiService.fetchEpisodesId(id).enqueue(new Callback<Episode>() {
            @Override
            public void onResponse(Call<Episode> call, Response<Episode> response) {
                episodeData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Episode> call, Throwable t) {
                episodeData.setValue(null);
            }
        });
        return episodeData;
    }

    public List<Episode> getEpisodes() {
        return App.episodeDao.getAll();
    }
}
