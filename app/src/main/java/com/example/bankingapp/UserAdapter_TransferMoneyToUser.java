package com.example.bankingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter_TransferMoneyToUser extends RecyclerView.Adapter<ViewHolder> {

    TransferMoneyToUser transferMoneyToUser;
    List<Model> modelList_transferMoneyToUser;
    public UserAdapter_TransferMoneyToUser(TransferMoneyToUser transferMoneyToUser, List<Model> modelList_transferMoneyToUser) {
        this.modelList_transferMoneyToUser=modelList_transferMoneyToUser;
        this.transferMoneyToUser=transferMoneyToUser;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                transferMoneyToUser.transfer_To_selceted_user(position);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(modelList_transferMoneyToUser.get(position).getName());
        holder.phoneno.setText(modelList_transferMoneyToUser.get(position).getPhoneNo());
        holder.Balance.setText(modelList_transferMoneyToUser.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return modelList_transferMoneyToUser.size();
    }
}
