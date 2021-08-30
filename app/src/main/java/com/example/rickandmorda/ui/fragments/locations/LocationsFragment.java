package com.example.rickandmorda.ui.fragments.locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorda.R;
import com.example.rickandmorda.bases.BaseFragment;
import com.example.rickandmorda.databinding.FragmentLocationsBinding;
import com.example.rickandmorda.ui.adapters.LocationAdapter;

public class LocationsFragment extends BaseFragment<LocationViewModel, FragmentLocationsBinding> {
    FragmentLocationsBinding binding;
    LocationAdapter adapter = new LocationAdapter();
    LocationViewModel locationViewModel;
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        setupLocationRecycler();
    }

    private void setupLocationRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.locationsRecycler.setLayoutManager(linearLayoutManager);
        binding.locationsRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            if (checkConnection()) {
                Navigation.findNavController(LocationsFragment.this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(LocationsFragmentDirections.
                                actionLocationsFragmentToLocationsDetailFragment().setPosition(position));
            } else {
                Toast.makeText(LocationsFragment.this.getContext(), R.string.internet_dialog_title, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void setupRequests() {
        if (!fetchLocations()) {
            adapter.addLocationsList(locationViewModel.getLocations());
        }

        binding.locationsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        locationViewModel.page++;
                        fetchLocations();
                    }
                }
            }
        });
    }

    private boolean fetchLocations() {
        if (checkConnection()) {
            locationViewModel.fetchLocations().observe(getViewLifecycleOwner(), locationRickAndMortyResponse -> {
                adapter.addLocationsList(locationRickAndMortyResponse.getResults());
            });
            return true;
        } else {
            return false;
        }
    }
}