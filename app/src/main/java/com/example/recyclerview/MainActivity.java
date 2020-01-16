package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_del;
    private Button btn_list;
    private Button btn_gird;
    private Button btn_flow;
    private RecyclerView recyclerview;
    private ArrayList<String> datas;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        //设置recyclerview适配器
        adapter = new MyRecyclerViewAdapter(MainActivity.this,datas);
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

        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this,"data"+data,Toast.LENGTH_SHORT).show();

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

    private void initData() {
        //准备数据集合
        datas = new ArrayList<>();
        for (int i=0;i<100;i++){
            datas.add("第"+i+"条数据");
        }
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
