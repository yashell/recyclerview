package com.example.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHodler> {
    private Context context;
    private ArrayList<String> datas;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 相当于getview方法中创建view和viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        return new MyViewHodler(itemView);
    }

    /**
     * 相当于getview方法中绑定数据的部分
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        // 根据位置得到相应的数据
        String data = datas.get(position);
        holder.tv_title.setText(data);

    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        private ImageView tv_icon;
        private TextView tv_title;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            tv_icon = (ImageView) itemView.findViewById(R.id.tv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);

            // itemView 是整行
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, datas.get(getLayoutPosition()));
                    }
                }
            });

            tv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"我是图片"+datas.get(getLayoutPosition()),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    /**
     * 点击RecyclerView某条的监听
     */
    public interface OnItemClickListener {

        /**
         * 当RecyclerView某个被点击时回调
         *
         * @param view 点击item视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data);
    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置RecyclerView某个监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
