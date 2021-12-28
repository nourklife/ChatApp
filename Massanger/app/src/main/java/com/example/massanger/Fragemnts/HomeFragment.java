package com.example.massanger.Fragemnts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.massanger.Adpter.AdpterUser;
import com.example.massanger.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class HomeFragment extends Fragment {
View view;
    private TabLayout mTab;

    private ViewPager viewPager;
    AdpterUser.TabAdpter tabAdpter;
    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_home, container, false);
        mTab = view.findViewById(R.id.tab);
        viewPager=view.findViewById(R.id.view_pager);


            tabAdpter=new AdpterUser.TabAdpter(getActivity().getSupportFragmentManager());
            tabAdpter.addFragment(new UsersFragment(),"Users");
            tabAdpter.addFragment(new ChatsFragment(),"CHATS");
            viewPager.setAdapter(tabAdpter);

        mTab.setupWithViewPager(viewPager);

        return view;
    }


}
