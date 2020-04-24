package com.text.shui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.text.shui.R;
import com.text.shui.contants.DataContants;

import java.util.List;

public class BRVTextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private final List<Integer> drawables;

    public BRVTextAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
        drawables = DataContants.getLowRecyDrawable();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder
                .setText(R.id.tv_content,s)
                .setBackgroundResource(R.id.rv_img,drawables.get(baseViewHolder.getLayoutPosition()))
                .setBackgroundColor(R.id.tv_time,R.color.colorAccent)
                .setVisible(R.id.btn_hide,false)
                ;

    }
}
