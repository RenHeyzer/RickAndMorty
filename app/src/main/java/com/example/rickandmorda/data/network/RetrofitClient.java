package com.example.rickandmorda.data.network;

import com.example.rickandmorda.data.network.apiservices.CharacterApiService;
import com.example.rickandmorda.data.network.apiservices.EpisodeApiService;
import com.example.rickandmorda.data.network.apiservices.LocationApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(provideLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private final Retrofit provideRetrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public CharacterApiService provideCharacterApiService() {
        return provideRetrofit.create(CharacterApiService.class);
    }

    public LocationApiService provideLocationApiService() {
        return provideRetrofit.create(LocationApiService.class);
    }

    public EpisodeApiService provideEpisodeApiService() {
        return provideRetrofit.create(EpisodeApiService.class);
    }
}
