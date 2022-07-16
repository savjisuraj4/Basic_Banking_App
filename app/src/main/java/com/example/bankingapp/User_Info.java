package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class User_Info extends AppCompatActivity {
    TextView Name,Phone,EmailID,AccountNO,IFSC_Code,Current_balance;
    String PHONE_NO;
    Double newbalance;
    Button transfer_money;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Name=findViewById(R.id.username);
        Phone=findViewById(R.id.userphonenumber);
        EmailID=findViewById(R.id.email);
        AccountNO=findViewById(R.id.account_no);
        IFSC_Code=findViewById(R.id.ifsc_code);
        Current_balance=findViewById(R.id.balance);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            PHONE_NO=bundle.getString("phoneno");
            showdata(PHONE_NO);
        }
        transfer_money=findViewById(R.id.transfer_button);
        transfer_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterAmountToTransfer();
            }
        });

    }

    public void EnterAmountToTransfer() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(User_Info.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_transfer_money, null);
        mBuilder.setTitle("Enter amount").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);

        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                transactionCancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAmount.getText().toString().isEmpty()){
                    mAmount.setError("Amount can't be empty");
                }else if(Double.parseDouble(mAmount.getText().toString()) > newbalance){
                    mAmount.setError("Your account don't have enough balance");
                }
                else if(Double.parseDouble(mAmount.getText().toString())<=0){
                    mAmount.setError("Amount can't be zero");
                }else{
                    Intent intent = new Intent(User_Info.this, TransferMoneyToUser.class);
                    intent.putExtra("phonenumber", Phone.getText().toString());
                    intent.putExtra("name", Name.getText().toString());
                    intent.putExtra("currentamount", newbalance.toString());
                    intent.putExtra("transferamount", mAmount.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void transactionCancel() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(User_Info.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(User_Info.this, "Transaction Cancelled !!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                EnterAmountToTransfer();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }




    public void showdata(String PHONE_NO) {
        Cursor cursor=new DatabaseHelper(this).readparticulardata(PHONE_NO);
        while (cursor.moveToNext()){
            String balancefromdb=cursor.getString(2);
            newbalance=Double.parseDouble(balancefromdb);
            NumberFormat nf=NumberFormat.getNumberInstance();
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            Name.setText(cursor.getString(1));
            Phone.setText(cursor.getString(0));
            EmailID.setText(cursor.getString(3));
            AccountNO.setText(cursor.getString(4));
            Current_balance.setText(cursor.getString(2));
            IFSC_Code.setText(cursor.getString(5));
        }
    }
    
}