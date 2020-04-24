package com.text.shui.fragment;

import androidx.fragment.app.Fragment;

public class CustomFragment extends Fragment {

    public static CustomFragment instance;
    private CustomFragment() {
    }

    public static CustomFragment getInstance(){
        if(instance == null){
            synchronized (CustomFragment.class){
                instance = new CustomFragment();
            }
        }
        return instance;
    }
}
