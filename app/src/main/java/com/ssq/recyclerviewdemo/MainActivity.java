package com.ssq.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RefreshLayout refreshLayout;
    private RecyclerView mRecyclerview;
    private List<Integer> typeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParam();
        initView();
    }

    private void initParam() {
        typeList.add(3);
        typeList.add(2);
        typeList.add(1);
        typeList.add(4);
        typeList.add(3);
        typeList.add(2);
        typeList.add(1);
        typeList.add(4);
    }

    private void initView() {
        mRecyclerview = findViewById(R.id.recyclerview);
        initRefresh();
        TopRecyclerViewAdapter adapter = new TopRecyclerViewAdapter(MainActivity.this, typeList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);
    }

    private void initRefresh() {
        refreshLayout = findViewById(R.id.refreshLayout);
        //设置下拉刷新的监听
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里可以做一下下拉刷新的操作
                //例如去请求后台接口啥的。。。
                Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "刷新成功！", Toast.LENGTH_LONG).show();
                        refreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });

        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                Toast.makeText(MainActivity.this, "上拉加载", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "加载成功！", Toast.LENGTH_LONG).show();
                        refreshLayout.setLoading(false);
                    }
                },1000);
            }
        });

        // 设置进度圈的背景色
//        refreshLayout.setProgressBackgroundColor(R.color.colorAccent);
        // 设置进度动画的颜色,可以使用多种颜色
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
    }
}
