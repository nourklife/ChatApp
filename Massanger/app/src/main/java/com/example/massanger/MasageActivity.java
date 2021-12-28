package com.example.massanger;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.massanger.Adpter.MessageAdpter;
import com.example.massanger.model.Chat;
import com.example.massanger.model.User;
import com.google.firebase.auth.FirebaseAuth;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MasageActivity extends AppCompatActivity implements View.OnClickListener  {
     CircleImageView profileImage;
   TextView mUsername;
     ImageButton mBtn_send;
    EditText mText_send;
    FirebaseUser firebaseUser;
    RecyclerView recyclerView;
    MessageAdpter messageAdpter;
    List<Chat> mList;
    DatabaseReference reference;
    Intent intent;
    ValueEventListener eventListener;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masage);
           Toolbar toolbar=findViewById(R.id.toolbar);
           recyclerView=findViewById(R.id.rv_message);
           recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();

                }
            });
            profileImage =findViewById(R.id.profile);
            mUsername=findViewById(R.id.user_name);
            mBtn_send=findViewById(R.id.bet_send);
            mText_send=findViewById(R.id.text_send);
            intent=getIntent();



               mBtn_send.setOnClickListener(this);
         userid=intent.getStringExtra("userid");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);


                    assert user != null;
                    mUsername.setText(user.getUserName());

                 if (user.getImageUrl().equals("default")) {
                       profileImage.setImageResource(R.mipmap.ic_launcher);
                    } else {

                      Glide.with(getApplicationContext()).load(user.getImageUrl()).into(profileImage);
                   }
                   readMessage(firebaseUser.getUid(),userid,user.getImageUrl());

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            seenMassage(userid);
        }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bet_send:
                String msg = mText_send.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(firebaseUser.getUid(), userid, msg);
                } else {
                    Toast.makeText(MasageActivity.this, "please enter a meassage", Toast.LENGTH_LONG).show();
                }

                mText_send.setText("");
                break;
        }
    }
    private void sendMessage(String sender,String receiver,String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> map = new HashMap<>();
        map.put("sender", sender);
        map.put("receiver", receiver);
        map.put("message", message);
        map.put("isseen", false);
        reference.child("chats").push().setValue(map);

       final   DatabaseReference chatRef=FirebaseDatabase.getInstance().getReference("ChatList")
                .child(firebaseUser.getUid())
                .child(userid);
        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    chatRef.child("id").setValue(userid);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void readMessage(final String myid, final String userid, final String imageurl){
        mList=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(myid)&&chat.getSender().equals(userid)
                            ||chat.getReceiver().equals(userid)&&chat.getSender().equals(myid)){
                        mList.add(chat);
                        messageAdpter=new MessageAdpter(mList,MasageActivity.this,imageurl);
                        recyclerView.setAdapter(messageAdpter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void seenMassage(final String userid){
    reference=FirebaseDatabase.getInstance().getReference("chats");
    eventListener=reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                Chat chat=dataSnapshot.getValue(Chat.class);
                if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid)){
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("isseen",true);
                    dataSnapshot.getRef().updateChildren(hashMap);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        setStatus("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(eventListener);
        setStatus("offline");
    }

    private void setStatus(String status){
        reference=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("status",status);
        reference.updateChildren(hashMap);

    }



}

