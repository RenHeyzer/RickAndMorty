package com.example.rickandmorda.ui.fragments.characters;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorda.bases.BaseViewModel;
import com.example.rickandmorda.data.repositories.CharacterRepository;
import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

public class CharactersViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<Character>> fetchCharacters() {
        return characterRepository.fetchCharacters(page);
    }

    public MutableLiveData<Character> fetchCharactersId(int id) {
        return characterRepository.fetchCharactersId(id);
    }

    public List<Character> getCharacters() {
        return characterRepository.getCharacters();
    }
}
