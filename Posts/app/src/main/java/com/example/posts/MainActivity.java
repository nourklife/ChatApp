package com.example.posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
RecyclerView recyclerView;
EditText mTitle,mBody;
Button mGet,mInsert;
    PostAdpter postAdpter=new PostAdpter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv);
        mTitle=findViewById(R.id.title);
        mBody=findViewById(R.id.body);
        mGet=findViewById(R.id.get);
        mInsert=findViewById(R.id.insert);
        recyclerView.setAdapter(postAdpter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        final PostDatabase postDatabase=PostDatabase.getInstance(this);
        mInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDatabase.postDaw().insert(new Post(2,mTitle.getEditableText().toString(),mBody.getEditableText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                       Log.d(TAG,"onCompleteA7A");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("error",e.getMessage());
                            }
                        });
            }
        });
        mGet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        postDatabase.postDaw().getPost().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Post> posts) {
                        postAdpter.setmList((ArrayList<Post>) posts);
                        postAdpter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,"get data succesed",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
});


    }




    }

