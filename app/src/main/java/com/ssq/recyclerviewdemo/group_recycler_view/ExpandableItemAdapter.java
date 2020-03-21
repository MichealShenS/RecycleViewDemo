package com.ssq.recyclerviewdemo.group_recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssq.recyclerviewdemo.HRecyclerViewAdapter;
import com.ssq.recyclerviewdemo.R;
import com.ssq.recyclerviewdemo.ToastUtil;

/**
 * Author : Mr.Shen
 * Date : 2020/3/21 16:00
 * Description :
 */
public class ExpandableItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    public ExpandableItemAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_hrecyclerview, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view1);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setFloorThree((RecyclerViewHolder) holder);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    //设置三楼数据（显示N张图片）
    private void setFloorThree(RecyclerViewHolder holder) {
        holder.mTvTitle.setText("横向滑动的View type = 3");
        setHRecyclerView(holder.mHRecyclerview);
    }

    private void setHRecyclerView(RecyclerView hRecyclerView) {
        HRecyclerViewAdapter hRecyclerViewAdapter = new HRecyclerViewAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        hRecyclerView.setLayoutManager(linearLayoutManager);
        hRecyclerView.setHasFixedSize(false);
        hRecyclerView.setAdapter(hRecyclerViewAdapter);
        hRecyclerViewAdapter.setOnItemClickListener(new HRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.showShort("你点击了" + (position + 1) + "条");
            }
        });
    }

    //横向的RecyclerView
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;
        private RecyclerView mHRecyclerview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
            mHRecyclerview = itemView.findViewById(R.id.h_recyclerview);
        }
    }
}
