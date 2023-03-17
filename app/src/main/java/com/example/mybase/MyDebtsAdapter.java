package com.example.mybase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybase.database.Debt;
import com.example.mybase.databinding.ItemDeptBinding;

import java.util.List;

public class MyDebtsAdapter extends
        RecyclerView.Adapter<MyDebtsAdapter.MyHolder> {
    List<DebtEntity> data;
    public MyDebtsAdapter(List<DebtEntity> data){
        this.data=data;
    }

    @NonNull
    @Override
    public MyDebtsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDeptBinding binding = ItemDeptBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyDebtsAdapter.MyHolder holder, int position) {
        holder.name.setText(data.get(position).money+"");
        holder.money.setText(""+position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView money;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            money = itemView.findViewById(R.id.money);
        }
    }
}
