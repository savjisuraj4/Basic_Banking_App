package com.example.bankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter_list_of_uders extends RecyclerView.Adapter<ViewHolder> {
    List_of_users list_of_users;
    List<Model> modelList;
    public  UserAdapter_list_of_uders(List_of_users list_of_users, List<Model> modelList){
        this.list_of_users=list_of_users;
        this.modelList=modelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                list_of_users.user_information(position);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(modelList.get(position).getName());
        holder.phoneno.setText(modelList.get(position).getPhoneNo());
        holder.Balance.setText(modelList.get(position).balance);
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

}
