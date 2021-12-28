package com.example.dagger;

import android.app.Application;

public class MainAplication extends Application {
    private CofeeCompoens compoens;
    @Override
    public void onCreate() {
        super.onCreate();
        compoens=DaggerCofeeCompoens.builder().Suger(7).milk(5).build();
    }

    public CofeeCompoens getCompoens() {
        return compoens;
    }
}
