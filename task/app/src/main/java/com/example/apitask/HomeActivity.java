package com.example.apitask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<String> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView=findViewById(R.id.rv);
        Item_Adpter item_adpter=new Item_Adpter(mList,HomeActivity.this);
        recyclerView.setAdapter(item_adpter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(HomeActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }
}
