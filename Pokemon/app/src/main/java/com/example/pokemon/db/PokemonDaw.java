package com.example.pokemon.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemon.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDaw {
    @Insert
    public void insert(Pokemon pokemon);
    @Query("delete from fav_pokemon where name =:pokemonName")
    public void DeletePokemon(String pokemonName);
    @Query("select * from fav_pokemon")
    public LiveData<List<Pokemon>> getData();
}
