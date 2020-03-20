package com.ssq.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author : Mr.Shen
 * Date : 2019/10/26 12:41
 * Description :
 */
public class HRecyclerViewAdapter extends RecyclerView.Adapter<HRecyclerViewAdapter.HImageHolder> {

    private final Context context;

    private OnItemClickListener onItemClickListener;

    public HRecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public HImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hrecyclerview_recyclerview_home, parent, false);
        HImageHolder hImageHolder = new HImageHolder(view);
        return hImageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HImageHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class HImageHolder extends RecyclerView.ViewHolder {

        public HImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    ;
}
