package com.example.rickandmorda.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity
public class Episode {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("air_data")
    private String air_date;

    @SerializedName("episode")
    private String episode;

    @SerializedName("created")
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode1 = (Episode) o;
        return id == episode1.id && Objects.equals(name, episode1.name) && Objects.equals(air_date, episode1.air_date) && Objects.equals(episode, episode1.episode) && Objects.equals(created, episode1.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, air_date, episode, created);
    }
}
