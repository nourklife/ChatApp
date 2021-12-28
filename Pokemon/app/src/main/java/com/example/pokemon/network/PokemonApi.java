package com.example.pokemon.network;


import com.example.pokemon.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public  interface PokemonApi {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemon();
}
