package com.example.whatsapp.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsapp.Fragment.CallsFragment;
import com.example.whatsapp.Fragment.ChatFragment;
import com.example.whatsapp.Fragment.StatusFragment;
import com.example.whatsapp.R;
import com.example.whatsapp.ViewPagerAdpter;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
View view;
TabLayout mTab;
ViewPager mViewpager;
ViewPagerAdpter viewPagerAdpter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        mTab=view.findViewById(R.id.tab);
        mViewpager=view.findViewById(R.id.view_pager);
        viewPagerAdpter=new ViewPagerAdpter(getActivity().getSupportFragmentManager());
        viewPagerAdpter.loadFragment(new ChatFragment(),"CHAT");
        viewPagerAdpter.loadFragment(new CallsFragment(),"CALLS");
        viewPagerAdpter.loadFragment(new StatusFragment(),"STATUS");
        mViewpager.setAdapter(viewPagerAdpter);
        mTab.setupWithViewPager(mViewpager);
        return view;

    }
}
