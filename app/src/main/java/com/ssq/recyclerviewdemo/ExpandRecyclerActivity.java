package com.ssq.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.ssq.recyclerviewdemo.model.ChapterInfo;
import com.ssq.recyclerviewdemo.model.CourseInfo;
import com.ssq.recyclerviewdemo.model.SectionInfo;

import java.util.ArrayList;
import java.util.List;

public class ExpandRecyclerActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CourseInfo mCourseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_recycler);

        initData();
        initViews();
    }

    private void initData() {
        //假数据
        mCourseInfo = new CourseInfo();
        mCourseInfo.name = "假装是课程的名称";
        for (int i = 0; i < 10; i++) {
            ChapterInfo chapterInfo = new ChapterInfo();
            chapterInfo.name = "假装是课时名称" + (i + 1);
            chapterInfo.chapterIndex = i;
            if (i == 0) {
                for (int j = 0; j < 2; j++) {
                    SectionInfo sectionInfo = new SectionInfo();
                    sectionInfo.name = "第" + (j + 1) + "节";
                    sectionInfo.chapterIndex = i;
                    sectionInfo.sectionIndex = j;
                    chapterInfo.sectionInfos.add(sectionInfo);
                }
            } else if (i == 1) {
                for (int j = 0; j < 3; j++) {
                    SectionInfo sectionInfo = new SectionInfo();
                    sectionInfo.name = "第" + (j + 1) + "节";
                    sectionInfo.chapterIndex = i;
                    sectionInfo.sectionIndex = j;
                    chapterInfo.sectionInfos.add(sectionInfo);
                }
            } else if (i == 2) {
            } else {
                for (int j = 0; j < 4; j++) {
                    SectionInfo sectionInfo = new SectionInfo();
                    sectionInfo.name = "第" + (j + 1) + "节";
                    sectionInfo.chapterIndex = i;
                    sectionInfo.sectionIndex = j;
                    chapterInfo.sectionInfos.add(sectionInfo);
                }
            }
            mCourseInfo.chapterInfos.add(chapterInfo);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_expand);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ChapterAdapter chapterAdapter = new ChapterAdapter(this, mCourseInfo);
        mRecyclerView.setAdapter(chapterAdapter);
        chapterAdapter.setOnItemClickListener(new ChapterAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, ChapterAdapter.ViewName viewName, int chapterIndex, int sectionIndex) {
                //LogUtils.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
                switch (viewName) {
                    case CHAPTER_ITEM:
                        if (mCourseInfo.chapterInfos.get(chapterIndex).sectionInfos.size() > 0) {
                            LogUtils.v("---onClick---just expand or narrow: " + chapterIndex);
                            if (chapterIndex + 1 == mCourseInfo.chapterInfos.size()) {
                                //如果是最后一个，则滚动到展开的最后一个item
                                mRecyclerView.smoothScrollToPosition(chapterAdapter.getItemCount());
                                LogUtils.v("---onClick---scroll to bottom");
                            }
                        } else {
                            onClickChapter(chapterIndex);
                        }
                        break;
                    case CHAPTER_ITEM_PRACTISE:
                        onClickPractise(chapterIndex);
                        break;
                    case SECTION_ITEM:
                        onClickSection(chapterIndex, sectionIndex);
                        break;
                }
            }
        });

        //以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果。
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return chapterAdapter.getItemViewType(position) == ChapterAdapter.VIEW_TYPE_CHAPTER ? 4 : 1;
            }
        });
        mRecyclerView.setLayoutManager(manager);
    }

    private void onClickChapter(int chapterIndex) {
        LogUtils.v("---onClick---play chapter: " + chapterIndex);
        ToastUtil.showShort("播放" + chapterIndex);
    }

    private void onClickSection(int chapterIndex, int sectionIndex) {
        LogUtils.v("---onClick---play---section: " + chapterIndex + ", " + sectionIndex);
        ToastUtil.showShort("播放" + chapterIndex + ", " + sectionIndex);
    }

    private void onClickPractise(int chapterIndex) {
        LogUtils.v("---onClick---practise: " + chapterIndex);
    }

}
