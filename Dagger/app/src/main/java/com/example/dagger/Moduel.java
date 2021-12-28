package com.example.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class Moduel {


    @Provides
    Revier provideRiver(){
        return new Revier();

    }

}
