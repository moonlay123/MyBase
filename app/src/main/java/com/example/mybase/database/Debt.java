package com.example.mybase.database;

public class Debt {
    public int id;
    public String name;
    public int money;
    public Debt(String name, int money, int id){
        this.money=money;
        this.name=name;
        this.id=id;
    }
}
