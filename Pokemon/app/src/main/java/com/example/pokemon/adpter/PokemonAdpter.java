package com.example.pokemon.adpter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemon.R;
import com.example.pokemon.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdpter extends RecyclerView.Adapter<PokemonAdpter.PokemonHolder> {
    ArrayList<Pokemon> mList=new ArrayList<>();
    private Context mContext;

    public void setmList(ArrayList<Pokemon> mList) {
        this.mList = mList;
    }

    public PokemonAdpter(Context mContext) {

        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_items,parent,false);
        PokemonHolder PokemonHolder = new PokemonHolder(view);
        return PokemonHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
      holder.mTextview.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getUrl())
                .into(holder.imageView);
    }
public Pokemon getPokemon(int position){
        return mList.get(position);
}
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {

ImageView imageView;
TextView mTextview;
        public PokemonHolder(@NonNull View itemView) {
            super(itemView);
    imageView=itemView.findViewById(R.id.pokemon_image);
    mTextview=itemView.findViewById(R.id.pokemon_name);

        }
    }
}
