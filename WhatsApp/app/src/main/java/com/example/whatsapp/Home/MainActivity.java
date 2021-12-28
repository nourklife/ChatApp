package com.example.whatsapp.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp.Home.HomeFragment;
import com.example.whatsapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
DrawerLayout mDrawer;
    ImageView mOpenDrawer;
    TextView mHome,mSettings,mLog,mFavourate,mAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawer=findViewById(R.id.drawer);
        mOpenDrawer=findViewById(R.id.open_drwer);
        mHome=findViewById(R.id.hom);
        mFavourate=findViewById(R.id.fav);
        mAbout=findViewById(R.id.info);
        mLog=findViewById(R.id.log);
        mSettings=findViewById(R.id.set);
        mHome.setOnClickListener(this);
        mOpenDrawer.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_drwer:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.hom:
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.page_contnier,new HomeFragment()).commit();
                break;
        }
    }
}
