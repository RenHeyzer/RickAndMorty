package com.example.rickandmorda.ui.fragments.characters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rickandmorda.App;
import com.example.rickandmorda.R;
import com.example.rickandmorda.databinding.FragmentCharactersBinding;
import com.example.rickandmorda.ui.adapters.CharacterAdapter;


public class CharactersFragment extends Fragment {
    CharactersViewModel viewModel;
    FragmentCharactersBinding binding;
    CharacterAdapter adapter = new CharacterAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);
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
        adapter.addList(viewModel.getCharacters());
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
        setupCharacterRecycler();
    }

    private void setupCharacterRecycler() {
        binding.charactersRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.charactersRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            if (App.checkConnection(requireContext())) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(CharactersFragmentDirections.
                                actionCharactersFragmentToDetailFragment().setPosition(position));
            } else {
                Toast.makeText(getContext(), R.string.internet_dialog_title, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRequests() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterRickAndMortyResponse -> {
            adapter.addList(characterRickAndMortyResponse.getResults());
        });
    }
}