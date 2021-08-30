package com.example.rickandmorda.data.network.apiservices;

import com.example.rickandmorda.models.Location;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationApiService {

    @GET("api/location")
    Call<RickAndMortyResponse<Location>> fetchLocations(@Query("page") int page);

    @GET("api/location/{id}")
    Call<Location> fetchLocationsId(@Path("id") int id);
}
