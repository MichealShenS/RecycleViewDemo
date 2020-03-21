package com.ssq.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssq.recyclerviewdemo.model.BaseInfo;
import com.ssq.recyclerviewdemo.model.ChapterInfo;
import com.ssq.recyclerviewdemo.model.CourseInfo;
import com.ssq.recyclerviewdemo.model.SectionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Mr.Shen
 * Date : 2020/3/21 7:53
 * Description : 一个可展开和收起的RecyclerView数据处理，传进的数据和显示的数据分开，展开添加item，收起则删除item。
 */
public class ChapterAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Integer> typeList = new ArrayList<>();

    public static final int VIEW_TYPE_CHAPTER = 1;
    public static final int VIEW_TYPE_SECTION = 2;
    //当前展开的课时，-1代表没有任何展开
    private int curExpandChapterIndex = -1;
    private Context mContext;

    //传进来的课程信息
    private CourseInfo courseInfo;

    //显示的数据集
    private List<BaseInfo> dataInfos = new ArrayList<>();

    public ChapterAdapter(Context context, CourseInfo _courseInfo) {
        this.mContext = context;
        this.courseInfo = _courseInfo;
        for (BaseInfo info : courseInfo.chapterInfos) {
            dataInfos.add(info);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == VIEW_TYPE_CHAPTER) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chapter, parent, false);
            return new ItemHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycler_view, parent, false);
            return new ItemSectionHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_CHAPTER) {
            ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.itemView.setTag(position);
            itemHolder.tvPractise.setTag(position);

            ChapterInfo chapterInfo = (ChapterInfo) dataInfos.get(position);
            itemHolder.tvName.setText(chapterInfo.name);

            if (chapterInfo.sectionInfos.size() > 0) {
                itemHolder.ivArrow.setVisibility(View.VISIBLE);
                if (curExpandChapterIndex == position) {
                    itemHolder.ivArrow.setBackgroundResource(R.color.colorAccent);
                } else {
                    itemHolder.ivArrow.setBackgroundResource(R.color.colorPrimary);
                }
            } else {
                itemHolder.ivArrow.setVisibility(View.INVISIBLE);
            }

        } else {
            ItemSectionHolder itemSectionHolder = (ItemSectionHolder) holder;
//            itemSectionHolder.tvName.setTag(position);
            itemSectionHolder.recyclerView.setTag(position);
            setItemRecyclerAdapter(itemSectionHolder.recyclerView);

//            SectionInfo sectionInfo = (SectionInfo) dataInfos.get(position);
//            itemSectionHolder.tvName.setText(sectionInfo.name);
        }

    }

    //该方法只更改itemView的部分信息，不全部刷新
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            String str = (String) payloads.get(0);
            //更改view的tag
            if (str.equals("change_position")) {
                if (getItemViewType(position) == VIEW_TYPE_CHAPTER) {
                    ItemHolder itemHolder = (ItemHolder) holder;
                    itemHolder.itemView.setTag(position);
                    itemHolder.tvPractise.setTag(position);
                    //改变箭头方向
                    if (curExpandChapterIndex == position) {
                        itemHolder.ivArrow.setBackgroundResource(R.color.colorAccent);
                    } else {
                        itemHolder.ivArrow.setBackgroundResource(R.color.colorPrimary);
                    }
                } else {
                    ItemSectionHolder itemSectionHolder = (ItemSectionHolder) holder;
//                    itemSectionHolder.tvName.setTag(position);
                    itemSectionHolder.recyclerView.setTag(position);
                }
            }
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (dataInfos == null) {
            return 0;
        } else {
            return dataInfos.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataInfos.get(position) instanceof ChapterInfo) {
            return VIEW_TYPE_CHAPTER;
        } else if (dataInfos.get(position) instanceof SectionInfo) {
            return VIEW_TYPE_SECTION;
        }
        return super.getItemViewType(position);
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public LinearLayout llBg;
        public ImageView ivArrow;
        public TextView tvName;
        public TextView tvPractise;
        public LinearLayout llSection;
        public GridView gvSection;

        public ItemHolder(View itemView) {
            super(itemView);
            ivArrow = (ImageView) itemView.findViewById(R.id.iv_item_chapter_arrow);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_chapter_name);
            tvPractise = (TextView) itemView.findViewById(R.id.tv_item_chapter_practise);

            //将创建的View注册点击事件
            itemView.setOnClickListener(ChapterAdapter.this);
            tvPractise.setOnClickListener(ChapterAdapter.this);
        }
    }

    public class ItemSectionHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public RecyclerView recyclerView;

        public ItemSectionHolder(View itemView) {
            super(itemView);
//            tvName = (TextView) itemView.findViewById(R.id.tv_item_section_name);
//
//            //将创建的View注册点击事件
//            tvName.setOnClickListener(ChapterAdapter.this);

            recyclerView = itemView.findViewById(R.id.item_recyclerview);
            initItemRecyclerDataList();
//            TopRecyclerViewAdapter adapter = new TopRecyclerViewAdapter(mContext, typeList);
//            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//            recyclerView.setAdapter(adapter);

        }
    }

    private void setItemRecyclerAdapter(RecyclerView recyclerView) {
        TopRecyclerViewAdapter adapter = new TopRecyclerViewAdapter(mContext, typeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }

    private void initItemRecyclerDataList() {
        typeList.add(1);
        typeList.add(3);
        typeList.add(3);
        typeList.add(3);
        typeList.add(3);
//        typeList.add(2);
//        typeList.add(4);
//        typeList.add(3);
//        typeList.add(2);
//        typeList.add(1);
//        typeList.add(4);
    }

    ////////////////////////////以下为item点击处理///////////////////////////////

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * item里面有多个控件可以点击
     */
    public enum ViewName {
        CHAPTER_ITEM,
        CHAPTER_ITEM_PRACTISE,
        SECTION_ITEM
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int chapterIndex, int sectionIndex);
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            int position = (int) v.getTag();
            ViewName viewName = ViewName.CHAPTER_ITEM;
            int chapterIndex = -1;
            int sectionIndex = -1;
            if (getItemViewType(position) == VIEW_TYPE_CHAPTER) {
                ChapterInfo chapterInfo = (ChapterInfo) dataInfos.get(position);
                chapterIndex = chapterInfo.chapterIndex;
                sectionIndex = -1;
                if (v.getId() == R.id.tv_item_chapter_practise) {
                    viewName = ViewName.CHAPTER_ITEM_PRACTISE;
                } else {
                    viewName = ViewName.CHAPTER_ITEM;
                    if (chapterInfo.sectionInfos.size() > 0) {
                        if (chapterIndex == curExpandChapterIndex) {
                            narrow(curExpandChapterIndex);
                        } else {
                            narrow(curExpandChapterIndex);
                            expand(chapterIndex);
                        }
                    }
                }
            } else if (getItemViewType(position) == VIEW_TYPE_SECTION) {
                SectionInfo sectionInfo = (SectionInfo) dataInfos.get(position);
                viewName = ViewName.SECTION_ITEM;
                chapterIndex = sectionInfo.chapterIndex;
                sectionIndex = sectionInfo.sectionIndex;
            }
            mOnItemClickListener.onClick(v, viewName, chapterIndex, sectionIndex);
        }

    }

    /**
     * 展开某个item
     *
     * @param chapterIndex
     */
    private void expand(int chapterIndex) {
        dataInfos.addAll(chapterIndex + 1, courseInfo.chapterInfos.get(chapterIndex).sectionInfos);
        curExpandChapterIndex = chapterIndex;
//        LogUtils.v("---expand---" + (chapterIndex + 1) + ", " + courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size());
        notifyItemRangeInserted(chapterIndex + 1, courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size());

        /*notifyItemRangeChanged(chapterIndex + 1 + courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size(),
                getItemCount() - chapterIndex - 1, "change_position");*/
        notifyItemRangeChanged(0, getItemCount(), "change_position");
    }

    /**
     * 收起某个item
     *
     * @param chapterIndex
     */
    private void narrow(int chapterIndex) {
        if (chapterIndex != -1) {
            int removeStart = chapterIndex + 1;
            int removeCount = 0;
            for (int i = removeStart; i < dataInfos.size() && getItemViewType(i) == VIEW_TYPE_SECTION; i++) {
                removeCount++;
            }
            dataInfos.removeAll(courseInfo.chapterInfos.get(chapterIndex).sectionInfos);
            curExpandChapterIndex = -1;
//            LogUtils.v("---narrow---" + removeStart + ", " + removeCount);
            notifyItemRangeRemoved(removeStart, removeCount);
            //notifyItemRangeChanged(removeStart, getItemCount() - removeStart, "change_position");
            notifyItemRangeChanged(0, getItemCount(), "change_position");
        }
    }

}
