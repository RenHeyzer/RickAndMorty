package com.example.rickandmorda.ui.fragments.characters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorda.App;
import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersViewModel extends ViewModel {
    MutableLiveData<RickAndMortyResponse<Character>> fetchCharacter() {
        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacters().enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Character> fetchCharacterId(int id) {
        MutableLiveData<Character> characterData = new MutableLiveData<>();
        App.characterApiService.fetchCharactersId(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                characterData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                characterData.setValue(null);
            }
        });
        return characterData;
    }

}
