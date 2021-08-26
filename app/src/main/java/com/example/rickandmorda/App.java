package com.example.rickandmorda;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.rickandmorda.data.db.RoomClient;
import com.example.rickandmorda.data.db.daos.CharacterDao;
import com.example.rickandmorda.data.db.daos.EpisodeDao;
import com.example.rickandmorda.data.db.daos.LocationDao;
import com.example.rickandmorda.data.network.apiservices.CharacterApiService;
import com.example.rickandmorda.data.network.apiservices.EpisodeApiService;
import com.example.rickandmorda.data.network.apiservices.LocationApiService;
import com.example.rickandmorda.data.network.apiservices.RetrofitClient;

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
        characterApiService = new RetrofitClient().provideCharacterApiService();
        locationApiService = new RetrofitClient().provideLocationApiService();
        episodeApiService = new RetrofitClient().provideEpisodeApiService();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideDatabase(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideDatabase(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideDatabase(this));
    }

    public static Boolean checkConnection(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
