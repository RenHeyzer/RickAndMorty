package com.example.rickandmorda.ui.fragments.characters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorda.data.repositories.CharacterRepository;
import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

public class CharactersViewModel extends ViewModel {
    private final CharacterRepository characterRepository = new CharacterRepository();

    public MutableLiveData<RickAndMortyResponse<Character>> fetchCharacters() {
        return characterRepository.fetchCharacter();
    }

    public MutableLiveData<Character> fetchCharacterId(int id) {
        return characterRepository.fetchCharacterId(id);
    }

    public List<Character> getCharacters() {
        return characterRepository.getCharacters();
    }
}
