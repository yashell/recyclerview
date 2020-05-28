package com.example.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.Utils.Utils;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHodler> {
    private Context context;
    private ArrayList<String> datas;
    private List<DataEntity> dataList;

    public MyRecyclerViewAdapter(Context context,List<DataEntity> mdataList) {
        this.context = context;
        this.dataList = mdataList;
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
        View itemView = View.inflate(context, R.layout.recyclerview_item, null);
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

        DataEntity data = dataList.get(position);
        holder.rec_title.setText(data.getTitle());
        holder.rec_time.setText(data.getTime());
        holder.rec_icon.setImageResource(data.getImageId());
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 添加数据
     * @param position
     * @param data
     */
    public void addData(int position, String data) {
        datas.add(position,data);
        //刷新适配器
        notifyItemInserted(position);
    }

    /**
     * 删除数据
     * @param position
     */
    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHodler extends RecyclerView.ViewHolder{
        private ImageView rec_icon;
        private TextView rec_title;
        private TextView rec_time;


        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            rec_icon = (ImageView) itemView.findViewById(R.id.rec_icon);
            rec_title = (TextView) itemView.findViewById(R.id.rec_title);
            rec_time = (TextView) itemView.findViewById(R.id.rec_time);

            // itemView 是整行
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, dataList.get(getLayoutPosition()));
                    }
                }
            });




//            tv_icon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),"我是图片"+datas.get(getLayoutPosition()),Toast.LENGTH_SHORT).show();
//                }
//            });

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
        public void onItemClick(View view, DataEntity data);
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
