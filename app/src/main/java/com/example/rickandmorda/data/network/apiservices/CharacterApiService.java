package com.example.rickandmorda.data.network.apiservices;

import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickAndMortyResponse<Character>> fetchCharacters(@Query("page") int page);

    @GET("api/character/{id}")
    Call<Character> fetchCharactersId(@Path("id") int id);
}
