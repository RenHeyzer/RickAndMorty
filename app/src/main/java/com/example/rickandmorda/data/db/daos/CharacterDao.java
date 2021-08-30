package com.example.rickandmorda.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rickandmorda.models.Character;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface CharacterDao {

    @Query("SELECT * FROM character")
    List<Character> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Character> results);
}
