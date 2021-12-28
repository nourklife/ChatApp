package com.example.facbook.UI.MAIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.PostAdpter;
import com.example.facbook.Pojo.FaceModel;
import com.example.facbook.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PostViewModel postViewModel;
    ArrayList<FaceModel> mList = new ArrayList<>();
    PostAdpter postAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts();
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(postAdpter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        postViewModel.viewMutableDataLive.observe(this, new Observer<List<FaceModel>>() {
            @Override
            public void onChanged(List<FaceModel> faceModels) {
                postAdpter.setmList((ArrayList<FaceModel>) faceModels);
            }
        });
    }
}
