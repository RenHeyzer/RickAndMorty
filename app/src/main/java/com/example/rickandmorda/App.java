package com.example.rickandmorda;

import android.app.Application;

import com.example.rickandmorda.network.CharacterApiService;
import com.example.rickandmorda.network.EpisodeApiService;
import com.example.rickandmorda.network.LocationApiService;
import com.example.rickandmorda.network.RetrofitClient;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static LocationApiService locationApiService;
    public static EpisodeApiService episodeApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        characterApiService = new RetrofitClient().provideCharacterApiService();
        locationApiService = new RetrofitClient().provideLocationApiService();
        episodeApiService = new RetrofitClient().provideEpisodeApiService();
    }
}
