package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SmartRefreshLayout extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<DataEntity> dataList = new ArrayList<>();
    private int dataTotal = 21;

    private DataRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh_layout);

        recyclerview =(RecyclerView) this.findViewById(R.id.recyclerview);
        initData(0);
        adapter = new DataRecyclerAdapter(dataList);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerview.scrollToPosition(0); //默认滚动到哪一条
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                dataList.clear();
                insertData(0);
                refreshlayout.finishRefresh(1000,true,false);//传入false表示刷新失败

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                insertData(dataList.size());
                boolean noMore = dataTotal> dataList.size()?false:true;
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                refreshlayout.finishLoadMore(1000,true,noMore);//传入false表示加载失败

            }
        });

    }

    private void initData(int num) {
        //准备数据集合
        for (int i=num;i<num+10;i++){
            DataEntity apple = new DataEntity("电动车行业门店采集"+i, R.drawable.ic_launcher_background,0,"2020-05-21 19:51:02");
            dataList.add(apple);
        }
    }

    public void insertData(int num) {
        int nu = (num+10) > dataTotal ?  dataTotal :  num+10;

        for (int i=num;i<nu;i++){
            DataEntity apple = new DataEntity("电动车行业门店采集"+i, R.drawable.ic_launcher_background,0,"2020-05-21 19:51:02");
            dataList.add(apple);
        }
        adapter.notifyItemChanged(dataList.size() - nu, dataList.size());//局部刷新
    }

}
