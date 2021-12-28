package com.example.massanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.massanger.Adpter.AdpterUser;
import com.example.massanger.Fragemnts.HomeFragment;
import com.example.massanger.Fragemnts.InfoFragment;
import com.example.massanger.Fragemnts.ProfileFragment;
import com.example.massanger.Fragemnts.SettingsFragment;
import com.example.massanger.Fragemnts.UsersFragment;
import com.example.massanger.Fragemnts.ChatsFragment;
import com.example.massanger.model.User;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    CircleImageView ProfileImage;
    TextView mUserName,mProfile,mSettings,mLogout,mInfo,mHome,mPageTitle;
    ImageView mOpenDrawer;
    DrawerLayout mDrawer;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initieviews();
        LoadFragment(new HomeFragment(),"Home");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
          User user= snapshot.getValue(User.class);
                assert user != null;
                mUserName.setText(user.getUserName());
          if (user.getImageUrl().equals("default")){
              ProfileImage.setImageResource(R.mipmap.ic_launcher);
          }
          else{
              Glide.with(getApplicationContext()).load(user.getImageUrl()).into(ProfileImage);
          }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initieviews() {
        ProfileImage=findViewById(R.id.profile_image);
        mUserName=findViewById(R.id.username);
        mProfile=findViewById(R.id.profile_img);
        mDrawer=findViewById(R.id.drawer);
        mPageTitle=findViewById(R.id.page_title);
        mInfo=findViewById(R.id.info);
        mSettings=findViewById(R.id.set);
        mOpenDrawer=findViewById(R.id.open);
        mLogout=findViewById(R.id.log);
        mHome=findViewById(R.id.home);
        mOpenDrawer.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mProfile.setOnClickListener(this);
        mLogout.setOnClickListener(this);
        mSettings.setOnClickListener(this);
        mInfo.setOnClickListener(this);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        setStatus("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        setStatus("offline");
    }

    private void setStatus(String status){
        databaseReference=FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
    HashMap<String,Object> hashMap=new HashMap<>();
    hashMap.put("status",status);
    databaseReference.updateChildren(hashMap);

}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.home:
           LoadFragment(new HomeFragment(),"Home");
                break;

            case R.id.set:
          LoadFragment(new SettingsFragment(),"Settings");
                break;
            case R.id.info:
            LoadFragment(new InfoFragment(),"Information");
                break;
            case R.id.profile_img:
              LoadFragment(new ProfileFragment(),"Profile");
                break;
            case R.id.log:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;

        }
    }
    public void LoadFragment(Fragment fragment,String pageTitle){
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.page_continer,fragment).commit();
        mPageTitle.setText(pageTitle);
        mDrawer.closeDrawer(Gravity.LEFT);


    }
}
