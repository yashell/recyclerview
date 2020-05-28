package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_del;
    private Button btn_list;
    private Button btn_gird;
    private Button btn_flow;
    private RecyclerView recyclerview;
//    private ArrayList<String> datas;
    private List<DataEntity> dataList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;

    private int total = 21;
    private int nowNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData(0);
        //设置recyclerview适配器
//        adapter = new MyRecyclerViewAdapter(MainActivity.this,dataList);
        adapter = new MyRecyclerViewAdapter(MainActivity.this,dataList);






        recyclerview.setAdapter(adapter);

        // layourManage   VERTICAL垂直  HORIZONTAL水平
        /**
         * layourManage
         * LinearLayoutManager实例
         * 第一个参数是传this Activity.this
         * 第二个参数 VERTICAL垂直  HORIZONTAL水平
         * 第三个参数 默认都是从0条显示，false的话从头 true 从尾
         */
        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

        /**
         * layourManage
         * GridLayoutManager实例
         * 第一个参数是传this Activity.this
         * 第二个参数是一行有多少条 int
         * 第三个参数 VERTICAL垂直  HORIZONTAL水平
         * 第四个参数 默认都是从0条显示，false的话从头 true 从尾
         */
//        recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,3,GridLayoutManager.VERTICAL,false));

        /**
         * layourManage
         * StaggeredGridLayoutManager实例
         * 第一个参数是有多少列 int
         * 第二个参数 VERTICAL垂直  HORIZONTAL水平
         */
//        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,GridLayoutManager.VERTICAL));
        recyclerview.scrollToPosition(30); //默认滚动到哪一条
        //添加分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.divider));
//        recyclerview.addItemDecoration(divider);

        // 这个写法也可以添加分割线，但颜色是通过  android:background="#ff0000" 来设置
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, DataEntity data) {
                Toast.makeText(MainActivity.this,"data"+data,Toast.LENGTH_SHORT).show();

            }
        });

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
                boolean noMore = total> dataList.size()?false:true;

//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                refreshlayout.finishLoadMore(1000,true,noMore);//传入false表示加载失败

            }
        });


    }

    private void initView() {
        btn_add =(Button) findViewById(R.id.btn_add);
        btn_del =(Button) findViewById(R.id.btn_del);
        btn_list = (Button)findViewById(R.id.btn_list);
        btn_gird = (Button)findViewById(R.id.btn_gird);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        recyclerview =(RecyclerView) findViewById(R.id.recyclerview);

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_gird.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }

    private void initData(int num) {
        //准备数据集合
//        datas = new ArrayList<>();
//        for (int i=num;i<num+10;i++){
//            datas.add("第"+i+"条数据");
//        }

        for (int i=num;i<num+10;i++){
            DataEntity apple = new DataEntity("电动车行业门店采集"+i, R.drawable.ic_launcher_background,0,"2020-05-21 19:51:02");
            dataList.add(apple);
        }

    }

    public void insertData(int num) {
//        int nu = (num+10) > total ?  total :  num+10;
//
//        for (int i=num;i<nu;i++){
//            datas.add("第"+i+"条数据");
//        }
//        adapter.notifyItemChanged(datas.size() - nu, datas.size());//局部刷新
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_del:
                break;
            case R.id.btn_list:
                recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_gird:
                recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_flow:
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,GridLayoutManager.VERTICAL));
                break;
        }


    }
}
