package com.example.pokemon.db;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class ModuelDB {
    @Provides
    @Singleton
    public static PokemonDB provide (Application application){
        return Room.databaseBuilder(application,PokemonDB.class,"favDataBase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
    @Provides
    @Singleton
    public static PokemonDaw provideDaw(PokemonDB pokemonDB){
        return pokemonDB.pokemonDaw();
    }
}
