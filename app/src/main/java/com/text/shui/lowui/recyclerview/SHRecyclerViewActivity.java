package com.text.shui.lowui.recyclerview;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.tabs.TabLayout;
import com.text.shui.R;
import com.text.shui.adapter.BRVTextAdapter;
import com.text.shui.adapter.LowReyAdapter;
import com.text.shui.base.BaseActivity;
import com.text.shui.contants.DataContants;
import com.text.shui.contants.SHContants;

import java.util.List;

import butterknife.BindView;

/**
 * RecyclerView 使用
 */
public class SHRecyclerViewActivity extends BaseActivity {
    @BindView(R.id.rv_tab)
    TabLayout rvTab;
    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;
    @BindView(R.id.rv3)
    RecyclerView rv3;
    @BindView(R.id.rv4)
    RecyclerView rv4;
    private List<String> content;
    private List<Integer> drawables;
    private LowReyAdapter adapterLine;
    private LowReyAdapter adapterGrid;

    private int LINEAR = 0;
    private int GRID = 1;
    private int STAGGEREDGRID = 2;
    private int CUSTOM = 3;
    private BRVTextAdapter adapterBRV;

    @Override
    public int getlayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void initView() {
        setTitle("Recyclerview");
        content = DataContants.getLowRecyData();
        drawables = DataContants.getLowRecyDrawable();
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv2.setLayoutManager(new GridLayoutManager(this,2));
        rv3.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv4.setLayoutManager(new LinearLayoutManager(this));

        adapterLine = new LowReyAdapter(this,content,drawables, SHContants.MANAGER_LINE);
        adapterGrid = new LowReyAdapter(this,content,drawables, SHContants.MANAGER_GRID);
        adapterBRV = new BRVTextAdapter(R.layout.adapter_low_rey_line,content);

        rv1.setAdapter(adapterLine);
        rv2.setAdapter(adapterGrid);
        rv3.setAdapter(adapterGrid);
        rv4.setAdapter(adapterBRV);

        DividerItemDecoration dividerV = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration dividerH = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        rv1.addItemDecoration(dividerV);
        rv2.addItemDecoration(dividerV);
        rv4.addItemDecoration(dividerV);
        String[] stringArray = getResources().getStringArray(R.array.low_recycler_list);
        for (int i = 0; i < stringArray.length; i++) {
            rvTab.addTab(rvTab.newTab().setText(stringArray[i]));
        }
        showView(LINEAR);
        initListener();
    }

    private void initListener() {
        rvTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 showView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        adapterBRV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Toast.makeText(SHRecyclerViewActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        adapterBRV.setOnItemChildClickListener(new OnItemChildClickListener() {
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                int id = view.getId();
                Log.e("brc","view id=="+id);
            }
        });
    }

    private void showView(int position) {
        int rv1Visibility = View.GONE,rv2Visibility = View.GONE,rv3Visibility = View.GONE
                ,rv4Visibility = View.GONE;
        if(position == LINEAR){
            rv1Visibility = View.VISIBLE;
        }else if(position == GRID){
            rv2Visibility = View.VISIBLE;
        }else if(position == STAGGEREDGRID){
            rv3Visibility = View.VISIBLE;
        }else if(position == CUSTOM){
            rv4Visibility = View.VISIBLE;
        }
        rv1.setVisibility(rv1Visibility);
        rv2.setVisibility(rv2Visibility);
        rv3.setVisibility(rv3Visibility);
        rv4.setVisibility(rv4Visibility);
    }

    @Override
    public void initData() {

    }
}
