package com.example.pokemon.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.PokemonResponse;
import com.example.pokemon.repositry.Repositry;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModelRepository extends ViewModel {
    private Repositry repositry;

    @ViewModelInject

    public ViewModelRepository(Repositry repositry) {
        this.repositry = repositry;
    }

    MutableLiveData<ArrayList<Pokemon>> mutableLiveData = new MutableLiveData<>();
    LiveData<List<Pokemon>> favList = null;


    public LiveData<List<Pokemon>> getList() {
        return favList;
    }

    public MutableLiveData<ArrayList<Pokemon>> getMutableLiveData() {
        return mutableLiveData;
    }


    @SuppressLint("CheckResult")
    public void getPokemon() {
        repositry.getPokemon()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {
                        ArrayList<Pokemon> list = pokemonResponse.getResults();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> mutableLiveData.setValue(result), error -> Log.e("viewModel", error.getMessage()));
    }

    public void Insert(Pokemon pokemon) {
        repositry.Insert(pokemon);
    }

    public void DeletePokemon(String pokemon) {
        repositry.Delete(pokemon);
    }

    public void getFavList() {
        favList = repositry.getFavPokemon();
    }

}
