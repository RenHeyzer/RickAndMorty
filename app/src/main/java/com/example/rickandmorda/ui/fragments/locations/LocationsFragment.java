package com.example.rickandmorda.ui.fragments.locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rickandmorda.App;
import com.example.rickandmorda.databinding.FragmentLocationsBinding;
import com.example.rickandmorda.ui.adapters.LocationAdapter;

public class LocationsFragment extends Fragment {
    FragmentLocationsBinding binding;
    LocationAdapter adapter = new LocationAdapter();
    LocationViewModel locationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        checkConnection();
    }

    private void checkConnection() {
        if (App.checkConnection(requireContext())) {
            setupRequests();
        } else {
            setupOffRequests();
        }
    }

    private void setupOffRequests() {
        adapter.addLocationsList(locationViewModel.getLocations());
    }

    private void setupRequests() {
        locationViewModel.fetchLocations().observe(getViewLifecycleOwner(), locationRickAndMortyResponse ->
                adapter.addLocationsList(locationRickAndMortyResponse.getResults()));
    }

    private void initialize() {
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        setupLocationRecycler();
    }

    private void setupLocationRecycler() {
        binding.locationsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.locationsRecycler.setAdapter(adapter);
    }
}