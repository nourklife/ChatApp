package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mTital,mDiscrption;
    RecyclerView mRecyclerView;
    AppCompatButton mSave;
    ArrayList<NotesModel> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTital=findViewById(R.id.tital);
        mDiscrption=findViewById(R.id.desc);
        mRecyclerView=findViewById(R.id.rv);
        mSave=findViewById(R.id.save);
        mSave.setOnClickListener(this);
        NotesAdpter notesAdpter=new NotesAdpter(mList,MainActivity.this);
        mRecyclerView.setAdapter(notesAdpter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:

        break;
        }
    }
}