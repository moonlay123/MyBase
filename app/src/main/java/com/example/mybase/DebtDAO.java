package com.example.mybase;

import android.provider.ContactsContract;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mybase.database.Debt;

import java.util.List;
@Dao
public interface DebtDAO {

    @Query("SELECT * FROM DebtEntity")
    List<DebtEntity> getAll();

    @Insert
    void save(DebtEntity debt);

    @Query("SELECT * FROM DebtEntity WHERE id = :id")
    DebtEntity getId(int id);

    @Delete
    void delete(DebtEntity debt);

    @Update
    void update(DebtEntity debt);
}
