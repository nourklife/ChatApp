package com.example.massanger.Fragemnts;

import android.media.session.MediaSession;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.massanger.Adpter.AdpterUser;
import com.example.massanger.R;
import com.example.massanger.model.Chat;
import com.example.massanger.model.ChatList;
import com.example.massanger.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;


public class ChatsFragment extends Fragment {
    RecyclerView recyclerView;
    private AdpterUser adpterUser;
    private List<User> userList;
    private List<ChatList> mList;
    FirebaseUser firebaseUser;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.rv_chats);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("ChatList").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ChatList chatList=dataSnapshot.getValue(ChatList.class);
                    mList.add(chatList);
                }
                chatlist();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }



    private void chatlist() {
        userList=new ArrayList<>();
        reference=FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    for (ChatList chatList:mList){
                        if (user.getId().equals(chatList.getId())){
                            userList.add(user);
                        }
                    }
                }
                adpterUser=new AdpterUser(userList,getContext(),true);
                recyclerView.setAdapter(adpterUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
