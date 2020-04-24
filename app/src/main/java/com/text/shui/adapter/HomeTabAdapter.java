package com.text.shui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页tab
 */
public class HomeTabAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> fragments = new ArrayList<>();
    public Context context;
    public String[] titles;
    public HomeTabAdapter(Context context, List<Fragment> fragments, String[] titles,FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = fragments;
        this.context = context;
        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
