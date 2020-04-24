package com.text.shui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.text.shui.R;
import com.text.shui.contants.SHContants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LowReyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> content;
    private List<Integer> drawales;
    private int itemType;

    public LowReyAdapter(Context context, List<String> content, List<Integer> drawales, int itemType) {
        this.content = content;
        this.context = context;
        this.drawales = drawales;
        this.itemType = itemType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == SHContants.MANAGER_LINE) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_low_rey_line, parent, false);
            return new LineViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_low_rey_grid, parent, false);
            return new GridViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LineViewHolder) {
            ((LineViewHolder) holder).tvContent.setText(content.get(position));
            ((LineViewHolder) holder).rvImg.setBackgroundResource(drawales.get(position));
        } else if (holder instanceof GridViewHolder) {
            ((GridViewHolder) holder).tvContent.setText(content.get(position));
            ((GridViewHolder) holder).rvImg.setBackgroundResource(drawales.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return drawales.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemType == SHContants.MANAGER_LINE) {
            return SHContants.MANAGER_LINE;
        } else if (itemType == SHContants.MANAGER_GRID) {
            return SHContants.MANAGER_GRID;
        }
        return super.getItemViewType(position);
    }

    public class LineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_img)
        ImageView rvImg;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public LineViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_img)
        ImageView rvImg;
        @BindView(R.id.tv_content)
        TextView tvContent;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
