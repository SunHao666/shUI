package com.text.shui.fragment;

import com.text.shui.base.BaseFragment;

public class TopLevelFragment extends BaseFragment {

    public static TopLevelFragment instance;
    private TopLevelFragment() {
    }

    public static TopLevelFragment getInstance(){
        if(instance == null){
            synchronized (TopLevelFragment.class){
                instance = new TopLevelFragment();
            }
        }
        return instance;
    }
}
