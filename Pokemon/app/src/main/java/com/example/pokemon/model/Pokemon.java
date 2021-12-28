package com.example.pokemon.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_pokemon")
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }
}

