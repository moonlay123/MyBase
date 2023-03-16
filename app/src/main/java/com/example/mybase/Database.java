package com.example.mybase;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
@androidx.room.Database(entities = {DebtEntity.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DebtDAO debtDAO();
}
