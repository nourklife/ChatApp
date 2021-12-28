package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdpter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentList=new ArrayList<>();
    ArrayList<String> mpageTitle=new ArrayList<>();
    public ViewPagerAdpter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }
    public void loadFragment(Fragment fragment,String pageTitle){
        fragmentList.add(fragment);
        mpageTitle.add(pageTitle);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return mpageTitle.get(position);
    }
}
