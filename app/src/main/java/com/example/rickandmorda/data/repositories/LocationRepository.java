package com.example.rickandmorda.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorda.App;
import com.example.rickandmorda.models.Location;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {
    public MutableLiveData<RickAndMortyResponse<Location>> fetchLocations() {
        MutableLiveData<RickAndMortyResponse<Location>> data = new MutableLiveData<>();
        App.locationApiService.fetchLocations().enqueue(new Callback<RickAndMortyResponse<Location>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Location>> call, Response<RickAndMortyResponse<Location>> response) {
                App.locationDao.insertAll(response.body().getResults());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Location>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public List<Location> getLocations() {
        return App.locationDao.getAll();
    }
}
