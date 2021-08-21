package com.example.rickandmorda.network;

import com.example.rickandmorda.models.Location;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiService {

    @GET("api/location")
    Call<RickAndMortyResponse<Location>> fetchLocations();
}
