package com.example.rickandmorda.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rickandmorda.data.db.daos.CharacterDao;
import com.example.rickandmorda.data.db.daos.EpisodeDao;
import com.example.rickandmorda.data.db.daos.LocationDao;
import com.example.rickandmorda.models.Character;
import com.example.rickandmorda.models.Episode;
import com.example.rickandmorda.models.Location;

@Database(entities = {Character.class, Episode.class, Location.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract EpisodeDao episodeDao();
    public abstract LocationDao locationDao();
}
