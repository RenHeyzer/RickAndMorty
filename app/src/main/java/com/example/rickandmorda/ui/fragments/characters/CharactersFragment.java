package com.example.rickandmorda.ui.fragments.characters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        setupRequests();
    }

    private void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
        setupCharacterRecycler();
    }

    private void setupCharacterRecycler() {
        binding.charactersRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.charactersRecycler.setAdapter(adapter);
        
        adapter.setOnItemClickListener(position -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(CharactersFragmentDirections.
                            actionCharactersFragmentToDetailFragment().setPosition(position));
        });
    }

    private void setupRequests() {
        viewModel.fetchCharacter().observe(getViewLifecycleOwner(), characterRickAndMortyResponse -> {
            adapter.addList(characterRickAndMortyResponse.getResults());
        });
    }
}