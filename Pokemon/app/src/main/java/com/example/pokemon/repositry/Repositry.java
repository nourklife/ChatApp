package com.example.pokemon.repositry;

import androidx.lifecycle.LiveData;

import com.example.pokemon.db.PokemonDaw;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.PokemonResponse;
import com.example.pokemon.network.PokemonApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repositry {
    private PokemonApi pokemonApi;
    private PokemonDaw pokemonDaw;
@Inject
    public Repositry(PokemonApi pokemonApi,PokemonDaw pokemonDaw) {
        this.pokemonApi = pokemonApi;
        this.pokemonDaw=pokemonDaw;
    }
    public Observable<PokemonResponse> getPokemon(){
        return pokemonApi.getPokemon();
    }
    public void Insert(Pokemon pokemon){pokemonDaw.insert(pokemon);}
    public void Delete(String pokemonName){pokemonDaw.
            DeletePokemon(pokemonName);
}
    public LiveData<List<Pokemon>>getFavPokemon(){return pokemonDaw.getData();}
}
