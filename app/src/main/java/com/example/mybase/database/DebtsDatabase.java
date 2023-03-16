package com.example.mybase.database;

import java.util.List;

public interface DebtsDatabase {
    void save(Debt debt);
    Debt get(int id);
    public List<Debt> getAll();
}
