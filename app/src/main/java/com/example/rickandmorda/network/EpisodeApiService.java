package com.example.rickandmorda.network;

import com.example.rickandmorda.models.Episode;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EpisodeApiService {
    @GET("api/episode")
    Call<RickAndMortyResponse<Episode>> fetchEpisodes();
}
