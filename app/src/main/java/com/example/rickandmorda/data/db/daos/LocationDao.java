package com.example.rickandmorda.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorda.models.Location;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Location> results);
}
