package com.text.shui.lowui.style;

import android.view.WindowManager;

import com.text.shui.R;
import com.text.shui.base.BaseActivity;

public class SHStyleActivity extends BaseActivity {

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        //window透明度
//        WindowManager.LayoutParams lp=getWindow().getAttributes();
//        lp.alpha=0.5f;
//        getWindow().setAttributes(lp);
        //window昏暗度
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.dimAmount=0.5f;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public int getlayout() {
        return R.layout.activity_style;
    }
}
