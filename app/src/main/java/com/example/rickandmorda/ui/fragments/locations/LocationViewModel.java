package com.example.rickandmorda.ui.fragments.locations;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorda.App;
import com.example.rickandmorda.data.repositories.LocationRepository;
import com.example.rickandmorda.models.Location;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {
    LocationRepository locationRepository = new LocationRepository();

    public MutableLiveData<RickAndMortyResponse<Location>> fetchLocations() {
        return locationRepository.fetchLocations();
    }

    public List<Location> getLocations() {
        return locationRepository.getLocations();
    }
}
