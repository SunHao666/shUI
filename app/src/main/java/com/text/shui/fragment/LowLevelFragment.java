package com.text.shui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.text.shui.R;
import com.text.shui.adapter.HomeListAdapter;
import com.text.shui.base.BaseFragment;
import com.text.shui.listener.SHOnItemClickListener;
import com.text.shui.lowui.recyclerview.SHRecyclerViewActivity;
import com.text.shui.lowui.style.SHStyleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基础UI
 */
public class LowLevelFragment extends BaseFragment {

    @BindView(R.id.rv_low)
    RecyclerView rvLow;

    public static LowLevelFragment instance;
    private List<String> data;

    private Class[] clazz = {SHStyleActivity.class, SHRecyclerViewActivity.class};
    private LowLevelFragment() {
    }

    public static LowLevelFragment getInstance() {
        if (instance == null) {
            synchronized (LowLevelFragment.class) {
                instance = new LowLevelFragment();
            }
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        String[] homeList = getResources().getStringArray(R.array.home_list);
        for (int i = 0; i < homeList.length; i++) {
            data.add(homeList[i]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_low, null);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {

        HomeListAdapter adapter = new HomeListAdapter(getActivity(),data);
        rvLow.setAdapter(adapter);

        rvLow.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter.setSHOnItemClickListener(new SHOnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),data.get(position),Toast.LENGTH_SHORT).show();
                onClickEvent(position);
            }
        });
    }

    private void onClickEvent(int position) {
       Intent intent = new Intent(getActivity(),clazz[position]);
       startActivity(intent);
    }
}
