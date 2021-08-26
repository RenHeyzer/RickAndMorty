package com.example.rickandmorda.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorda.models.Episode;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EpisodeDao {
    @Query("SELECT * FROM episode")
    List<Episode> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Episode> results);
}
