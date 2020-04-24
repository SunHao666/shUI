package com.text.shui;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.text.shui.adapter.HomeTabAdapter;
import com.text.shui.fragment.CustomFragment;
import com.text.shui.fragment.LowLevelFragment;
import com.text.shui.fragment.MiddleLevelFragment;
import com.text.shui.fragment.TopLevelFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private HomeTabAdapter adapter;
    private List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFrament();
        initView();
    }

    private void initFrament() {
        fragments = new ArrayList<>();
        fragments.add(LowLevelFragment.getInstance());
        fragments.add(MiddleLevelFragment.getInstance());
        fragments.add(TopLevelFragment.getInstance());
        fragments.add(CustomFragment.getInstance());
    }

    private void initView() {
        Resources resources = getResources();
        String[] stringArray = resources.getStringArray(R.array.home_tab);
        for (int i = 0; i < stringArray.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(stringArray[i]));
        }
        adapter = new HomeTabAdapter(this, fragments,stringArray,getSupportFragmentManager());
        viewpager.setAdapter(adapter);

        tablayout.setupWithViewPager(viewpager);
    }
}
