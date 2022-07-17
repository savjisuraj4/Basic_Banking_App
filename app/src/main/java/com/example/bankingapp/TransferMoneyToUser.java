package com.example.bankingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransferMoneyToUser extends AppCompatActivity {
    UserAdapter_TransferMoneyToUser adapter;
    List<Model> modelList_transferMoneyToUser=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String Name,phoneno,currentamount,transferamount,date,selected_userphoneno,selected_username,seleced_userbalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money_to_user);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Bundle bundle=getIntent().getExtras();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        date = simpleDateFormat.format(calendar.getTime());
        if(bundle!=null){
            phoneno = bundle.getString("phonenumber");
            Name = bundle.getString("name");
            currentamount = bundle.getString("currentamount");
            transferamount = bundle.getString("transferamount");
            showdata(phoneno);
        }
    }

    private void showdata(String phoneno) {
        modelList_transferMoneyToUser.clear();
        Cursor cursor=new DatabaseHelper(this).readselectuserdata(phoneno);
        while (cursor.moveToNext()){
            String balancefromdb=cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model=new Model(cursor.getString(1),cursor.getString(0),price);
            modelList_transferMoneyToUser.add(model);
        }
        adapter=new UserAdapter_TransferMoneyToUser(TransferMoneyToUser.this,modelList_transferMoneyToUser);
        recyclerView.setAdapter(adapter);
    }
    public void transfer_To_selceted_user(int position) {
        selected_userphoneno=modelList_transferMoneyToUser.get(position).getPhoneNo();
        Cursor cursor=new DatabaseHelper(this).readparticulardata(selected_userphoneno);
        while (cursor.moveToNext()){
            seleced_userbalance=cursor.getString(2);
            selected_username=cursor.getString(1);
            Double Dou_selected_user_beforebalance=Double.parseDouble(seleced_userbalance);
            Double Dou_selected_user_transfer_amount=Double.parseDouble(transferamount);
            Double Dou_selected_user_new_amount=Dou_selected_user_transfer_amount+Dou_selected_user_beforebalance;

            Double Dou_debited_user_newamont=Double.parseDouble(currentamount)-Double.parseDouble(transferamount);
            new DatabaseHelper(this).updateAmount(selected_userphoneno,Dou_selected_user_new_amount.toString());
            new DatabaseHelper(this).updateAmount(phoneno,Dou_debited_user_newamont.toString());
            new DatabaseHelper(this).insertTransferRecord(Name,selected_username,date,Dou_selected_user_transfer_amount.toString(),"Success");
            Toast.makeText(this, "Transaction Successful!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(TransferMoneyToUser.this, List_of_users.class));
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(TransferMoneyToUser.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new DatabaseHelper(TransferMoneyToUser.this).insertTransferRecord(Name,"No Selected",date,transferamount,"Failed");
                        Toast.makeText(TransferMoneyToUser.this, "Transaction Cancelled !!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(TransferMoneyToUser.this, List_of_users.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }
}