package com.text.shui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Fragment基类
 */
public class MiddleLevelFragment extends Fragment {

    public Context mContenxt;

    public static MiddleLevelFragment instance;
    private MiddleLevelFragment() {
    }

    public static MiddleLevelFragment getInstance(){
        if(instance == null){
            synchronized (MiddleLevelFragment.class){
                instance = new MiddleLevelFragment();
            }
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
