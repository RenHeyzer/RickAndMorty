package com.example.rickandmorda.network;

import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<Character>> fetchCharacters();

    @GET("api/character/{id}")
    Call<Character> fetchCharactersId(@Path("id") int id);
}
