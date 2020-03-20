package com.ssq.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Author : Mr.Shen
 * Date : 2019/10/26 11:47
 * Description :
 */
public class TopRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    //布局标识集合
    private final List<Integer> typeList;

    public TopRecyclerViewAdapter(Context context, List<Integer> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    //设置常量
    private static final int TYPE_IMG_THREE = 2;
    private static final int TYPE_IMG_RECYCLER = 3;
    private static final int TYPE_IMG_RECYCLER_ONE = 1;
    private static final int TYPE_IMG_RECYCLER_FOUR = 4;

    /**
     * 根据不同的position，设置不同的ViewType
     * position表示当前是第几个Item，通过position拿到当前的Item对象，然后判断这个item对象需要那种视图
     */
    @Override
    public int getItemViewType(int position) {
        switch (typeList.get(position)) {
            case 2:
                return TYPE_IMG_THREE;
            case 3:
                return TYPE_IMG_RECYCLER;
            case 1:
                return TYPE_IMG_RECYCLER_ONE;
            case 4:
                return TYPE_IMG_RECYCLER_FOUR;
        }
        return 0;
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder(当RecyclerView需要一个ViewHolder时会回调该方法，如果有可复用的View不会回调)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_IMG_THREE:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_three, parent, false);
                ThreeViewHolder threeViewHolder = new ThreeViewHolder(view);
                return threeViewHolder;
            case TYPE_IMG_RECYCLER:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_hrecyclerview, parent, false);
                RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view1);
                return recyclerViewHolder;
            case TYPE_IMG_RECYCLER_ONE:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_one, parent, false);
                RecycleViewHolderOne recycleViewHolderOne = new RecycleViewHolderOne(view2);
                return recycleViewHolderOne;
            case TYPE_IMG_RECYCLER_FOUR:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_four, parent, false);
                RecycleViewHolderFour recycleViewHolderFour = new RecycleViewHolderFour(view3);
                return recycleViewHolderFour;
        }
        return null;
    }

    //填充onCreateViewHolder方法返回的holder中的控件(当一个View需要出现在屏幕上时，该方法会被回调，我们需要再该方法中根据数据来更改视图)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ThreeViewHolder) {
            setFloorTwo((ThreeViewHolder) holder, position);
        } else if (holder instanceof RecyclerViewHolder) {
            setFloorThree((RecyclerViewHolder) holder);
        } else if (holder instanceof RecycleViewHolderOne) {
            setOnePic((RecycleViewHolderOne) holder);
        } else if (holder instanceof RecycleViewHolderFour) {
            setFourPic((RecycleViewHolderFour) holder);
        }
    }

    //获取数据的数量(告诉RecyclerView有多少个视图需要显示)
    @Override
    public int getItemCount() {
        return typeList.size();
    }

    private void setOnePic(RecycleViewHolderOne holderOne) {
        holderOne.tvTitle.setText("显示一张图片 type = 1");
//        holderOne.mIvShowOne.setImageResource(R.color.colorAccent);
    }

    //设置二楼数据（显示3张图片）
    private void setFloorTwo(ThreeViewHolder holder, int position) {
        holder.mTvTitle.setText("这里显示三张图片 type = 2");
    }

    //设置三楼数据（显示N张图片）
    private void setFloorThree(RecyclerViewHolder holder) {
        holder.mTvTitle.setText("横向滑动的View type = 3");
        setHRecyclerView(holder.mHRecyclerview);
    }

    private void setFourPic(RecycleViewHolderFour holderFour) {
//        holderFour.mIvOne.setImageResource(R.color.colorAccent);
        holderFour.mTvTitle.setText("这里显示两张图片 type = 4");
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
                Toast.makeText(context, "你点击了" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //三张图片
    public class ThreeViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;
        private ImageView mIvOne;
        private ImageView mIvTwo;
        private ImageView mIvThree;

        public ThreeViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvOne = itemView.findViewById(R.id.iv_one);
            mIvTwo = itemView.findViewById(R.id.iv_two);
            mIvThree = itemView.findViewById(R.id.iv_three);
        }
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

    public class RecycleViewHolderOne extends RecyclerView.ViewHolder {

        private ImageView mIvShowOne;
        private TextView tvTitle;

        public RecycleViewHolderOne(@NonNull View itemView) {
            super(itemView);
            mIvShowOne = itemView.findViewById(R.id.iv_show_one);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

    public class RecycleViewHolderFour extends RecyclerView.ViewHolder {

        private ImageView mIvOne;
        private ImageView mIvTwo;
        private TextView mTvTitle;

        public RecycleViewHolderFour(@NonNull View itemView) {
            super(itemView);
            mIvOne = itemView.findViewById(R.id.iv_one);
            mIvTwo = itemView.findViewById(R.id.iv_two);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
