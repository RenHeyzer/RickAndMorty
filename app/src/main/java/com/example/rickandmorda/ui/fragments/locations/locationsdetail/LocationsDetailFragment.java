package com.example.rickandmorda.ui.fragments.locations.locationsdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorda.bases.BaseFragment;
import com.example.rickandmorda.databinding.FragmentLocationsDetailBinding;
import com.example.rickandmorda.ui.fragments.locations.LocationViewModel;

public class LocationsDetailFragment extends BaseFragment<LocationViewModel, FragmentLocationsDetailBinding> {

    FragmentLocationsDetailBinding binding;
    LocationViewModel viewModel;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationsDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
    }

    @Override
    protected void setupRequests() {
        id = LocationsDetailFragmentArgs.fromBundle(getArguments()).getPosition();

        viewModel.fetchLocationsId(id).observe(getViewLifecycleOwner(), location -> {
            binding.name.setText(location.getName());
            binding.type.setText(location.getType());
            binding.dimension.setText(location.getDimension());
            binding.created.setText(location.getCreated());
        });
    }
}