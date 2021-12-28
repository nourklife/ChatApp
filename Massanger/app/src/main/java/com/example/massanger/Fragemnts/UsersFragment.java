package com.example.massanger.Fragemnts;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.massanger.Adpter.AdpterUser;
import com.example.massanger.R;
import com.example.massanger.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UsersFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
     AdpterUser adpterUser;
     EditText mSearch;
   private List<User> users;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        users =new ArrayList<>();;
      view= inflater.inflate(R.layout.fragment_users, container, false);
      recyclerView=view.findViewById(R.id.Rc_user);
      mSearch=view.findViewById(R.id.search);
      mSearch.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              searchUser(s.toString().toLowerCase());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
      recyclerView.setHasFixedSize(true);
      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
      recyclerView.setLayoutManager(linearLayoutManager);
      redUsers();
     return view;
    }

    private void searchUser(String s) {
        final FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        Query  query =FirebaseDatabase.getInstance().getReference("Users").orderByChild("username")
                .startAt(s)
                .endAt(s+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mSearch.getText().toString().equals("")) {
                    users.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        assert firebaseUser != null;
                        assert user != null;
                        if (!user.getId().equals(firebaseUser.getUid())) {
                            users.add(user);
                        }
                    }
                    adpterUser = new AdpterUser(users, getContext(), false);
                    recyclerView.setAdapter(adpterUser);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void redUsers() {
        final String firebaseUser= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             users.clear();

             for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                 User user=dataSnapshot.getValue(User.class);


                     assert user != null;
                 Log.v("anything",user.getId());
                 if (!user.getId().equals(firebaseUser)){

                     users.add(user);
                 }


             }
                adpterUser=new AdpterUser(users,getContext(),false);
                recyclerView.setAdapter(adpterUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}
