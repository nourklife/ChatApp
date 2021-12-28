package com.example.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    Cofee cofee,cofee2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      CofeeCompoens compoens=((MainAplication)getApplication()).getCompoens();
      compoens.inject(this);
     String o= cofee.getCofeeCub()+"the farm of coffe1 is : "+cofee.fram+"the farm of cofee2 is :"+cofee2.fram;
        Log.d(TAG,"the drink divide into :"+o);
    }
}
