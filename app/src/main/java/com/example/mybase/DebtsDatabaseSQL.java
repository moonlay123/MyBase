package com.example.mybase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mybase.database.Debt;
import com.example.mybase.database.DebtsDatabase;

import java.util.ArrayList;
import java.util.List;

public class DebtsDatabaseSQL extends SQLiteOpenHelper implements DebtsDatabase {
    private static final String DB_NAME ="debts.db";
    private static final String TABLE_NAME ="Debts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MONEY = "money";

    public DebtsDatabaseSQL(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME +" VARCHAR,"+
                COLUMN_MONEY +" INTEGER" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_NAME+";");
        onCreate(db);
    }

    @Override
    public void save(Debt debt) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,debt.name);
        values.put(COLUMN_MONEY,debt.money);
        db.insert(TABLE_NAME,null,values);
    }

    @Override
    public Debt get(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null,null);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int moneyIndex = cursor.getColumnIndex(COLUMN_MONEY);
        String name = cursor.getString(nameIndex);
        int money = cursor.getInt(moneyIndex);
        cursor.close();
        return new Debt(name,money,id);
    }

    @Override
    public List<Debt> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null,null);
        List<Debt> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int moneyIndex = cursor.getColumnIndex(COLUMN_MONEY);
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            String name = cursor.getString(nameIndex);
            int money = cursor.getInt(moneyIndex);
            int id = cursor.getInt(idIndex);
            list.add(new Debt(name,money,id));
        }
        cursor.close();
        return list;
    }
}
