package com.example.bankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class List_of_users extends AppCompatActivity {
    List<Model> modelList_displaylist=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    UserAdapter_list_of_uders userAdapter_list_of_uders;

    String phoneno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DisplayData();
    }

    public void DisplayData() {
        modelList_displaylist.clear();
        Cursor cursor =new DatabaseHelper(this).readalldata();
        while (cursor.moveToNext()){
            String balancedb=cursor.getString(2);
            Double amount=Double.parseDouble(balancedb);
            NumberFormat numberFormat=NumberFormat.getNumberInstance();
            numberFormat.setGroupingUsed(true);
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setMinimumFractionDigits(2);

            String balance=numberFormat.format(amount);
            Model model=new Model(cursor.getString(1),cursor.getString(0),balance);
            modelList_displaylist.add(model);
        }
        userAdapter_list_of_uders=new UserAdapter_list_of_uders(List_of_users.this,modelList_displaylist);
        recyclerView.setAdapter(userAdapter_list_of_uders);
    }

    public void user_information(int position) {
        phoneno=modelList_displaylist.get(position).getPhoneNo();
        Intent intent=new Intent(List_of_users.this,User_Info.class);
        intent.putExtra("phoneno",phoneno);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.History){
            startActivity(new Intent(this,History.class));
        }
        return super.onOptionsItemSelected(item);
    }
}