package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mTitel,mDesc;
    RecyclerView mRcucleview;
    Button mSave;
    DatabaseReference databaseReference;
    ArrayList<NotesModel> List =new ArrayList<>();
    NotesAdpter notesAdpter =new NotesAdpter(List,MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitel=findViewById(R.id.title);
        mDesc=findViewById(R.id.Desc);
        mSave=findViewById(R.id.save);
        mRcucleview=findViewById(R.id.RV);
        databaseReference= FirebaseDatabase.getInstance().getReference("notes");
        mRcucleview.setAdapter(notesAdpter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        mRcucleview.setLayoutManager(layoutManager);
        mSave.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                Insert();
                Toast.makeText(MainActivity.this,"Notes are saved",Toast.LENGTH_LONG).show();
                break;
        }

    }
    void Insert(){
        String notekey=databaseReference.push().getKey();
        String Title=mTitel.getText().toString();
        String Desc=mDesc.getText().toString();
        NotesModel notesModel=new NotesModel(notekey,Title,Desc);
        databaseReference.child(notekey).setValue(notesModel);
    }
}
