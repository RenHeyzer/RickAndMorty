package com.example.rickandmorda.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorda.App;
import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    public MutableLiveData<RickAndMortyResponse<Character>> fetchCharacters(int page) {

        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<Character>>() {

            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAll(response.body().getResults());
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public MutableLiveData<Character> fetchCharactersId(int id) {
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

    public List<Character> getCharacters() {
        return App.characterDao.getAll();
    }
}
