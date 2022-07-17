package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TABLE_NAME = "user_table";
    private final String TRANSFER_TABLE="transfer_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        sqliteDatabase.execSQL("create table "+TRANSFER_TABLE+" (TRANSFER_ID INTEGER PRIMARY KEY AUTOINCREMENT,FROM_NAME VARCHAR,TO_NAME VARCHAR,AMOUNT DECIMAL,Status VARCHAR,DATE VARCHAR )");
        sqliteDatabase.execSQL("insert into user_table values(1234567890,'Joey',9472.00,'jory.02@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
        sqliteDatabase.execSQL("insert into user_table values(2345678901,'Chandler',582.67,'chandi.03@gmail.com','XXXXXXXXXXXX2341','BCA98765432')");
        sqliteDatabase.execSQL("insert into user_table values(3456789012,'Monica Gellar',1359.56,'mongellar.04@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
        sqliteDatabase.execSQL("insert into user_table values(4567890123,'Rachel Green',1500.01,'greenrach.05@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
        sqliteDatabase.execSQL("insert into user_table values(5678901234,'Ben',2603.48,'bengellar.06@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
        sqliteDatabase.execSQL("insert into user_table values(6789012345,'Guna',945.16,'guna.07@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
        sqliteDatabase.execSQL("insert into user_table values(7890123456,'sammy',5936.00,'sam.08@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        sqliteDatabase.execSQL("insert into user_table values(8901234567,'Janice',857.22,'jannu.09@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
        sqliteDatabase.execSQL("insert into user_table values(9012345678,'Amy',4398.46,'amy.10@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
        sqliteDatabase.execSQL("insert into user_table values(1234567809,'Pickles',273.90,'piccy.01@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int oldVersion, int newVersion) {
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS "+TRANSFER_TABLE);
        onCreate(sqliteDatabase);
    }

    public Cursor readalldata(){
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        return sqliteDatabase.rawQuery("select * from user_table", null);
    }

    public Cursor readparticulardata(String phonenumber) {
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();

        return sqliteDatabase.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);

    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        return sqliteDatabase.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        sqliteDatabase.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }
    public Cursor read_Transfer_Histroy(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select * from transfer_table",null);
    }
    public boolean insertTransferRecord(String from_name,String to_name,String date,String amount,String transaction_status){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("FROM_NAME",from_name);
        contentValues.put("TO_NAME",to_name);
        contentValues.put("AMOUNT",amount);
        contentValues.put("Status",transaction_status);
        contentValues.put("DATE",date);
        Long result=sqLiteDatabase.insert(TRANSFER_TABLE,null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
}
