package com.example.rickandmorda.data.network.apiservices;

import com.example.rickandmorda.models.Episode;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeApiService {

    @GET("api/episode")
    Call<RickAndMortyResponse<Episode>> fetchEpisodes(@Query("page") int page);

    @GET("api/episode/{id}")
    Call<Episode> fetchEpisodesId(@Path("id") int id);
}
