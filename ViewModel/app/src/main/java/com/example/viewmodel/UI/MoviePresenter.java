package com.example.viewmodel.UI;

import android.util.Log;

import com.example.viewmodel.pojo.Data;

public class MoviePresenter {
    MovieView view;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }
   Data data=new Data("NorShaban","1/1/2000",25);


    public void getMovieName(){

        view.onGetMovieName(data.getName());
    }

}
