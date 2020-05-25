package com.example.recyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recyclerview.Utils.Utils;

import java.util.List;


public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.ViewHolder> {
    private List<DataEntity> dataList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View dataView;
        TextView ShowTitle;
        TextView ShowTime;


        public ViewHolder(View view) {
            super(view);
            dataView = view;
            ShowTitle = view.findViewById(R.id.rec_title);
            ShowTime = view.findViewById(R.id.rec_time);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.dataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                DataEntity data = dataList.get(position);
                if (Utils.isFastClick()) {
//                    Intent intent = new Intent(view.getContext(), HomeDetails.class);
//                    view.getContext().startActivity(intent);
                    Toast.makeText(view.getContext(),"data"+data,Toast.LENGTH_SHORT).show();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataEntity item = dataList.get(position);
        holder.ShowTitle.setText(item.getTitle());
        holder.ShowTime.setText(item.getTime());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public DataRecyclerAdapter(List<DataEntity> mdataList) {
        dataList = mdataList;
    }


}
