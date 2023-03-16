package com.example.mybase;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mybase.database.Debt;
import com.example.mybase.database.DebtsDatabase;

import java.util.ArrayList;
import java.util.List;

public class DebtsDatabaseSP implements DebtsDatabase {
    private SharedPreferences storage;
    public DebtsDatabaseSP(Context context){
        storage = context.getSharedPreferences("storage",Context.MODE_PRIVATE);
    }
    @Override
    public void save(Debt debt) {
        int size = storage.getInt("size",0);
        SharedPreferences.Editor editor = storage.edit();
        editor.putString("name"+size,debt.name);
        editor.putInt("money"+size,debt.money);
        editor.putInt("size",size+1);
        editor.apply();
    }

    @Override
    public Debt get(int id) {
        String name = storage.getString("name"+id,"");
        int money = storage.getInt("money"+id,0);
        return new Debt(name,money,id);
    }

    @Override
    public List<Debt> getAll() {
        int size = storage.getInt("size",0);
        ArrayList<Debt> list = new ArrayList<>();
        for (int i=0;i < size;i++){
            list.add(get(i));
        }
        return list;
    }
}
