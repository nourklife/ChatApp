package com.example.dagger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class Cofee {
    private int Suger;
    private Revier revier;
    private int milk;
    @Inject
    Fram fram;
@Inject
    public Cofee( @suger int suger, Revier revier,@milk int milk) {
        Suger = suger;
        this.revier = revier;
        this.milk=milk;

    }

    @Inject

    public String getCofeeCub(){
    return fram.getBeens() + "with " +revier.getWater() + "suger : "+Suger + "+" +"milk :" +milk;
    }

}
