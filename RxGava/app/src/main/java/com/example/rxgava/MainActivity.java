package com.example.rxgava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer [] list=new Integer[9];
        list[0]=0;
        list[1]=5;
        list[2]=7;
        list[3]=9;
        list[4]=12;
        list[5]=10;
        list[6]=120;
        list[7]=70;
        list[8]=80;
Observable observable=Observable.fromArray(list);
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
Log.d(TAG,"onSubscribe :");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG,"onNext :" +o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError :"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete:");
            }
        };
        observable.subscribe(observer);
        //subscribe(i->  Log.d(TAG,"onCreate:student1:"  +i));
     //   try {
       //     Thread.sleep(3000);
      //  } catch (InterruptedException e) {
         //   e.printStackTrace();
       // }
      //  cold.subscribe(i-> Log.d(TAG,"onCreate:student2:" +i));

    }


    public void sleep(int i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
