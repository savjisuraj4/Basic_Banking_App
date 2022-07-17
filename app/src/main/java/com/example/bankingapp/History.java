package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    List<Model> history_list=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TextView empty_history_text;
    UserAdapter_history adapter_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        empty_history_text=findViewById(R.id.empty_text);
        showHistory();
    }

    private void showHistory() {
        history_list.clear();
        Cursor cursor=new DatabaseHelper(this).read_Transfer_Histroy();
        while (cursor.moveToNext()){
            String amountfromhistory=cursor.getString(3);
            Double balance=Double.parseDouble(amountfromhistory);
            NumberFormat nf=NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price=nf.format(balance);

            Model model=new Model(cursor.getString(1),cursor.getString(2),price,cursor.getString(5),cursor.getString(4));
            history_list.add(model);
        }
        adapter_history=new UserAdapter_history(History.this,history_list);
        recyclerView.setAdapter(adapter_history);

        if(history_list.isEmpty()){
            empty_history_text.setVisibility(View.VISIBLE);
        }
    }
}