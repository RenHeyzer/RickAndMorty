package com.example.rickandmorda.data.network.apiservices;

import com.example.rickandmorda.models.Location;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiService {

    @GET("api/location")
    Call<RickAndMortyResponse<Location>> fetchLocations();
}
