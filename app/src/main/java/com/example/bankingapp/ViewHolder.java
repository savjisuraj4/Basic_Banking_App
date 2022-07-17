package com.example.bankingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder {

    TextView Name, phoneno, Balance, mRupee, mRupeeslash,from_name,to_name,transaction_status,date;
    ImageView imageView;
    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        Name = itemView.findViewById(R.id.user_name);
        phoneno = itemView.findViewById(R.id.phoneno);
        Balance = itemView.findViewById(R.id.balance);
        mRupee = itemView.findViewById(R.id.rupee);
        mRupeeslash = itemView.findViewById(R.id.rupeesign);
        date=itemView.findViewById(R.id.date);
        imageView=itemView.findViewById(R.id.arrow);
        transaction_status=itemView.findViewById(R.id.transaction_status);
        from_name=itemView.findViewById(R.id.from_name);
        to_name=itemView.findViewById(R.id.To_name);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }
    private ViewHolder.ClickListener mClickListener;
    public interface  ClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
