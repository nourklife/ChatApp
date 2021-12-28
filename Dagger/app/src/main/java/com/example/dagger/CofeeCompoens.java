package com.example.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
@Singleton
@Component(modules = Moduel.class)
public interface CofeeCompoens {
    Cofee getCofee();
    void inject(MainActivity mainActivity);
    @Component.Builder
    public interface bulider{
        @BindsInstance
        bulider Suger(@suger int suger);
        @BindsInstance
        bulider milk(@milk int milk);
        CofeeCompoens build();
    }
}
