package com.example.rickandmorda.ui.fragments.characters;

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
import com.example.rickandmorda.databinding.FragmentCharactersBinding;
import com.example.rickandmorda.ui.adapters.CharacterAdapter;

public class CharactersFragment extends BaseFragment<CharactersViewModel, FragmentCharactersBinding> {

    CharactersViewModel viewModel;
    FragmentCharactersBinding binding;
    CharacterAdapter adapter = new CharacterAdapter();
    private LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
        setupCharacterRecycler();
    }

    private void setupCharacterRecycler() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.charactersRecycler.setLayoutManager(linearLayoutManager);
        binding.charactersRecycler.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            if (checkConnection()) {
                Navigation.findNavController(CharactersFragment.this.requireActivity(), R.id.nav_host_fragment)
                        .navigate(CharactersFragmentDirections.
                                actionCharactersFragmentToDetailFragment().setPosition(position));
            } else {
                Toast.makeText(CharactersFragment.this.getContext(), R.string.internet_dialog_title, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void setupRequests() {
        if (!fetchCharacters()) {
            adapter.submitList(viewModel.getCharacters());
        }

        binding.charactersRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        viewModel.page++;
                        fetchCharacters();
                    }
                }
            }
        });
    }

    private boolean fetchCharacters() {
        if (checkConnection()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterRickAndMortyResponse -> {
                adapter.submitList(characterRickAndMortyResponse.getResults());
            });
            return true;
        } else {
            return false;
        }
    }
}