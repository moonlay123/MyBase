package com.example.mybase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.mybase.database.Debt;
import com.example.mybase.database.DebtsDatabase;
import com.example.mybase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DebtDAO debtDAO = App.getDatabase().debtDAO();
        ArrayList<DebtEntity> data = new ArrayList<>();
        new Thread(() -> {
            List<DebtEntity> debts = debtDAO.getAll();
            runOnUiThread(() -> {
                data.addAll(debts);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                binding.recyclerView.setAdapter(new MyDebtsAdapter(data));
            });
        }).start();
        binding.add.setOnClickListener(view ->{
            DebtEntity debt =new DebtEntity("123",(int)(Math.random()*1000));
            data.add(debt);
            new Thread(()->{
                debtDAO.save(debt);
            });
            binding.recyclerView.getAdapter().notifyItemChanged(data.size()-1);
        });
        binding.delete.setOnClickListener(view ->{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage("Вы уверены?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DebtEntity a = data.get(data.size()-1);
                                    new Thread(()->{
                                        debtDAO.delete(a);
                                    });
                                    data.remove(data.size()-1);
                                    binding.recyclerView.getAdapter().notifyItemRemoved(data.size());
                                }
                            })
                            .setNegativeButton("Cancel",null);
            AlertDialog alert = alertDialog.create();
            alert.show();
        });
        binding.update.setOnClickListener(view ->{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setMessage("Вы уверены?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DebtEntity debt =new DebtEntity("123",(int)(Math.random()*1000));
                            data.set(data.size()-1,debt);
                            new Thread(()->{
                                debtDAO.update(debt);
                            });
                           binding.recyclerView.getAdapter().notifyItemChanged(data.size()-1);
                        }
                    })
                    .setNegativeButton("Cancel",null);
            AlertDialog alert = alertDialog.create();
            alert.show();
            });
    }
}