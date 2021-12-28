package com.example.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokemon.adpter.PokemonAdpter;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.viewmodel.ViewModelRepository;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ViewModelRepository viewModel;
    private PokemonAdpter pokemonAdpter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView_pokemon);
        pokemonAdpter = new PokemonAdpter(this);
        recyclerView.setAdapter(pokemonAdpter);
        button=findViewById(R.id.fav);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FavPokemon.class));

            }
        });
        swipPokemon();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        viewModel = new ViewModelProvider(this).get(ViewModelRepository.class);
        viewModel.getPokemon();
        viewModel.getMutableLiveData().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemon) {

                pokemonAdpter.setmList(pokemon);
            }
        });
    }

    public void swipPokemon() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pokemonIndex = viewHolder.getAdapterPosition();
             Pokemon pokemon=   pokemonAdpter.getPokemon(pokemonIndex);
             viewModel.Insert(pokemon);
             pokemonAdpter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"it added to favoiurate ",Toast.LENGTH_LONG).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
