package com.example.bankingapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter_history extends RecyclerView.Adapter<ViewHolder> {
    History history;
    List<Model> modelList_history;
    TextView transaction_status;
    public UserAdapter_history(History history,List<Model> modelList_history){
        this.history=history;
        this.modelList_history=modelList_history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.historylist,parent,false);
        transaction_status=itemview.findViewById(R.id.transaction_status);
        ViewHolder viewHolder=new ViewHolder(itemview);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.from_name.setText(modelList_history.get(position).getFrom_name());
        holder.to_name.setText(modelList_history.get(position).getTo_name());
        holder.Balance.setText(modelList_history.get(position).getBalance());
        holder.transaction_status.setText(modelList_history.get(position).getTransaction_status());
        holder.date.setText(modelList_history.get(position).getDate());
        if(modelList_history.get(position).getTransaction_status().equals("Failed")){
            holder.transaction_status.setTextColor(Color.parseColor("#f40404"));
        }else{
            holder.transaction_status.setTextColor(Color.parseColor("#4BB543"));
        }
    }

    @Override
    public int getItemCount() {
        return modelList_history.size();
    }
}
