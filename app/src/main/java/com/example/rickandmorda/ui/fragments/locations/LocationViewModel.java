package com.example.rickandmorda.ui.fragments.locations;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorda.bases.BaseViewModel;
import com.example.rickandmorda.data.repositories.LocationRepository;
import com.example.rickandmorda.models.Location;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

public class LocationViewModel extends BaseViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<Location>> fetchLocations() {
        return locationRepository.fetchLocations(page);
    }

    public MutableLiveData<Location> fetchLocationsId(int id) {
        return locationRepository.fetchLocationsId(id);
    }

    public List<Location> getLocations() {
        return locationRepository.getLocations();
    }
}
