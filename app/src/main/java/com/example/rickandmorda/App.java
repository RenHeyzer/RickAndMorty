package com.example.rickandmorda;

import android.app.Application;

import com.example.rickandmorda.data.db.RoomClient;
import com.example.rickandmorda.data.db.daos.CharacterDao;
import com.example.rickandmorda.data.db.daos.EpisodeDao;
import com.example.rickandmorda.data.db.daos.LocationDao;
import com.example.rickandmorda.data.network.RetrofitClient;
import com.example.rickandmorda.data.network.apiservices.CharacterApiService;
import com.example.rickandmorda.data.network.apiservices.EpisodeApiService;
import com.example.rickandmorda.data.network.apiservices.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static LocationApiService locationApiService;
    public static EpisodeApiService episodeApiService;
    public static CharacterDao characterDao;
    public static EpisodeDao episodeDao;
    public static LocationDao locationDao;

    @Override
    public void onCreate() {
        super.onCreate();

        RetrofitClient retrofitClient = new RetrofitClient();

        characterApiService = retrofitClient.provideCharacterApiService();
        locationApiService = retrofitClient.provideLocationApiService();
        episodeApiService = retrofitClient.provideEpisodeApiService();

        RoomClient roomClient = new RoomClient();

        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideDatabase(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideDatabase(this));
    }
}
